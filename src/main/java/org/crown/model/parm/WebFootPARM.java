package org.crown.model.parm;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.crown.common.cons.Regex;
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
 * @author zqchen
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WebFootPARM extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "网站logo")
    @NotBlank(groups = {Create.class, Update.class}, message = "网站logo不能为空")
    private String logo;
    
    @ApiModelProperty(notes = "网站banner图")
    @NotBlank(groups = {Create.class, Update.class}, message = "网站banner图不能为空")
    private String bannerImg;
    
    @ApiModelProperty(notes = "馆别ID")
    @NotBlank(groups = {Create.class, Update.class}, message = "馆别ID不能为空")
    private String libId;
    
    @ApiModelProperty(notes = "地址")
    @NotBlank(groups = {Create.class, Update.class}, message = "地址不能为空")
    private String addressZhCn;
    
    @ApiModelProperty(notes = "联络电话")
    @Pattern(groups = {Create.class, Update.class}, regexp = Regex.PHONE, message = "电话号码格式不正确")
    private String phone;

    @ApiModelProperty(notes = "邮箱")
    @Email(groups = {Create.class, Update.class}, message = "邮箱格式不正确")
    private String email;
    
    @ApiModelProperty(notes = "传真")
    private String fax;

    public interface Create {

    }

    public interface Update {

    }

}
