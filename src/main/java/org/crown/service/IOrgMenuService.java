package org.crown.service;

import java.util.List;

import org.crown.framework.service.BaseService;
import org.crown.model.entity.OrgMenu;

public interface IOrgMenuService extends BaseService<OrgMenu> {

    /**
     * 保存群组组织和菜单关系
     *
     * @param orgId
     * @param menuIds
     */
    void saveOrgMenu(Integer orgId, List<Integer> menuIds);
}
