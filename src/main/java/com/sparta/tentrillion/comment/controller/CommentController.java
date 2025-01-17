package com.sparta.tentrillion.comment.controller;

import com.sparta.tentrillion.aop.Envelop;
import com.sparta.tentrillion.comment.dto.CommentRequestDto;
import com.sparta.tentrillion.comment.dto.CommentResponseDto;
import com.sparta.tentrillion.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @Envelop("댓글 생성입니다.")
    @PostMapping("/cards/{cardId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable(value = "cardId") Long cardId,
                                                            @RequestBody CommentRequestDto commentRequestDto,
                                                            @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(cardId,commentRequestDto,userDetails.getUsername()));
    }

    @Envelop("댓글 조회입니다")
    @GetMapping("/cards/{cardId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable(value = "cardId") Long cardId){
        return  ResponseEntity.ok().body(commentService.getComment(cardId));
    }
}
