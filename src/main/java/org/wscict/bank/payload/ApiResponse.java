package org.wscict.bank.payload;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final LocalDateTime timestamp;
    private final String message;

    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.message = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
