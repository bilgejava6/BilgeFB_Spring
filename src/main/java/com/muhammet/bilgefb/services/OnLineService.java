package com.muhammet.bilgefb.services;

import com.muhammet.bilgefb.dto.request.DefaultRequestDto;
import com.muhammet.bilgefb.dto.response.OnlineUsersResponseDto;
import com.muhammet.bilgefb.exceptions.BilgeFbException;
import com.muhammet.bilgefb.exceptions.ErrorType;
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
public class OnLineService {
    private final IOnlineRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final IUserRepository userRepository;
    public OnlineUsersResponseDto getOnlineUsers(DefaultRequestDto dto) {
        Optional<Long> userId = jwtTokenManager.getByIdFromToken(dto.getToken());
        if(userId.isEmpty())
            throw new BilgeFbException(ErrorType.INVALID_TOKEN);
        /**
         *  DİKKAT!!!!
         *  burada online olan kullanıcılara ait kayıtları buluyoruz ancak burada kullanıcılarıra ait
         *  diğer bilgiler bulunmamakta. Bu nedenle online içinde userid bilgilerini alarak user tablosundan
         *  bu kullanıcıların bilgilerini çekmek gereklidir.
         *  İşte tam burada doğru kodlama devreye girecek. bu nedenle önce yanlış olanı görelim.         *
         */

        /**
         * 1- YANLIŞ YAKLAŞIM, bu kullanımda uygulama bir süre sonra yavaşlayacaktır.
         * sebebi ise,
         *
         * 1.500 kişi online olsun, burada her bir kişi için varlığını sorgulamak üzere
         * db ye istek atıyorsunuz, bu işlem genellikle ne kadar hızlı olursa olsun sorundur.
         * diyelim ki 1ms-5ms sürsün. böyle bir durum da 1.500 kişi için 1.500*5ms = 7.500ms
         *  List<Online> onlineUsers = repository.findAllByIsonlineTrue();
         *         List<VwUserProfile> vwUsers = new ArrayList<>();
         *  onlineUsers.forEach(online->{
         *             Optional<User> user = userRepository.findById(online.getId());
         *             user.ifPresent(value->{
         *                 vwUsers.add(
         *                         VwUserProfile.builder()
         *                                 .profileUrl(value.getUserimage()==null ? "/images/users/user-11.jpg" : value.getUserimage())
         *                                 .username(value.getUsername())
         *                                 .userid(value.getId())
         *                                 .build()
         *                 );
         *             });
         *         });
         * 2- DOĞRU YAKLAŞIM,
         *  öncelikle user tablosundan alınmak istenilen kullanıcıların id listesi online tablosundan çekilir.
         *
         */
        List<Long> onlineUserIds = repository.getAllOnlineUserIds();
        List<User> onlineUserList = userRepository.findAllById(onlineUserIds);
        List<VwUserProfile> vwUsers = new ArrayList<>();
        onlineUserList.forEach(user->{
            vwUsers.add(
                    VwUserProfile.builder()
                    .profileUrl(user.getUserimage()==null ? "/images/users/user-11.jpg" : user.getUserimage())
                    .username(user.getUsername())
                    .userid(user.getId())
                    .build()
            );
        });
        return OnlineUsersResponseDto.builder()
                .data(vwUsers)
                .statusCode(200)
                .message("Online kullanıcılar başarıyla getirildi")
                .build();
    }
}
