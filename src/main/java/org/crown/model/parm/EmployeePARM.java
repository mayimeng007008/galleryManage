/*
 * Copyright (c) 2018-2022 Caratacus, (caratacus@qq.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.crown.model.parm;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.crown.common.cons.Regex;
import org.crown.enums.StatusEnum;
import org.crown.framework.model.convert.Convert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户PARM
 * </p>
 *
 * @author 
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeePARM extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "帐号")
    @NotBlank(groups = {Create.class, Update.class}, message = "使用者帐号不能为空")
    private String employeeid;
    @ApiModelProperty(notes = "姓名")
    @NotBlank(groups = {Create.class, Update.class}, message = "姓名不能为空")
    private String employeename;
    @Email(groups = {Create.class, Update.class}, message = "邮箱格式不正确")
    @ApiModelProperty(notes = "邮箱")
    private String email;
    @NotBlank(groups = {Create.class, Update.class}, message = "密码不能为空")
    @ApiModelProperty(notes = "密码")
    private String pwd;
    @Pattern(groups = {Create.class, Update.class}, regexp = Regex.PHONE, message = "手机号码格式不正确")
    @ApiModelProperty(notes = "手机号")
    private String cellphone;
    @NotNull(groups = Status.class, message = "用户状态不能为空")
    @ApiModelProperty(notes = "状态:0：开启 1：关闭")
    private StatusEnum islock;
    @ApiModelProperty(notes = "群组组织ID")
    @NotNull(groups = {Create.class, Update.class}, message = "所属群组组织不能为空")
    private Integer orgIds;

    public interface Create {

    }

    public interface Update {

    }

    public interface Status {

    }

}
