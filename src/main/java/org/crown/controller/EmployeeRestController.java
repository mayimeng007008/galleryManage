package org.crown.controller;

import java.util.Objects;

import org.apache.commons.codec.digest.Md5Crypt;
import org.crown.common.annotations.Resources;
import org.crown.enums.AuthTypeEnum;
import org.crown.enums.StatusEnum;
import org.crown.framework.controller.SuperController;
import org.crown.framework.enums.ErrorCodeEnum;
import org.crown.framework.responses.ApiResponses;
import org.crown.framework.utils.ApiAssert;
import org.crown.model.dto.EmployeeDTO;
import org.crown.model.entity.Employee;
import org.crown.model.parm.EmployeePARM;
import org.crown.service.IEmployeeService;
import org.crown.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author Caratacus
 */
@Api(tags = {"Employee"}, description = "用户操作相关接口")
@RestController
@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class EmployeeRestController extends SuperController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IOrgService orgService;

    
    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "需要检查的账号", paramType = "query"),
            @ApiImplicitParam(name = "nickname", value = "需要检查的账号", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "需要检查的账号", paramType = "query")
    })
    @GetMapping
    public ApiResponses<IPage<EmployeeDTO>> page(@RequestParam(value = "employeeid", required = false) String loginName,
                                             @RequestParam(value = "employeename", required = false) String nickname,
                                             @RequestParam(value = "islock", required = false) StatusEnum status) {
        Integer isOk = 1;
        
        return success(
                employeeService.query().likeRight(StringUtils.isNotEmpty(loginName), Employee::getEmployeeid, loginName)
                        .likeRight(StringUtils.isNotEmpty(nickname), Employee::getEmployeename, nickname)
                        .eq(Objects.nonNull(status), Employee::getIslock, status)
                        .eq(true, Employee::getIsdataeffid, isOk)
                        .page(this.<Employee>getPage())
                        .convert(
                        		e -> {
                        			EmployeeDTO empD = e.convert(EmployeeDTO.class);
                        			String orgname = orgService.getById(e.getOrgIds()).getOrgname();
                                    empD.setOrgname(orgname);
                                    return empD;
                                }
                        		//e -> e.convert(EmployeeDTO.class)
                        		)
        );
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("查询单个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    @GetMapping("/{id}")
    public ApiResponses<EmployeeDTO> get(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getById(id);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, employee);
         
        EmployeeDTO employeeDTO = employee.convert(EmployeeDTO.class);
        //List<Integer> roleIds = employeeService.getOrgIds(employee.getId());
        //employeeDTO.setOrgIds(roleIds);
        //employeeDTO.setOrgname(orgService.getById(employee.getOrgIds()).getOrgname());;
        return success(employeeDTO);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("设置用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}/status")
    public ApiResponses<Void> updateStatus(@PathVariable("id") Integer id, @RequestBody @Validated(EmployeePARM.Status.class) EmployeePARM employeePARM) {
        employeeService.updateStatus(id, employeePARM.getIslock());
        return success();
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("创建用户")
    @PostMapping
    public ApiResponses<Void> create(@RequestBody @Validated(EmployeePARM.Create.class) EmployeePARM employeePARM) {
        int count = employeeService.query().eq(Employee::getEmployeeid, employeePARM.getEmployeeid()).count();
        ApiAssert.isTrue(ErrorCodeEnum.USERNAME_ALREADY_EXISTS, count == 0);
        Employee employee = employeePARM.convert(Employee.class);
        //没设置密码 设置默认密码
        if (StringUtils.isEmpty(employee.getPwd())) {
            employee.setPwd(Md5Crypt.apr1Crypt(employee.getEmployeeid(), employee.getEmployeename()));
        } else {
        	employee.setPwd(Md5Crypt.apr1Crypt(employee.getPwd(), employee.getEmployeename()));
        }
        employeeService.save(employee);
        //employeeService.saveEmployeeOrgs(employee.getId(), employeePARM.getOrgIds());
        return success(HttpStatus.CREATED);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody @Validated(EmployeePARM.Update.class) EmployeePARM employeePARM) {
        Employee employee = employeePARM.convert(Employee.class);
        employee.setId(id);
      //没设置密码 设置默认密码
        if (StringUtils.isEmpty(employee.getPwd())) {
            employee.setPwd(Md5Crypt.apr1Crypt(employee.getEmployeeid(), employee.getEmployeename()));
        } else {
        	employee.setPwd(Md5Crypt.apr1Crypt(employee.getPwd(), employee.getEmployeename()));
        }
        
        employeeService.updateById(employee);
        //employeeService.saveEmployeeOrgs(id, employeePARM.getOrgIds());
        return success();
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {
    	//真删除
        //orgService.removeById(id);
    	
    	//假删除
    	Employee emp = new Employee();
    	emp.setId(id);
    	emp.setIsdataeffid(0);
    	employeeService.updateById(emp);
        return success(HttpStatus.NO_CONTENT);
    }
}

