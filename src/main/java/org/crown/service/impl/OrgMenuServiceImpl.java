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

import java.util.List;
import java.util.stream.Collectors;

import org.crown.framework.service.impl.BaseServiceImpl;
import org.crown.mapper.OrgMenuMapper;
import org.crown.model.entity.OrgMenu;
import org.crown.service.IOrgMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 群组组织菜单关系表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class OrgMenuServiceImpl extends BaseServiceImpl<OrgMenuMapper, OrgMenu> implements IOrgMenuService {

    @Override
    @Transactional
    public void saveOrgMenu(Integer orgId, List<Integer> menuIds) {
        delete().eq(OrgMenu::getOrgid, orgId).execute();
        saveBatch(menuIds.stream().map(menuId -> new OrgMenu(orgId, menuId)).collect(Collectors.toList()));
    }
}
