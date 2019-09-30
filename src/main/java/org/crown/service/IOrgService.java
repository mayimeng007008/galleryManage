package org.crown.service;

import org.crown.framework.service.BaseService;
import org.crown.model.dto.OrgDTO;
import org.crown.model.entity.Org;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface IOrgService extends BaseService<Org> {

    /**
     * 获取群组组织详情列表
     *
     * @param page
     * @param orgName
     * @return
     */
    IPage<OrgDTO> pageOrgDTO(Page<Org> page, String orgName);
}
