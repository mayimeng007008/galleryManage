package org.crown.controller;

import org.crown.common.annotations.Resources;
import org.crown.enums.AuthTypeEnum;
import org.crown.framework.controller.SuperController;
import org.crown.framework.responses.ApiResponses;
import org.crown.model.dto.VisitedDTO;
import org.crown.model.entity.VisitedTable;
import org.crown.service.IVisitedLogService;
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
@Api(tags = {"VisitedLog"}, description = "页面访问次数汇出报表相关接口")
@RestController
@RequestMapping(value = "/pageClickss", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class VisitedLogRestController extends SuperController {

    @Autowired
    private IVisitedLogService visitedLogService;

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("查询指定日期范围的页面访问次数报表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pagePath", value = "需要查询的页面路径",required=false, paramType = "query"),
        @ApiImplicitParam(name = "startDate", value = "需要查询的开始日期", paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "需要查询的结束日期", paramType = "query"),
        @ApiImplicitParam(name = "dateType", value = "统计类型", paramType = "query")
    })
    @GetMapping("/pageVistedLogs")
    public ApiResponses<IPage<VisitedDTO>> page(@RequestParam(value = "pagePath",
    		  required = false) String pagePath,
    		  @RequestParam(value = "startDate", required = false) String startDate ,
    		  @RequestParam(value = "endDate", required = false) String endDate,
    		  @RequestParam(value = "dateType", required = false,defaultValue="10") String dateType ) {
    	IPage<VisitedDTO> lists = visitedLogService.getVisitedLogLists(this.<VisitedTable>getPage(),pagePath, startDate, endDate, dateType);
    	return success(lists);
    }

}

