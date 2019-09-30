package org.crown.model.entity;

import java.time.LocalDateTime;

import org.crown.framework.model.convert.Convert;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("webloginlogs")
public class LogTable extends Convert {
	
	private static final long serialVersionUID = 1L;
	@TableId(value = "webloginlogs_sn", type = IdType.AUTO)
    private Integer id;
	
	 /**
     * 创建时间
     */
    @TableField(value="createdate",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
	
	private String username;
	
	private String loginip;
	private Integer systype;
	@TableField(value="userid")
	private String userId;
	//'成功1，失败0'
	private Integer islogin;
	//查询项别名
	@TableField(exist = false)
	private Integer count;
	@TableField(exist = false )
	private String createdate;
	
	
	
    
	
}
