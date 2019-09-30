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
package org.crown.model.dto;

import java.time.LocalDateTime;

import org.crown.enums.MenuTypeEnum;
import org.crown.enums.StatusEnum;
import org.crown.framework.model.convert.Convert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户DTO
 * </p>
 *
 * @author Caratacus
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WebhomelinkDTO extends Convert {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(notes = "菜单名称")
    private String nameZhTw;
    
    @ApiModelProperty(notes = "连接方式 0：本页开启.1开新视窗.2：拥有第二层")
    private MenuTypeEnum isblank;
    
    @ApiModelProperty(notes = "连接网址")
    private String strurl;
    
    @ApiModelProperty(notes = "是否显示 0：关闭 1：显示")
    private StatusEnum display;
    
    @ApiModelProperty(notes = "点击率")
    private Integer clicknum;
    
    @ApiModelProperty(notes = "排序数")
    private Integer sortnum;
    
    @ApiModelProperty(notes = "上层菜单")
    private Integer parent;
    @ApiModelProperty(notes = "主键")
    private Integer id;
    
    @ApiModelProperty(notes = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(notes = "修改时间")
    private LocalDateTime updateTime;
	 
}
