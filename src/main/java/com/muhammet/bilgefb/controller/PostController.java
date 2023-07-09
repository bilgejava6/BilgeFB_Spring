package com.muhammet.bilgefb.controller;
import com.cloudinary.utils.ObjectUtils;
import com.muhammet.bilgefb.dto.request.CreatePostRequestDto;
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

    @PostMapping(value = CREATE_POST,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin("*")
    public ResponseEntity<?> createPost(@RequestBody MultipartFile imageFile){
        String resimURL = "";
        try{
            Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "di8byecer",
                    "api_key", "572154474642921",
                    "api_secret", "DmSNbqFIPpknd4R-ug7PegoPwzE"));
            UUID uuid = UUID.randomUUID();

            Map result =  cloudinary.uploader()
                    .upload(imageFile.getBytes(),
                            ObjectUtils.asMap(uuid, "test" ));
            resimURL = result.get("url").toString();
        }   catch (Exception e){
            System.out.println("Hata oldu....: "+ e.toString());
        }
        return ResponseEntity.ok(resimURL);
    }

    @GetMapping("/testUploadImage")
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


}
