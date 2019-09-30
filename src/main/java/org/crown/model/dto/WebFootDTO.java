package org.crown.model.dto;

import java.time.LocalDateTime;

import org.crown.framework.model.convert.Convert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 网站底部DTO
 * </p>
 *
 * @author zqchen
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WebFootDTO extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "主键")
    private Integer id;
    @ApiModelProperty(notes = "网站logo")
    private String logo;
    
    @ApiModelProperty(notes = "网站banner图")
    private String bannerImg;
    
    @ApiModelProperty(notes = "馆别ID")
    private String libId;
    
    @ApiModelProperty(notes = "联系电话")
    private String phone;

    @ApiModelProperty(notes = "传真")
    private String fax;

    @ApiModelProperty(notes = "邮箱")
    private String email;

    @ApiModelProperty(notes = "地址")
    private String addressZhCn;
    @ApiModelProperty(notes = "安全网址")
    private String securityurl;

    @ApiModelProperty(notes = "qrcode1")
    private String qrcode1;

    @ApiModelProperty(notes = "qrcode2")
    private String qrcode2;

    @ApiModelProperty(notes = "创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(notes = "修改时间")
    private LocalDateTime updateTime;
	 
}
