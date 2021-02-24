package com.zup.cdc.shared.errors;

public class SimpleErrorDTO {
    private String message;

    public SimpleErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
