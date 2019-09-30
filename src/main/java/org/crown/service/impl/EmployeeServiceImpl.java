/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.crown.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.collections4.CollectionUtils;
import org.crown.common.utils.JWTUtils;
import org.crown.common.utils.TypeUtils;
import org.crown.enums.StatusEnum;
import org.crown.framework.enums.ErrorCodeEnum;
import org.crown.framework.service.impl.BaseServiceImpl;
import org.crown.framework.utils.ApiAssert;
import org.crown.mapper.EmployeeMapper;
import org.crown.model.dto.EmployeeDetailsDTO;
import org.crown.model.dto.TokenDTO;
import org.crown.model.entity.Employee;
import org.crown.model.entity.EmployeeRole;
import org.crown.service.IEmployeeRoleService;
import org.crown.service.IEmployeeService;
import org.crown.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IEmployeeRoleService employeeRoleService;

    @Override
    @Transactional
    public Employee login(String employeeid, String pwd) {
        Employee employee = query().eq(Employee::getEmployeeid, employeeid).getOne();
        //用户不存在
        ApiAssert.notNull(ErrorCodeEnum.EmployeeNAME_OR_PASSWORD_IS_WRONG, employee);
        //用户名密码错误
        ApiAssert.isTrue(ErrorCodeEnum.EmployeeNAME_OR_PASSWORD_IS_WRONG, Md5Crypt.apr1Crypt(pwd, employeeid).equals(employee.getPwd()));
        //用户被禁用
        ApiAssert.isTrue(ErrorCodeEnum.Employee_IS_DISABLED, StatusEnum.NORMAL.equals(employee.getIslock()));
        updateById(employee);
        return employee;
    }

    @Override
    public TokenDTO getToken(Employee Employee) {
        Integer uid = Employee.getId();
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUid(uid);
        tokenDTO.setToken(JWTUtils.generate(uid));
        return tokenDTO;
    }

    @Override
    public EmployeeDetailsDTO getEmployeeDetails(Integer uid) {
        Employee Employee = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.Employee_NOT_FOUND, Employee);
        EmployeeDetailsDTO EmployeeDetails = Employee.convert(EmployeeDetailsDTO.class);
        EmployeeDetails.setPerms(resourceService.getUserPerms(uid));
        return EmployeeDetails;
    }

    @Override
    @Transactional
    public void updatePassword(Integer uid, String oldPassword, String newPassword) {
        Employee Employee = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.Employee_NOT_FOUND, Employee);
        //用户名密码错误
        ApiAssert.isTrue(ErrorCodeEnum.ORIGINAL_PASSWORD_IS_INCORRECT, Md5Crypt.apr1Crypt(oldPassword, Employee.getEmployeename()).equals(Employee.getPwd()));
        Employee.setPwd(Md5Crypt.apr1Crypt(newPassword, Employee.getEmployeename()));
        updateById(Employee);
    }

    @Override
    @Transactional
    public void resetPwd(Integer uid) {
        Employee Employee = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.Employee_NOT_FOUND, Employee);
        Employee.setPwd(Md5Crypt.apr1Crypt(Employee.getEmployeeid(), Employee.getEmployeename()));
        updateById(Employee);
    }

    @Override
    @Transactional
    public void updateStatus(Integer uid, StatusEnum status) {
        Employee Employee = getById(uid);
        ApiAssert.notNull(ErrorCodeEnum.Employee_NOT_FOUND, Employee);
        Employee.setIslock(status);
        updateById(Employee);
    }

	@Override
	@Transactional
	public void saveEmployeeOrgs(Integer uid, List<Integer> roleIds) {
		if (CollectionUtils.isNotEmpty(roleIds)) {
            employeeRoleService.delete().eq(EmployeeRole::getFuncUuid, uid).execute();
            employeeRoleService.saveBatch(roleIds.stream().map(e -> new EmployeeRole(uid, e)).collect(Collectors.toList()));
        }
		
	}

	@Override
	public List<Integer> getOrgIds(Integer uid) {
		return employeeRoleService.query().select(EmployeeRole::getEmployee).eq(EmployeeRole::getFuncUuid, uid).listObjs(TypeUtils::castToInt);
	}

}
