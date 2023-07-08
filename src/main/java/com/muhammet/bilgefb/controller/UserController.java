package com.muhammet.bilgefb.controller;
import com.muhammet.bilgefb.dto.request.DefaultRequestDto;
import com.muhammet.bilgefb.dto.request.DoLoginRequestDto;
import com.muhammet.bilgefb.dto.request.RegisterRequestDto;
import com.muhammet.bilgefb.dto.request.SaveProfileRequestDto;
import com.muhammet.bilgefb.dto.response.*;
import com.muhammet.bilgefb.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @CrossOrigin("*")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok(RegisterResponseDto.builder()
                        .statusCode(200)
                        .message("User created successfully").build());
    }

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        return ResponseEntity.ok(userService.doLogin(dto));
    }

    @PostMapping(GET_PROFILE_BY_TOKEN)
    @CrossOrigin("*")
    public ResponseEntity<GetProfileByTokenResponseDto> getProfileByToken(@RequestBody @Valid DefaultRequestDto dto){
          return ResponseEntity.ok(userService.getProfileByToken(dto.getToken()));
    }

    @PostMapping(GET_PROFILE)
    @CrossOrigin("*")
    public ResponseEntity<GetProfileResponseDto> getProfile(@RequestBody @Valid DefaultRequestDto dto){
        return ResponseEntity.ok(userService.getProfile(dto.getToken()));
    }

    @PostMapping(SAVE_PROFILE)
    @CrossOrigin("*")
    public ResponseEntity<SaveProfileResponseDto> saveProfile(@RequestBody @Valid SaveProfileRequestDto dto){
        return ResponseEntity.ok(userService.saveProfile(dto));
    }

}
