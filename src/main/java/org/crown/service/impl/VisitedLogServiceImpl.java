package org.crown.service.impl;

import org.crown.framework.service.impl.BaseServiceImpl;
import org.crown.mapper.VisitedLogMapper;
import org.crown.model.dto.VisitedDTO;
import org.crown.model.entity.VisitedTable;
import org.crown.service.IVisitedLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 访问次数报表实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class VisitedLogServiceImpl extends BaseServiceImpl<VisitedLogMapper, VisitedTable> implements IVisitedLogService {

	@Override
	public IPage<VisitedDTO> getVisitedLogLists(Page<VisitedTable> page, String pagePath, String startDate, String endDate,
			String dateType) {
		IPage<VisitedTable> conv = baseMapper.getVisitedLogLists(page, pagePath, startDate, endDate, dateType);
		return conv.convert(vi -> {
			VisitedDTO dto = vi.convert(VisitedDTO.class);
			
            return dto;
        });
		
		//return conv;
	}

}
