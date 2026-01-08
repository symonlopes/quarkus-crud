package org.acme.error;

import lombok.Getter;

@Getter
public class CodedException extends RuntimeException {

    private final String code;

    public CodedException(String code, String message) {
        super(message);
        this.code = code;
    }
}
