package org.crown.framework.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;

public class DeleteImgUtil {
	
	@Value("${lycm.uploadPath}")
    private  String uploadPath;
	@Value("${lycm.scaleImgPath}")
    private  String scaleImgPath;
	
	public  void deleteImgs(String imgName ) {
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
		
		
	}

}
