package com.muhammet.bilgefb.controller;
import com.muhammet.bilgefb.dto.request.DoLoginRequestDto;
import com.muhammet.bilgefb.dto.request.RegisterRequestDto;
import com.muhammet.bilgefb.dto.response.DoLoginResponseDto;
import com.muhammet.bilgefb.dto.response.RegisterResponseDto;
import com.muhammet.bilgefb.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.muhammet.bilgefb.constants.RestApiList.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
    private final UserService userService;
    /**
     * fetch()
     * .then() olumlu bir sonuç dönmüş se işlem
     * .catch() olumsuz bir sonuç dönmüş se işlem
     * DİKKAT : api methodlarınız asla tek bir değer dönmesin, Boolean gibi değerler de dönmesin, mutlaka
     * bir entity olarak dönün ve bu entity içinde bir mesaj - status code ve bir data döndürün.
     */
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok(RegisterResponseDto.builder()
                        .statusCode(200)
                        .message("User created successfully").build());
    }

    @PostMapping(LOGIN)
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(userService.doLogin(dto));
    }
}
