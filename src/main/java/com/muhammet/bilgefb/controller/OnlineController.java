package com.muhammet.bilgefb.controller;
import com.muhammet.bilgefb.dto.request.DefaultRequestDto;
import com.muhammet.bilgefb.dto.response.OnlineUsersResponseDto;
import com.muhammet.bilgefb.services.OnLineService;
import com.muhammet.bilgefb.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.muhammet.bilgefb.constants.RestApiList.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(ONLINE)
public class OnlineController {

    private final OnLineService onLineService;
    private final UserService userService;

    @PostMapping(ONLINE_USERS)
    @CrossOrigin("*")
    public ResponseEntity<OnlineUsersResponseDto> getOnlineUsers(@RequestBody @Valid DefaultRequestDto dto){
        return ResponseEntity.ok(onLineService.getOnlineUsers(dto));
    }
}
