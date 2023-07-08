package com.muhammet.bilgefb.dto.response;

import com.muhammet.bilgefb.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetProfileResponseDto {
    int statusCode;
    String message;
    User data;
}
