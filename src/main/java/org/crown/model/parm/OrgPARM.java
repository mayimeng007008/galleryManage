package org.crown.model.parm;

import javax.validation.constraints.NotBlank;

import org.crown.framework.model.convert.Convert;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrgPARM extends Convert {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "群组组织名称")
    @NotBlank(groups = {Create.class, Update.class}, message = "群组组织名称不能为空")
    private String orgname;

    public interface Create {

    }

    public interface Update {

    }
}