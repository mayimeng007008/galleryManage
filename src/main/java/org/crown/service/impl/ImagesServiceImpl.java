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

import org.crown.framework.service.impl.BaseServiceImpl;
import org.crown.mapper.ImagesMapper;
import org.crown.model.entity.Images;
import org.crown.service.IImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 表 服务实现类
 * </p>
 *
 */
@Transactional(readOnly = false)
@Service
public class ImagesServiceImpl extends BaseServiceImpl<ImagesMapper, Images> implements IImagesService {

	@Autowired
	ImagesMapper imagesMapper;
	
	@Override
	public int findCount(String galleryUuid) {
	
		return imagesMapper.findCount(galleryUuid);
	}

	@Override
	public void addImgVisits(int id) {
		
		imagesMapper.addImgVisits(id);
	}

	@Override
	public Images findImageById(int id) {
		
		return imagesMapper.selectById(id);
	}

	@Override
	public void addImageThumbsUpById(int id, int num) {
		
		imagesMapper.addImageThumbsUpById(id,num);
	}

	
}
