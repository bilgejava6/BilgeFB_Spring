package com.muhammet.bilgefb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UploadFileResponseDto {
    int statusCode;
    String message;
    String url;
}
