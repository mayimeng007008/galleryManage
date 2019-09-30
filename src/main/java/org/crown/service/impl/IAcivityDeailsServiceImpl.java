package org.crown.service.impl;

import java.io.File;

import org.crown.framework.service.impl.BaseServiceImpl;
import org.crown.mapper.ActivityDetailsMapper;
import org.crown.model.entity.ActivityDetail;
import org.crown.service.IActivityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = false)
public class IAcivityDeailsServiceImpl extends BaseServiceImpl<ActivityDetailsMapper, ActivityDetail> implements IActivityDetailsService{
	
	
	@Value("${lycm.uploadPath}")
    private String uploadPath;
	@Value("${lycm.scaleImgPath}")
    private String scaleImgPath;
	
	
	@Autowired
	ActivityDetailsMapper activityDetailsMapper;
	
	
	@Override
	public void updateActivityDetail(ActivityDetail activityDetail) {
		
		activityDetailsMapper.updateDetail(activityDetail);
	}
	
	
	@Override
	@Transactional
	public void deleteImg(String imgName) {
		String imgPath=uploadPath+imgName;
		String imgScaleImgPath = scaleImgPath+imgName;
		File fileimgPath = new File(imgPath);
		File fileimgScaleImgPath = new File(imgScaleImgPath);
		if(fileimgPath.exists()&&fileimgPath.isFile()) {
			fileimgPath.delete();
		}
		if(fileimgScaleImgPath.exists()&&fileimgScaleImgPath.isFile()) {
			fileimgScaleImgPath.delete();
		}
		 ActivityDetail a= activityDetailsMapper.selectByImgName(imgName);
		if(a!=null) {
			activityDetailsMapper.deleteImg1(imgName);
		}else {
			activityDetailsMapper.deleteImg2(imgName);
		}
		
	}
	

}
