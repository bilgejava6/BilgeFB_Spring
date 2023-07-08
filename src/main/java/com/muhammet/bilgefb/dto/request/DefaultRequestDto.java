package com.muhammet.bilgefb.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DefaultRequestDto {
    @NotEmpty
    String token;
    int page;
    int size;
}
