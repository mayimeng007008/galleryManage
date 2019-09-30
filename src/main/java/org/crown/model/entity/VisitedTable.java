package org.crown.model.entity;

import java.time.LocalDateTime;

import org.crown.framework.model.convert.Convert;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("websitepagehitcount")
public class VisitedTable extends Convert {
	
	private static final long serialVersionUID = 1L;
	@TableId(value = "uuid")
    private Integer id;
	
	 /**
     * 创建时间
     */
    @TableField(value="created_date",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
	
	//查询项别名
	@TableField(exist = false)
	private Integer clickNum;
	@TableField(exist = false )
	private String createdate;
	
	@TableField(exist = false )
	private String pagePath;
}
