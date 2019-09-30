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

import org.crown.framework.service.impl.BaseServiceImpl;
import org.crown.mapper.LoginLogMapper;
import org.crown.model.dto.LogDTO;
import org.crown.model.entity.LogTable;
import org.crown.service.ILoginLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author Caratacus
 */
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogMapper, LogTable> implements ILoginLogService {

	@Override
	public IPage<LogDTO> getNowLoginLogLists(Page<LogTable> page, String userId, String startDate, String endDate,
			String dateType) {
		IPage<LogTable> conv = baseMapper.getNowLoginLogLists(page, userId, startDate, endDate, dateType);
		return conv.convert(log -> {
            LogDTO dto = log.convert(LogDTO.class);
			if(log.getIslogin().equals(1)) { 
				dto.setSuccessTimes(log.getCount());
				dto.setFailTimes(0); 
			} else { 
				dto.setSuccessTimes(0);
				dto.setFailTimes(log.getCount());
			}
            return dto;
        });
		
		//return conv;
	}

    


	@Override
	public List<LogDTO> getAllLogs() {
		// TODO Auto-generated method stub
		return null;
	}

}
