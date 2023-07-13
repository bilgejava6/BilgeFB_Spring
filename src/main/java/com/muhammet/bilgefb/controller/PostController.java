package com.muhammet.bilgefb.controller;
import com.cloudinary.utils.ObjectUtils;
import com.muhammet.bilgefb.dto.request.CreatePostRequestDto;
import com.muhammet.bilgefb.dto.request.DefaultRequestDto;
import com.muhammet.bilgefb.dto.response.CreatePostResponseDto;
import com.muhammet.bilgefb.dto.response.GetAllPostsResponseDto;
import com.muhammet.bilgefb.dto.response.UploadFileResponseDto;
import com.muhammet.bilgefb.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.muhammet.bilgefb.constants.RestApiList.*;
import com.cloudinary.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(POST)
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/uploadfile",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin("*")
    public ResponseEntity<UploadFileResponseDto> uploadPostImage(@RequestParam(value = "file") MultipartFile file){
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
        }   catch (Exception e){
            System.out.println("Hata oldu....: "+ e.toString());
        }
        return ResponseEntity.ok(UploadFileResponseDto.builder()
                .message("Başarılı")
                .statusCode(200)
                .url(resimURL)
                .build());
    }


    @PostMapping(CREATE_POST)
    @CrossOrigin("*")
    public ResponseEntity<CreatePostResponseDto> createPost(@RequestBody @Valid CreatePostRequestDto dto){
        return ResponseEntity.ok(
                postService.createPost(dto)
        );
    }

    //@GetMapping("/testUploadImage")
    public ResponseEntity<Void> testUploadImage(){
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "di8byecer",
                "api_key", "572154474642921",
                "api_secret", "DmSNbqFIPpknd4R-ug7PegoPwzE"));
        try{
            UUID uuid = UUID.randomUUID();
        Map result =  cloudinary.uploader()
                    .upload(new File("C:\\Users\\MuhammetAli\\Downloads\\Untitled.png"),
                            ObjectUtils.asMap(uuid, "test" ));
        System.out.println(result.get("url"));
        }catch (Exception e){

        }

        return ResponseEntity.ok().build();
    }

    @PostMapping(GET_POSTS)
    @CrossOrigin("*")
    public ResponseEntity<GetAllPostsResponseDto> getAllPosts(@RequestBody @Valid DefaultRequestDto dto){
        return ResponseEntity.ok(
                postService.getAllPosts(dto)
        );
    }
}
