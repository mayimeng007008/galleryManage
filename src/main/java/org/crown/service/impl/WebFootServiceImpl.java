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
import org.crown.mapper.WebFootMapper;
import org.crown.model.dto.WebFootDTO;
import org.crown.model.entity.WebFoot;
import org.crown.service.IWebFootService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 网站底部表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class WebFootServiceImpl extends BaseServiceImpl<WebFootMapper, WebFoot> implements IWebFootService {

    @Override
    public IPage<WebFootDTO> pageWebFootDTO(Page<WebFoot> page, String address) {
        IPage<WebFoot> orgPage = query().like(StringUtils.isNotEmpty(address), WebFoot::getAddressZhCn, address).eq(true, WebFoot::getIsdataeffid, 1).page(page);
        return orgPage.convert(org -> {
            WebFootDTO orgDTO = org.convert(WebFootDTO.class);
            return orgDTO;
        });
    }
}
