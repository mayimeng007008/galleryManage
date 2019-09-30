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

import java.time.LocalDateTime;

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
public class ActivityPARM extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "活动主题")
    @NotBlank(groups = {Create.class, Update.class}, message = "活动主题不能为空")
	private String theme;
	/**
	 	*副主题
	 */
    @ApiModelProperty(notes = "副主题")
    //@NotBlank(groups = {Create.class, Update.class}, message = "副主题不能为空")
	private String subtopic;
    
    /**
     * 活动地址
     */
    @ApiModelProperty(notes = "活动地址")
    //@NotBlank(groups = {Create.class, Update.class}, message = "活动地址不能为空")
    private String address;
	
	/**
	 * 活动日期
	 */
    @ApiModelProperty(notes = "活动日期")
    //@NotBlank(groups = {Create.class, Update.class}, message = "活动日期不能为空")
	private String activityDate;

    public interface Create {

    }

    public interface Update {

    }

}
