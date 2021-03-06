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
package org.crown.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.crown.common.annotations.Resources;
import org.crown.enums.AuthTypeEnum;
import org.crown.framework.controller.SuperController;
import org.crown.framework.responses.ApiResponses;
import org.crown.model.dto.OrgDTO;
import org.crown.model.entity.Org;
import org.crown.model.parm.OrgPARM;
import org.crown.service.IOrgMenuService;
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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 群组组织表 前端控制器
 * </p>
 *
 * @author Caratacus
 */
@Api(tags = {"Org"}, description = "群组组织相关接口")
@RestController
@RequestMapping(value = "/orgs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class OrgRestController extends SuperController {

    @Autowired
    private IOrgService orgService;
    @Autowired
    private IOrgMenuService orgMenuService;

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询所有群组组织(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgname", value = "需要查询的群组组织名", paramType = "query")
    })
    @GetMapping
    public ApiResponses<IPage<OrgDTO>> page(@RequestParam(value = "orgname", required = false) String orgName) {
        return success(orgService.pageOrgDTO(this.<Org>getPage(), orgName));
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询所有群组组织")
    @GetMapping("/orgs")
    public ApiResponses<List<Org>> list() {
    	//不带where条件查询
        //return success(orgService.list());
        return success(orgService.list(new QueryWrapper<Org>().eq("isdataeffid", 1)));
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询单个群组组织")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "群组组织ID", required = true, paramType = "path")
    })
    @GetMapping("/{id}")
    public ApiResponses<Org> get(@PathVariable("id") Integer id) {
        Org org = orgService.getById(id);
        return success(org);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "添加群组组织")
    @PostMapping
    public ApiResponses<Void> create(@RequestBody @Validated(OrgPARM.Create.class) OrgPARM orgPARM) {
        orgService.save(orgPARM.convert(Org.class));
        return success(HttpStatus.CREATED);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "修改群组组织")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}")
    public ApiResponses<Void> update(@PathVariable("id") Integer id, @RequestBody @Validated(OrgPARM.Update.class) OrgPARM orgPARM) {
        Org org = orgPARM.convert(Org.class);
        org.setId(id);
        orgService.updateById(org);
        return success();
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "删除群组组织")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "群组组织ID", required = true, paramType = "path")
    })
    @DeleteMapping("/{id}")
    public ApiResponses<Void> delete(@PathVariable("id") Integer id) {
    	//真删除
        //orgService.removeById(id);
    	
    	//假删除
    	Org org = new Org();
    	org.setId(id);
    	org.setIsdataeffid(0);
        orgService.updateById(org);
        return success(HttpStatus.NO_CONTENT);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "修改群组组织菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "群组组织ID", required = true, paramType = "path")
    })
    @PutMapping("/{id}/menus")
    public ApiResponses<Void> menus(@PathVariable("id") Integer id, @RequestBody @NotEmpty(message = "菜单ID不能为空") List<Integer> menuIds) {
        orgMenuService.saveOrgMenu(id, menuIds);
        return success();
    }
}

