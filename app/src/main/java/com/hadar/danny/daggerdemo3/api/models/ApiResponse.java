package com.hadar.danny.daggerdemo3.api.models;

public class ApiResponse<T> {

    public final Status status;
    public final T data;
    public final Throwable error;

    private ApiResponse(Status status, T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(Status.SUCCESS, data, null);
    }

    public static <T> ApiResponse<T> loading() {
        return new ApiResponse<>(Status.LOADING, null, null);
    }

    public static <T> ApiResponse<T> error(Throwable error) {
        return new ApiResponse<>(Status.ERROR, null, error);
    }

    public enum Status {LOADING, SUCCESS, ERROR}
}
