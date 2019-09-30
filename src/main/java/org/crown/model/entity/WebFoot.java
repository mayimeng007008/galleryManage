
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
 * 系统用户表
 * </p>
 *
 * @author Caratacus
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("webfooter")
public class WebFoot extends Convert {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "footersn", type = IdType.AUTO)
    private Integer id;
    
     /**
     * logo
     */
     private String logo;

     /**
      * bannerImg 图
      */
     @TableField(value="bannerImg")
      private String bannerImg;

      /**
      * libId
      */
     @TableField(value="libId")
      private String libId;

      
     /**
     * address_zh_tw
     */
     private String addressZhTw;

     /**
     * address_en_us
     */
     private String addressEnUs;

     /**
     * phone
     */
     private String phone;

     /**
     * fax
     */
     private String fax;

     /**
     * email
     */
     private String email;

     /**
     * securityurl
     */
     private String securityurl;

     /**
     * qrcode_1
     */
     @TableField(value="qrcode_1")
     private String qrcode1;

     /**
     * qrcode_2
     */
     @TableField(value="qrcode_2")
     private String qrcode2;

     /**
     * isdataeffid
     */
     private Integer isdataeffid;


     /**
     * 越南語
     */
     private String addressVi;

     /**
     * address_zh_cn
     */
     private String addressZhCn;

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

}
