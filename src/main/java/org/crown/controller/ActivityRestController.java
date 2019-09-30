package org.crown.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.crown.common.annotations.Resources;
import org.crown.common.utils.JsonUtil;
import org.crown.common.utils.UploadUtil;
import org.crown.enums.AuthTypeEnum;
import org.crown.framework.controller.SuperController;
import org.crown.framework.enums.ErrorCodeEnum;
import org.crown.framework.responses.ApiResponses;
import org.crown.framework.utils.ApiAssert;
import org.crown.model.dto.ActivityDTO;
import org.crown.model.dto.ActivityDetailDTO;
import org.crown.model.entity.Activity;
import org.crown.model.entity.ActivityDetail;
import org.crown.model.entity.PageView;
import org.crown.model.parm.ActivityPARM;
import org.crown.service.IActivityDetailsService;
import org.crown.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 系统活动表 前端控制器
 * </p>
 *
 * @author Caratacus
 */
@Api(tags = {"Activity"}, description = "活动操作相关接口")
@RestController
@RequestMapping(value = "/activity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class ActivityRestController extends SuperController {

    @Autowired
    private IActivityService activityService;
    
    @Autowired
    private IActivityDetailsService activityDetailService;
    
    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("查询所有活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "theme", value = "需要查询的活动名称", paramType = "query")
    })
    @GetMapping
    
    public ApiResponses<IPage<ActivityDTO>> page(@RequestParam(value = "theme", required = false) String theme) {
        Integer isOk = 0;
        
        return success(
                activityService.query().like(StringUtils.isNotEmpty(theme), Activity::getTheme, theme)
                        .eq(true, Activity::getDeleted, isOk)
                        .page(this.<Activity>getPage())
                        .convert(
                        		at -> {
                        			ActivityDTO atD = at.convert(ActivityDTO.class);
                        			//ActivityDetail AD=activityDetailService.findImgPath(atD.getUuid());
                        			 QueryWrapper<ActivityDetail> queryWrapper= new QueryWrapper<>();
                        			   queryWrapper.eq("activity_uuid", atD.getUuid());
                        			   ActivityDetail activityDeail=activityDetailService.getOne(queryWrapper);
                        			atD.setThemeImgPath(activityDeail.getImg1());
                        			//System.out.println(atD);
                                    return atD;
                                }
                        		//e -> e.convert(ActivityDTO.class)
                        		)
        );
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("查询单个活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "活动uuID", required = true, paramType = "path")
    })
    @GetMapping("/{uuid}")
    public ApiResponses<ActivityDTO> get(@PathVariable("uuid") String uuid) {
        Activity activity = activityService.getById(uuid);
        ApiAssert.notNull(ErrorCodeEnum.USER_NOT_FOUND, activity);
        activityService.updateVisits(uuid);
       PageView PV=  activityService.findVisits(uuid);
        ActivityDTO activityDTO = activity.convert(ActivityDTO.class);
        activityDTO.setVisits(PV.getVisits());
        System.out.println(activityDTO);
        return success(activityDTO);
    }
    
    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("创建活动")
    @PostMapping
    public ApiResponses<Activity> create(@RequestBody @Validated(ActivityPARM.Create.class) ActivityPARM ActivityPARM) {
        Activity activity = ActivityPARM.convert(Activity.class);
        
        activityService.save(activity);
        ActivityDetail detail = new ActivityDetailDTO().convert(ActivityDetail.class);
        detail.setActiviyUuid(activity.getUuid());
        activityDetailService.save(detail);
        PageView pv = new PageView();
        pv.setVisits(0).setActivityUuid(activity.getUuid());
       activityService.addVisits(pv);
        return success(activity);
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation("修改活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "活动uuID", required = true, paramType = "path")
    })
    @PutMapping("/{uuid}")
    public ApiResponses<Void> update(@PathVariable("uuid") String uuid, @RequestBody @Validated(ActivityPARM.Update.class) ActivityPARM ActivityPARM) {
        Activity activity = ActivityPARM.convert(Activity.class);
        activity.setUuid(uuid);
        activityService.updateById(activity);
        return success();
    }

    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "删除活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uuid", value = "活动uuID", required = true, paramType = "path")
    })
    @DeleteMapping("/{uuid}")
    public ApiResponses<Void> delete(@PathVariable("uuid") String uuid) {
    	
    	Activity at = new Activity();
    	at.setUuid(uuid);
    	at.setDeleted(1);
    	activityService.updateById(at);
        return success(HttpStatus.NO_CONTENT);
    }
    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "删除取消上传的照片")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "imgPathArr", value = "照片的路径数组", required = true, paramType = "path")
    })
    @GetMapping("/deleteImgs")
    public void deleteImgs(@PathParam("imgPathArr") String[] imgPathArr) {
    	activityService.deleteImgs(imgPathArr);
    }
    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "批量删除照片")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "ids", value = "照片的id数组", required = true, paramType = "path")
    })
    @GetMapping("/deleteSelectedImgs")
    public void deleteSelectedImgs(@PathParam("ids") int[] ids) {
    	activityService.deleteSelectedImgs(ids);
    }
    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "删除全部照片")
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "uuId", value = "相册的uuId", required = true, paramType = "path")
    })
    @GetMapping("/deleteAllImgs")
    public void deleteAllImgs(@PathParam("uuId") String uuId ) {
    	activityService.deleteAllImgs(uuId);
    }
    
    
    @Autowired
    UploadUtil uploadUtil;

    /**
     * 头像上传 目前首先相对路径
     */
    @Resources(auth = AuthTypeEnum.AUTH)
    @ApiOperation(value = "上传图片")
    @PostMapping(value = "/upload")
    public ApiResponses<JsonUtil> imgUpload(HttpServletRequest req, @RequestParam("file") MultipartFile[] files,
    		@RequestParam("uuid") String uuid, ModelMap model) {
    	
    	Map<String, Object> map = uploadUtil.upload(files, uuid);
    	//System.out.println(map);
        JsonUtil j = new JsonUtil();
        j.setMsg(map);
        System.out.println(j);
        //j.setData(map);
        return success(j);
    }
    
}

