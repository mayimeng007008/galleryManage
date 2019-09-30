package org.crown.service;

import org.crown.framework.service.BaseService;
import org.crown.model.dto.VisitedDTO;
import org.crown.model.entity.VisitedTable;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 访问次数报表 服务类
 * </p>
 *
 * @author zqchen
 */
public interface IVisitedLogService extends BaseService<VisitedTable> {

    /**
     * 默认查询当天登录日志
     *
     * @param uid
     * @return
     */
	IPage<VisitedDTO> getVisitedLogLists(Page<VisitedTable> page, String pagePath,  String startDate,  String endDate,  String dateType);

}
