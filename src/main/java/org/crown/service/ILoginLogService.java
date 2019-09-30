package org.crown.service;

import java.util.List;

import org.crown.framework.service.BaseService;
import org.crown.model.dto.LogDTO;
import org.crown.model.entity.LogTable;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 登录日志报表 服务类
 * </p>
 *
 * @author zqchen
 */
public interface ILoginLogService extends BaseService<LogTable> {

    /**
     * 默认查询当天登录日志
     *
     * @param uid
     * @return
     */
	IPage<LogDTO> getNowLoginLogLists(Page<LogTable> page, String userId,  String startDate,  String endDate,  String dateType);

    /**
     * 获取开放权限资源列表
     *
     * @return
     */
    List<LogDTO> getAllLogs();

}
