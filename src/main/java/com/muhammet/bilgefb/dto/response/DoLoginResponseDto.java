package com.muhammet.bilgefb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoLoginResponseDto {
    int statusCode; // 200
    String message; // Login successfully
    String token; // bir kişi kimliğini doğruladıktan sonra ona verilen kimlik kartı gibi düşünülebilir.
}
