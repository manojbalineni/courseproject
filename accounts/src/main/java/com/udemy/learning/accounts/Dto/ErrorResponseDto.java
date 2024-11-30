package com.udemy.learning.accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to represent Error Response"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API invoked by customer"
    )
    private String apiPath;
    @Schema(
            description = "Error Code "
    )
    private HttpStatus errorCode;
    @Schema(
            description = "Error Message"
    )
    private String errorMessage;
    @Schema(
            description = "Error Time"
    )
    private LocalDateTime errorTime;
}
