

package org.crown.service;

import org.crown.enums.StatusEnum;
import org.crown.framework.service.BaseService;
import org.crown.model.dto.WebhomelinkDTO;
import org.crown.model.entity.Webhomelink;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author 
 */
public interface IWebhomelinkService extends BaseService<Webhomelink> {

    /**
     * 保存菜单
     *
     * @param homelink
     * @param resourceIds
     */
    void saveWebhomelink(Webhomelink homelink );

    /**
     * 修改菜单
     *
     * @param homelink
     * @param resourceIds
     */
    void updateWebhomelink(Webhomelink homelink);

    /**
     * 递归删除菜单
     *
     * @param homelinkId
     */
    void removeWebhomelink(Integer homelinkId);

    /**
     * 修改菜单状态
     *
     * @param homelinkId
     * @param status
     */
    void updateStatus(Integer homelinkId, StatusEnum status);

    /**
     * 获取菜单详情
     *
     * @param homelinkId
     * @return
     */
    WebhomelinkDTO getWebhomelinkDTODetails(Integer homelinkId);

}
