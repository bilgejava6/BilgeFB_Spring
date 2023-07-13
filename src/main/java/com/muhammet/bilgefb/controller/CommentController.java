package com.muhammet.bilgefb.controller;

import com.muhammet.bilgefb.dto.request.CreateCommentRequestDto;
import com.muhammet.bilgefb.dto.request.GetAllCommentByPostIdRequestDto;
import com.muhammet.bilgefb.dto.response.CreateCommentResponseDto;
import com.muhammet.bilgefb.repository.view.VwComment;
import com.muhammet.bilgefb.services.CommnetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muhammet.bilgefb.constants.RestApiList.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT)
public class CommentController {

    private final CommnetService commnetService;
    @PostMapping("/getallcommentbypostid")
    @CrossOrigin("*")
    public ResponseEntity<List<VwComment>> getAllCommentsByPostId(@RequestBody @Valid GetAllCommentByPostIdRequestDto dto){
        return ResponseEntity.ok(commnetService.findAllByPostId(dto.getPostid()));
    }

    @PostMapping("/createcomment")
    @CrossOrigin("*")
    public ResponseEntity<CreateCommentResponseDto> createComment(@RequestBody @Valid CreateCommentRequestDto dto) {
        return ResponseEntity.ok(commnetService.createComment(dto));
    }
}
