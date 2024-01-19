package gr.athtech.spring.app.transfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class ApiResponse<T> implements Serializable {
    String transaction = UUID.randomUUID().toString().toUpperCase();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    Date created = new Date();

    T data;
    ApiError apiError;
}
