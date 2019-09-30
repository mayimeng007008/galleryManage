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

import org.crown.common.annotations.Resources;
import org.crown.enums.AuthTypeEnum;
import org.crown.framework.controller.SuperController;
import org.crown.framework.responses.ApiResponses;
import org.crown.model.dto.LogDTO;
import org.crown.model.entity.LogTable;
import org.crown.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author Caratacus
 */
@Api(tags = {"LoginLog"}, description = "登录日志汇出报表相关接口")
@RestController
@RequestMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class LoginLogRestController extends SuperController {

    @Autowired
    private ILoginLogService loginLogService;

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("查询指定日期范围的登录日志报表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "需要查询的用户编号",required=false, paramType = "query"),
        @ApiImplicitParam(name = "startDate", value = "需要查询的开始日期", paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "需要查询的结束日期", paramType = "query"),
        @ApiImplicitParam(name = "dateType", value = "统计类型", paramType = "query")
    })
    @GetMapping("/loginLogs")
    public ApiResponses<IPage<LogDTO>> page(@RequestParam(value = "userId",
    		  required = false) String userId,
    		  @RequestParam(value = "startDate", required = false) String startDate ,
    		  @RequestParam(value = "endDate", required = false) String endDate,
    		  @RequestParam(value = "dateType", required = false,defaultValue="10") String dateType ) {
    	IPage<LogDTO> lists = loginLogService.getNowLoginLogLists(this.<LogTable>getPage(),userId, startDate, endDate, dateType);
    	return success(lists);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "查询所有日志报表")
    @GetMapping("/allloginLogs")
    public ApiResponses<List<LogTable>> list() {
        return success(loginLogService.list());
    }



}

