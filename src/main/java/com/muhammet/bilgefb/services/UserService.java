package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.dto.request.DefaultRequestDto;
import com.muhammet.bilgefb.dto.request.DoLoginRequestDto;
import com.muhammet.bilgefb.dto.request.RegisterRequestDto;
import com.muhammet.bilgefb.dto.request.SaveProfileRequestDto;
import com.muhammet.bilgefb.dto.response.*;
import com.muhammet.bilgefb.exceptions.BilgeFbException;
import com.muhammet.bilgefb.exceptions.ErrorType;
import com.muhammet.bilgefb.mapper.IUserMapper;
import com.muhammet.bilgefb.repository.IOnlineRepository;
import com.muhammet.bilgefb.repository.IUserRepository;
import com.muhammet.bilgefb.repository.entity.Online;
import com.muhammet.bilgefb.repository.entity.User;
import com.muhammet.bilgefb.repository.view.VwUserProfile;
import com.muhammet.bilgefb.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository repository;
    private final IOnlineRepository onlineRepository;
    private final JwtTokenManager jwtTokenManager;
    public void save(User user){
        repository.save(user);
    }
    public void save(RegisterRequestDto dto) {
        Optional<User> userOptional = repository.findOptionalByUsername(dto.getUsername());
        if(userOptional.isPresent()) // Eğer bu kullanıcı adı daha önce kayıt edilmiş ise hata fırlat
            throw new BilgeFbException(ErrorType.REGISTER_KULLANICIADI_KAYITLI);
        User user = IUserMapper.INSTANCE.userFromDto(dto);
        user.setMemberdate(System.currentTimeMillis());
        repository.save(user);
    }

    public DoLoginResponseDto doLogin(DoLoginRequestDto dto) {
        Optional<User> userOptional = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if(userOptional.isEmpty()) // Eğer bu kullanıcı adı ve şifre ile kayıtlı bir kullanıcı yok ise hata fırlat
            throw new BilgeFbException(ErrorType.INVALID_USERNAME_AND_PASSWORD);
        Optional<String> token = jwtTokenManager.createToken(userOptional.get().getId());
        if(token.isEmpty()) // eğer token oluşturulamaz ise hata fırlat
            throw new BilgeFbException(ErrorType.TOKEN_NOT_CREATE);
        /**
         * Burada kişi baraşılı bir şekilde giriş yaptığı için online durumunu true olarak kaydediyoruz.
         * Ancak burada dikkat edilmesi gereken şey, kullanıcı daha önce giriş yapmış ise  sadece durumunu
         * true yapmamız yeterli iken, ilk defa giriş yapıyorsa kayıt etmemiz gerekiyor.
         */
        Optional<Online> online = onlineRepository.findOptionalByUserid(userOptional.get().getId());
        if(online.isEmpty()){
            onlineRepository.save(
                    Online.builder()
                            .lastonline(System.currentTimeMillis())
                            .isonline(true)
                            .userid(userOptional.get().getId())
                            .build()
            );
        }else{
            online.get().setIsonline(true);
            online.get().setLastonline(System.currentTimeMillis());
            onlineRepository.save(online.get());
        }
        return DoLoginResponseDto.builder()
                .token(token.get())
                .statusCode(200)
                .message("Login successfully").build();
    }

    public DoLogoutResponseDto doLogout(DefaultRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getByIdFromToken(dto.getToken());
        if(userId.isEmpty())
            throw new BilgeFbException(ErrorType.INVALID_TOKEN);
        Optional<Online> online = onlineRepository.findOptionalByUserid(userId.get());
        online.ifPresent(value->{
            value.setIsonline(false);
            onlineRepository.save(value);
        });
        return DoLogoutResponseDto.builder()
                .message("Başarıyla çıkış yapıldı")
                .statusCode(200)
                .build();
    }

    public GetProfileByTokenResponseDto getProfileByToken(String token){
        Optional<Long> userId = jwtTokenManager.getByIdFromToken(token);
        if(userId.isEmpty())
            throw new BilgeFbException(ErrorType.INVALID_TOKEN);
        Optional<User> userOptional = repository.findById(userId.get());
        if(userOptional.isEmpty())
            throw new BilgeFbException(ErrorType.ID_NOT_FOUND);
        VwUserProfile vwUserProfile = VwUserProfile.builder()
                .profileUrl(userOptional.get().getUserimage()==null ? "/images/users/user-1.jpg" : userOptional.get().getUserimage())
                .username(userOptional.get().getUsername())
                .userid(userOptional.get().getId())
                .followerCount(12435) // TODO: daha sonra bu follower servis üzerinden alnımalıdır.
                .build();
        return GetProfileByTokenResponseDto.builder()
                .data(vwUserProfile)
                .message("Kullanıcı bilgileri başarıyla getirildi")
                .statusCode(200)
                .build();
    }

    public GetProfileResponseDto getProfile(String token) {
        Optional<Long> userId = jwtTokenManager.getByIdFromToken(token);
        if(userId.isEmpty())
            throw new BilgeFbException(ErrorType.INVALID_TOKEN);
        Optional<User> userOptional = repository.findById(userId.get());
        if(userOptional.isEmpty())
            throw new BilgeFbException(ErrorType.ID_NOT_FOUND);
        return GetProfileResponseDto.builder()
                .data(userOptional.get())
                .message("Kullanıcı bilgileri başarıyla getirildi")
                .statusCode(200)
                .build();
    }

    public SaveProfileResponseDto saveProfile(SaveProfileRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getByIdFromToken(dto.getToken());
        if(userId.isEmpty())
            throw new BilgeFbException(ErrorType.INVALID_TOKEN);
        Optional<User> userOptional = repository.findById(userId.get());
        if(userOptional.isEmpty())
            throw new BilgeFbException(ErrorType.ID_NOT_FOUND);
        User user = dto.getUser();
        if(!user.getId().equals(userId.get()))
            throw new BilgeFbException(ErrorType.INVALID_TOKEN);
        repository.save(user);
        return SaveProfileResponseDto.builder()
                .message("Kullanıcı bilgileri başarıyla güncellendi")
                .statusCode(200)
                .build();
    }

    public GetNewUserListResponseDto getNewUserList(DefaultRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getByIdFromToken(dto.getToken());
        if(userId.isEmpty())
            throw new BilgeFbException(ErrorType.INVALID_TOKEN);
        List<User> userList = repository.findTop5ByOrderByMemberdateDesc();
        List<VwUserProfile> newUserList = new ArrayList<>();
        userList.forEach(x->{
            newUserList.add(
                    VwUserProfile.builder()
                            .userid(x.getId())
                            .username(x.getUsername())
                            .profileUrl(x.getUserimage()==null ? "/images/users/user-1.jpg" : x.getUserimage())
                            .build()
            );
        });
        return GetNewUserListResponseDto.builder()
                .data(newUserList)
                .message("Yeni kullanıcılar başarıyla getirildi")
                .statusCode(200)
                .build();
    }

    public Optional<User> findById(Long id){
        return repository.findById(id);
    }

}
