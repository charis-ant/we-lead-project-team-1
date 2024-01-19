package gr.athtech.spring.app.transfer;

public record ApiError(Integer status, String message, String path) {
}
