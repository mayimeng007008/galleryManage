package org.crown.service;

import org.crown.framework.service.BaseService;
import org.crown.model.entity.ActivityDetail;

public interface IActivityDetailsService extends BaseService<ActivityDetail> {
	void updateActivityDetail(ActivityDetail activityDetail);

	void deleteImg(String imgName);


}
