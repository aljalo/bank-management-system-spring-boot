package org.wscict.bank.payload;

import java.time.LocalDateTime;
import java.util.List;

public class PaginationResponse<T> {

    private final boolean success;
    private final List<T> data;

    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;

    private final LocalDateTime timestamp;

    public PaginationResponse(
            boolean success,
            List<T> data,
            int page,
            int size,
            long totalElements,
            int totalPages) {

        this.success = success;
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSuccess() {
        return success;
    }

    public List<T> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
