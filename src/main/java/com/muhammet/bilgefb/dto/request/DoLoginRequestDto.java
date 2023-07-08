package com.muhammet.bilgefb.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoLoginRequestDto {
    @NotEmpty
    @Size(min = 3, max = 60)
    String username;
    @NotEmpty
    String password;
}
