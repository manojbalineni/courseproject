package com.udemy.learning.accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(
        name = "Response",
        description = "Schema to hold successful response"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    @Schema(
            description = "Status Code",
            example = "200"
    )
    private String statusCode;
    @Schema(
            description = "Status Message",
            example = "Request Processed Successfully"
    )
    private String statusMsg;
}
