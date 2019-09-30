package org.crown.model.parm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
 * 用户PARM
 * </p>
 *
 * @author Caratacus
 */
@ApiModel
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WebhomelinkPARM extends Convert {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "菜单名称")
    @NotBlank(groups = {Create.class, Update.class}, message = "菜单名称不能为空")
    private String nameZhTw;
    
    @ApiModelProperty(notes = "连接方式")
    @NotNull(groups = {Create.class, Update.class}, message = "请选择连接方式")
    private MenuTypeEnum isblank;
    
    @ApiModelProperty(notes = "连接网址")
    //@NotBlank(groups = {Create.class, Update.class}, message = "连接网址不能为空")
    private String strurl;
    
    @ApiModelProperty(notes = "是否显示")
    @NotNull(groups = {Create.class, Update.class}, message = "请选择是否显示")
    private StatusEnum display;
    
    @ApiModelProperty(notes = "点击率")
    //@NotNull(groups = {Create.class, Update.class}, message = "点击率不能为空")
    private Integer clicknum;
    
    @ApiModelProperty(notes = "排序数")
    //@NotNull(groups = {Create.class, Update.class}, message = "排序数不能为空")
    private Integer sortnum;
    
    @ApiModelProperty(notes = "上层菜单")
    //@NotNull(groups = {Create.class, Update.class}, message = "上层菜单不能为空")
    private Integer parent;
    
    

    public interface Create {

    }

    public interface Update {

    }
    
    public interface Status {
    	
    }
    

}
