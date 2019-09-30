package org.crown.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.crown.framework.mapper.BaseMapper;
import org.crown.model.dto.LogDTO;
import org.crown.model.entity.LogTable;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 登录报表表 Mapper 接口
 * </p>
 *
 * @author zqchen
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LogTable> {

    /**
     * 获取默认昨天到今天的登录日志
     *
     * @param uid
     * @return
     * @RequestParam(value = "userId", required = false) String userId,
      @RequestParam(value = "startDate", required = false) String startDate,
      @RequestParam(value = "endDate", required = false) String endDate,
      @RequestParam(value = "dateType", required = false) String dateType
     */
	IPage<LogTable> getNowLoginLogLists(Page<LogTable> page, String userId, String startDate, String endDate, String dateType);

    /**
     * 日志报表汇出
     *
     * @param userId
     * @return
     */
    List<LogDTO> getAllLogs();
}
