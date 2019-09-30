package org.crown.model.entity;

import java.time.LocalDateTime;

import org.crown.enums.MenuTypeEnum;
import org.crown.enums.StatusEnum;
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
*  webhomelink
* @author zqchen 2019-06-28
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("webhomelink")
public class Webhomelink extends Convert {
	/**
	    * homelinksn
	    */
		@TableId(value = "homelinksn", type = IdType.AUTO)
	    private Integer id;

	    /**
	    * name_zh_tw
	    */
	    private String nameZhTw;

	    /**
	    * isblank
	    */
	    private MenuTypeEnum isblank;

	    /**
	    * display
	    */
	    private StatusEnum display;

	    /**
	    * sortnum
	    */
	    private Integer sortnum;

	    /**
	     * 创建者ID
	     */
	    @TableField(value="dataowner", fill = FieldFill.INSERT)
	    private Integer createUid;
	    /**
	     * 更新者ID
	     */
	    @TableField(value="latelychangeduser", fill = FieldFill.INSERT)
	    private Integer updateUid;
	    
	    /**
	     * 创建时间
	     */
	    @TableField(value="createdate",fill = FieldFill.INSERT)
	    private LocalDateTime createTime;
	    /**
	     * 修改时间
	     */
	    @TableField(value="latelychangeddate",fill = FieldFill.INSERT_UPDATE)
	    private LocalDateTime updateTime;
	    
	    /**
	    * isdataeffid
	    */
	    private Integer isdataeffid;

	    /**
	    * clicknum
	    */
	    private Integer clicknum;

	    /**
	    * strurl
	    */
	    private String strurl;

	    /**
	    * ispost
	    */
	    private Integer ispost;

	    /**
	    * parent
	    */
	    private Integer parent;

	    /**
	    * name_en_us
	    */
	    private String nameEnUs;

	    /**
	    * 是否於行動版顯示1表顯示0表不顯示
	    */
	    private Integer ismobile;

	    /**
	    * 越南語
	    */
	    private String nameVi;
}
