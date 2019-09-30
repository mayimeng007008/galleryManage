package org.crown.controller;

import java.util.List;

import org.crown.common.annotations.Resources;
import org.crown.enums.AuthTypeEnum;
import org.crown.framework.controller.SuperController;
import org.crown.framework.responses.ApiResponses;
import org.crown.model.dto.ComboDTO;
import org.crown.model.dto.WebhomelinkDTO;
import org.crown.model.entity.Webhomelink;
import org.crown.model.parm.WebhomelinkPARM;
import org.crown.service.IWebhomelinkService;
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

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author Caratacus
 */
@Api(tags = {"Webhomelink"}, description = "菜单相关接口")
@RestController
@RequestMapping(value = "/homelinks", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class WebHomelinkRestController extends SuperController {

    @Autowired
    private IWebhomelinkService homelinkService;

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询所有菜单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "nameZhTw", value = "需要检查询的菜单名称", paramType = "query")
    })
    @GetMapping
    public ApiResponses<List<Webhomelink>> list(@RequestParam(value = "nameZhTw", required = false) String nameZhTw) {
    	Integer isOk = 1;
        
        return success(
        		homelinkService.query().like(StringUtils.isNotEmpty(nameZhTw), Webhomelink::getNameZhTw, nameZhTw)
                        .eq(true, Webhomelink::getIsdataeffid, isOk)
                        .list()
        );
        //return success(homelinkService.list());
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询父级菜单(下拉框)")
    @GetMapping("/combos")
    public ApiResponses<List<ComboDTO>> combos() {
        return success(
                homelinkService.query().select(Webhomelink::getId, Webhomelink::getNameZhTw).eq(true, Webhomelink::getIsdataeffid, 1).eq(true, Webhomelink::getParent,0)
                        .entitys(e -> {
                            ComboDTO combo = new ComboDTO();
                            combo.setId(e.getId());
                            combo.setName(e.getNameZhTw());
                            return combo;
                        })
        );
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询单个菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单ID", required = true, paramType = "path")
    })
    @GetMapping("/{id}")
    public ApiResponses<WebhomelinkDTO> get(@PathVariable("id") Integer id) {
        return success(homelinkService.getWebhomelinkDTODetails(id));
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "添加菜单")
    @PostMapping
    public ApiResponses<Void> create(@RequestBody @Validated(WebhomelinkPARM.Create.class) WebhomelinkPARM homelinkPARM) {
        Webhomelink homelink = homelinkPARM.convert(Webhomelink.class);
        homelinkService.saveWebhomelink(homelink );
        return success(HttpStatus.CREATED);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "修改菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody @Validated(WebhomelinkPARM.Update.class) WebhomelinkPARM homelinkPARM) {
        Webhomelink homelink = homelinkPARM.convert(Webhomelink.class);
        homelink.setId(id);
        homelinkService.updateWebhomelink(homelink );
        return success();
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "删除菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单ID", required = true, paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {
        //homelinkService.removeWebhomelink(id);
    	//假删除
    	Webhomelink homelink = new Webhomelink();
    	homelink.setId(id);
    	homelink.setIsdataeffid(0);
    	homelinkService.updateById(homelink);
        return success(HttpStatus.NO_CONTENT);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("设置菜单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}/status")
    public ApiResponses<Void> updateStatus(@PathVariable("id") Integer id, @RequestBody @Validated(WebhomelinkPARM.Status.class) WebhomelinkPARM homelinkPARM) {
        homelinkService.updateStatus(id, homelinkPARM.getDisplay());
        return success();
    }

}

