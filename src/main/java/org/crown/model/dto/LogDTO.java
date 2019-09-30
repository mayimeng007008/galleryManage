package org.crown.model.dto;

import org.crown.framework.model.BaseModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LogDTO  extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes = "登录次数")
	private Integer count;
	@ApiModelProperty(notes = "是否成功登录")
	private Integer islogin;
	//@Excel(name = "日期",height = 12, width = 40)
	@ApiModelProperty(notes = "日期")
    private String createdate;
	//@Excel(name = "用户编号",height = 12, width = 40)
	@ApiModelProperty(notes = "用户编号")
    private String userId;
	@ApiModelProperty(notes = "成功次数")
    private Integer successTimes;
	//@Excel(name = "失败次数",height = 12, width = 40)
	@ApiModelProperty(notes = "失败次数")
    private Integer failTimes;
    
	
}
