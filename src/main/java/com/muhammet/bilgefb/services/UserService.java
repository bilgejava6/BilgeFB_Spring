package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.dto.request.DoLoginRequestDto;
import com.muhammet.bilgefb.dto.request.RegisterRequestDto;
import com.muhammet.bilgefb.dto.response.DoLoginResponseDto;
import com.muhammet.bilgefb.exceptions.BilgeFbException;
import com.muhammet.bilgefb.exceptions.ErrorType;
import com.muhammet.bilgefb.mapper.IUserMapper;
import com.muhammet.bilgefb.repository.IUserRepository;
import com.muhammet.bilgefb.repository.entity.User;
import com.muhammet.bilgefb.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository repository;
    private final JwtTokenManager jwtTokenManager;
    public void save(RegisterRequestDto dto) {
        Optional<User> userOptional = repository.findOptionalByUsername(dto.getUsername());
        if(userOptional.isPresent()) // Eğer bu kullanıcı adı daha önce kayıt edilmiş ise hata fırlat
            throw new BilgeFbException(ErrorType.REGISTER_KULLANICIADI_KAYITLI);
        User user = IUserMapper.INSTANCE.userFromDto(dto);
        repository.save(user);
    }

    public DoLoginResponseDto doLogin(DoLoginRequestDto dto) {
        Optional<User> userOptional = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if(userOptional.isEmpty()) // Eğer bu kullanıcı adı ve şifre ile kayıtlı bir kullanıcı yok ise hata fırlat
            throw new BilgeFbException(ErrorType.INVALID_USERNAME_AND_PASSWORD);
        Optional<String> token = jwtTokenManager.createToken(userOptional.get().getId());
        if(token.isEmpty()) // eğer token oluşturulamaz ise hata fırlat
            throw new BilgeFbException(ErrorType.TOKEN_NOT_CREATE);
        return DoLoginResponseDto.builder()
                .token(token.get())
                .statusCode(200)
                .message("Login successfully").build();
    }
}
