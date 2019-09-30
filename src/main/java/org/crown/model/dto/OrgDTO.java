package org.crown.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.crown.framework.model.BaseModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrgDTO extends BaseModel {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "群组组织名称")
    private String orgname;

    @ApiModelProperty(notes = "是否可查看全部資料")
    private String isauth;

    @ApiModelProperty(notes = "菜单ID集合")
    private List<Integer> menuIds;

    @ApiModelProperty(notes = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(notes = "修改时间")
    private LocalDateTime updateTime;
}
