package org.crown.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.crown.framework.mapper.BaseMapper;
import org.crown.model.entity.Images;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author Caratacus
 */
@Mapper
public interface ImagesMapper extends BaseMapper<Images> {

	int findCount(String galleryUuid);

	void addImgVisits(int id);

	void addImageThumbsUpById(int id, int num);



}
