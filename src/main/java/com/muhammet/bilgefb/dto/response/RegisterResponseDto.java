package com.muhammet.bilgefb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterResponseDto {
    int statusCode; // 200
    String message; // Kayıt başarılı
}
