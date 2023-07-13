package com.muhammet.bilgefb.controller;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.muhammet.bilgefb.dto.request.DefaultRequestDto;
import com.muhammet.bilgefb.dto.request.DoLoginRequestDto;
import com.muhammet.bilgefb.dto.request.RegisterRequestDto;
import com.muhammet.bilgefb.dto.request.SaveProfileRequestDto;
import com.muhammet.bilgefb.dto.response.*;
import com.muhammet.bilgefb.repository.entity.User;
import com.muhammet.bilgefb.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping(LOGOUT)
    @CrossOrigin("*")
    public ResponseEntity<DoLogoutResponseDto> doLogout(@RequestBody @Valid DefaultRequestDto dto){
        return ResponseEntity.ok(userService.doLogout(dto));
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

    @PostMapping(NEWUSERLIST)
    @CrossOrigin("*")
    public ResponseEntity<GetNewUserListResponseDto> getNewUserList(@RequestBody @Valid DefaultRequestDto dto){
        return ResponseEntity.ok(userService.getNewUserList(dto));
    }

    @PostMapping(value = "/uploadfile",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin("*")
    public ResponseEntity<UploadFileResponseDto> uploadProfileImage(@RequestParam(value = "file") MultipartFile file, Long userid){
        String resimURL = "";
        try{
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "di8byecer",
                    "api_key", "572154474642921",
                    "api_secret", "DmSNbqFIPpknd4R-ug7PegoPwzE"));
            UUID uuid = UUID.randomUUID();

            Map result =  cloudinary.uploader()
                    .upload(file.getBytes(),
                            ObjectUtils.asMap(uuid, "test" ));
            resimURL = result.get("url").toString();
            Optional<User> user = userService.findById(userid);
            if(user.isPresent()){
                user.get().setUserimage(resimURL);
                userService.save(user.get());
            }
        }   catch (Exception e){
            System.out.println("Hata oldu....: "+ e.toString());
        }
        return ResponseEntity.ok(UploadFileResponseDto.builder()
                .message("Başarılı")
                .statusCode(200)
                .url(resimURL)
                .build());
    }

}
