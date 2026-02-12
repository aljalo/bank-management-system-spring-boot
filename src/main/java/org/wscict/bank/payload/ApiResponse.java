package org.wscict.bank.payload;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final LocalDateTime timestamp;

    public ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
