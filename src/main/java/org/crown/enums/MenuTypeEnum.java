package org.crown.enums;

import org.crown.common.exception.UnknownEnumException;
import org.crown.framework.enums.IEnum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * 菜单类型枚举
 * </p>
 *
 * @author Caratacus
 */
public enum MenuTypeEnum implements IEnum {

    /**
     * 	init
     */
    START(0),
    /**
     * 目录
     */
    CATALOG(1),
    /**
     * 菜单
     */
    MENU(2),
    /**
     * 按钮
     */
    BUTTON(3);

    @EnumValue
    private final int value;

    MenuTypeEnum(final int value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public int getValue() {
        return this.value;
    }


    @JsonCreator
    public static MenuTypeEnum getEnum(int value) {
        for (MenuTypeEnum menuTypeEnum : MenuTypeEnum.values()) {
            if (menuTypeEnum.getValue() == value) {
                return menuTypeEnum;
            }
        }
        throw new UnknownEnumException("Error: Invalid MenuTypeEnum type value: " + value);
    }
}
