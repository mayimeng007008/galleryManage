
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

/**
 * <p>
 * 相册活动详情表
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("webactivity_detail")
public class ActivityDetail extends Convert {

    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.UUID)
    private String uuid;
    /**
     * 活动id
     */
    @TableField(value="activity_uuid")
    private String activiyUuid;
    /**
     	* 默认为0,1表示已被删除
     */
    private Integer deleted;
    
    /**
     	* 标题
     */
    @TableField(value="activity_title")
    private String activityTitle;
    /**
     * 详情
     */
    @TableField(value="activity_detail")
    private String activityDetail;
    /**
     	* 活动封面宣传图1
     */
    private String img1;
    
    /**
     * 活动封面宣传图1
     */
    private String img2;

    /**
     * 创建者ID
     */
    @TableField(value="creator", fill = FieldFill.INSERT)
    private Integer createUid;
    
    /**
     * 创建时间``, ``,
 * ``, 
     */
    @TableField(value="created_date",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(value="update_date",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
