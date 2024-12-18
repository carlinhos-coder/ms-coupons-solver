package co.com.bancolombia.exceptions;

import co.com.bancolombia.CouponsErrorEnums;

public class CouponsException extends RuntimeException{
    public CouponsException(CouponsErrorEnums error) {
        super(error.name());
    }
}
