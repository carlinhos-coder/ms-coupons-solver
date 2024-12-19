package co.com.bancolombia.enums;

import lombok.Getter;

@Getter
public enum CouponsEnum {
    ZERO(0),
    ZERO_FLOAT(0f);

    private final int intValue;
    private final float floatValue;

    CouponsEnum(int intValue) {
        this.intValue = intValue;
        this.floatValue = 0f;
    }

    CouponsEnum(float floatValue) {
        this.intValue = 0;
        this.floatValue = floatValue;
    }

}
