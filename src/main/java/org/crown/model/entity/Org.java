package org.crown.model.entity;
import java.time.LocalDateTime;

import org.crown.framework.model.BaseModel;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
*  org
* @author zqchen 2019-06-20
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("weborg")
public class Org extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
    * orgid
    */
    @TableId(value = "orgid")
    private Integer id;

    /**
    * orgname
    */
    private String orgname;

    /**
    * ispower
    */
    private Integer ispower;

    /**
    * latelychangeduser
    */
    //@TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer latelychangeduser;

    /**
    * dataowner
    */
    private Integer dataowner;

    /**
    * dataownergroup
    */
    private Integer dataownergroup;

    /**
    * createdate
    */
    @TableField(value="createdate", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
    * isdataeffid
    */
    private Integer isdataeffid;

    /**
    * latelychangeddate
    */
    @TableField(value = "latelychangeddate", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
    * auth
    */
    private String auth;

    /**
    * uuid
    */
    //@TableField(fill = FieldFill.INSERT)
    private String uuid;

    /**
    * 是否可查看全部資料
    */
    private Integer isauth;

    /**
    * isdailyreport
    */
    private Integer isdailyreport;

}
