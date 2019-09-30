package org.crown.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.crown.framework.mapper.BaseMapper;
import org.crown.model.entity.VisitedTable;

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
public interface VisitedLogMapper extends BaseMapper<VisitedTable> {

    /**
     * 获取默认昨天到今天的访问次数
     *
     * @param uid
     * @return
     * @RequestParam(value = "userId", required = false) String userId,
      @RequestParam(value = "startDate", required = false) String startDate,
      @RequestParam(value = "endDate", required = false) String endDate,
      @RequestParam(value = "dateType", required = false) String dateType
     */
	IPage<VisitedTable> getVisitedLogLists(Page<VisitedTable> page, String pagePath, String startDate, String endDate, String dateType);


}
