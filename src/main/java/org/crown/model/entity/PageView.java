
package org.crown.model.entity;

import org.crown.framework.model.convert.Convert;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 相册活动表
 * </p>
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("pageview")
public class PageView extends Convert {

    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    /**
     * 访问量
     */
    @TableField(value="visits")
    private Integer visits;
    
    /**
     * 活动uuid
     */
    @TableField(value="activity_uuid")
    private String activityUuid;

}
