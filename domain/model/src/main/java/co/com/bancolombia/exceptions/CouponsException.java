package co.com.bancolombia.exceptions;

import co.com.bancolombia.enums.CouponsErrorEnums;

public class CouponsException extends RuntimeException{
    public CouponsException(CouponsErrorEnums error) {
        super(error.name());
    }
}
