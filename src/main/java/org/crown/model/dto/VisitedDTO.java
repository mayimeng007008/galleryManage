package org.crown.model.dto;

import org.crown.framework.model.BaseModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VisitedDTO  extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes = "访问次数")
	private Integer clickNum;
	//@Excel(name = "日期",height = 12, width = 40)
	@ApiModelProperty(notes = "日期")
    private String createdate;
	//@Excel(name = "用户编号",height = 12, width = 40)
	@ApiModelProperty(notes = "页面路径")
    private String pagePath;
}
