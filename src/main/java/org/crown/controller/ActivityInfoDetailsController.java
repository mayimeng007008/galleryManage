package org.crown.controller;

import org.crown.common.annotations.Resources;
import org.crown.enums.AuthTypeEnum;
import org.crown.framework.controller.SuperController;
import org.crown.framework.enums.ErrorCodeEnum;
import org.crown.framework.responses.ApiResponses;
import org.crown.framework.utils.ApiAssert;
import org.crown.model.dto.ActivityDTO;
import org.crown.model.dto.ActivityDetailDTO;
import org.crown.model.entity.Activity;
import org.crown.model.entity.ActivityDetail;
import org.crown.model.parm.ActivityPARM;
import org.crown.service.IActivityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 活动详情控制器
 * </p>
 *
 * @author Caratacus
 */
@Api(tags = {"ActivityDetails"}, description = "活动详情相关操作接口")
@RestController
@RequestMapping(value = "/activityDetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class ActivityInfoDetailsController extends SuperController{
	
	@Autowired
	IActivityDetailsService detailsService;
	
	
	   @Resources(auth = AuthTypeEnum.AUTH)
	    @ApiOperation("查询单个活动详情")
	    @ApiImplicitParams({
	            @ApiImplicitParam(name = "activityUuid", value = "活动uuID", required = true, paramType = "path")
	    })
	    @GetMapping("/{activityUuid}")
	    public ApiResponses<ActivityDetailDTO> get(@PathVariable("activityUuid") String activityUuid) {
		   QueryWrapper<ActivityDetail> queryWrapper= new QueryWrapper<>();
		   queryWrapper.eq("activity_uuid", activityUuid);
		   ActivityDetail activityDeail=detailsService.getOne(queryWrapper);
	       //ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, activityDeail);
	         
	        ActivityDetailDTO activityDeailDTO = activityDeail.convert(ActivityDetailDTO.class);
	        //System.out.println(65);
	        //System.out.println(activityDeailDTO);
	        return success(activityDeailDTO);
	    }
	   
	   @Resources(auth = AuthTypeEnum.AUTH)
	   @ApiOperation("修改活动详情")
	   @ApiImplicitParams({
           @ApiImplicitParam(name = "activityUuid", value = "活动uuID", required = true, paramType = "path")
   })
	   @PutMapping("/{activityUuid}")
	public ApiResponses<Void> update(@PathVariable("activityUuid") String activityUuid,@RequestBody ActivityDetail activityDetail ) {
		   activityDetail.setActiviyUuid(activityUuid);
		   detailsService.updateActivityDetail(activityDetail); 
	        return success();
	}
	
	   @Resources(auth = AuthTypeEnum.AUTH)
	   @ApiOperation("删除活动详情照片")
	   @ApiImplicitParams({
		   @ApiImplicitParam(name = "imgName", value = "照片名字", required = true, paramType = "path")
	   })
	   @GetMapping("/deleteImg/{imgName}")
	   public ApiResponses<Void> deleteImg(@PathVariable("imgName") String imgName ) {
		  
		   detailsService.deleteImg(imgName); 
		   return success();
	   }
	   
}
