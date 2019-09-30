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

import org.crown.common.utils.TypeUtils;
import org.crown.framework.service.impl.BaseServiceImpl;
import org.crown.mapper.OrgMapper;
import org.crown.model.dto.OrgDTO;
import org.crown.model.entity.Org;
import org.crown.model.entity.OrgMenu;
import org.crown.service.IOrgMenuService;
import org.crown.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 群组组织表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class OrgServiceImpl extends BaseServiceImpl<OrgMapper, Org> implements IOrgService {

    @Autowired
    private IOrgMenuService orgMenuService;

    @Override
    public IPage<OrgDTO> pageOrgDTO(Page<Org> page, String orgName) {
        IPage<Org> orgPage = query().like(StringUtils.isNotEmpty(orgName), Org::getOrgname, orgName).eq(true, Org::getIsdataeffid, 1).page(page);
        return orgPage.convert(org -> {
            OrgDTO orgDTO = org.convert(OrgDTO.class);
            orgDTO.setMenuIds(orgMenuService.query()
                    .select(OrgMenu::getFuncUuid)
                    .eq(OrgMenu::getOrgid, org.getId())
                    .listObjs(TypeUtils::castToInt)
            );
            return orgDTO;
        });
    }
}
