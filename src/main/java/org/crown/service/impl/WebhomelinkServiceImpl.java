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

import java.util.Objects;

import org.crown.enums.StatusEnum;
import org.crown.framework.enums.ErrorCodeEnum;
import org.crown.framework.service.impl.BaseServiceImpl;
import org.crown.framework.utils.ApiAssert;
import org.crown.mapper.WebhomelinkMapper;
import org.crown.model.dto.WebhomelinkDTO;
import org.crown.model.entity.Webhomelink;
import org.crown.service.IWebhomelinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class WebhomelinkServiceImpl extends BaseServiceImpl<WebhomelinkMapper, Webhomelink> implements IWebhomelinkService {

    @Override
    @Transactional
    public void saveWebhomelink(Webhomelink homelink) {
        save(homelink);
    }

    @Override
    @Transactional
    public void updateWebhomelink(Webhomelink homelink ) {
        updateById(homelink);
    }

    @Override
    @Transactional
    public void removeWebhomelink(Integer homelinkId) {
        if (parentIdNotNull(homelinkId)) {
            query().eq(Webhomelink::getParent, homelinkId)
                    .list()
                    .stream()
                    .filter(e -> parentIdNotNull(e.getParent()))
                    .forEach(e -> removeWebhomelink(e.getId()));
            //删除菜单
            removeById(homelinkId);
        }

    }

    @Override
    @Transactional
    public void updateStatus(Integer homelinkId, StatusEnum status) {
        Webhomelink homelink = getById(homelinkId);
        ApiAssert.notNull(ErrorCodeEnum.MENU_NOT_FOUND, homelink);
        homelink.setDisplay(status);
        updateById(homelink);
    }

    /**
     * 父ID不为0并且不为空
     *
     * @param parentId
     * @return
     */
    private boolean parentIdNotNull(Integer parentId) {
        return Objects.nonNull(parentId) && parentId != 0;
    }

    @Override
    public WebhomelinkDTO getWebhomelinkDTODetails(Integer homelinkId) {
        Webhomelink homelink = getById(homelinkId);
        ApiAssert.notNull(ErrorCodeEnum.MENU_NOT_FOUND, homelink);
        WebhomelinkDTO homelinkDTO = homelink.convert(WebhomelinkDTO.class);
        return homelinkDTO;
    }
}
