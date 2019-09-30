
package org.crown.model.entity;

import java.time.LocalDateTime;

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
 * <p>
 * 系统用户表
 * </p>
 *
 * @author Caratacus
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("webemployee")
public class Employee extends Convert {

    private static final long serialVersionUID = 1L;
    
    @TableId(value = "employeesn", type = IdType.AUTO)
    private Integer id;
    /**
     	* 默认为1,0表示已被删除
     */
    private Integer isdataeffid;
    
    /**
     	* 所属群组组织
     */
    @TableField(value="orgid")
    private Integer orgIds;
    /**
     	* 登陆名
     */
    private String employeeid;
    /**
     * 登陆名
     */
    private String pwd;
    /**
     * 昵称
     */
    private String employeename;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String cellphone;
    
    /**
     * 状态 0：禁用 1：正常
     */
    private StatusEnum islock;

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
