package com.sparta.tentrillion.stat.controller;

import com.sparta.tentrillion.board.Board;
import com.sparta.tentrillion.global.argumentResolver.annotation.LoginUser;
import com.sparta.tentrillion.stat.dto.StatRequestDto;
import com.sparta.tentrillion.stat.dto.StatResponseDto;
import com.sparta.tentrillion.stat.service.StatService;
import com.sparta.tentrillion.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class StatController {

    private final StatService statService;

    // stat 생성
    @PostMapping("/{boardId}/stats")
    public ResponseEntity<StatResponseDto> creatStat(@Valid @RequestBody StatRequestDto statRequestDto,
                                                     @PathVariable(value = "boardId") Long boardId
                                                     ,
                                                     @LoginUser User user
                                                      ){
        return ResponseEntity.status(HttpStatus.CREATED).body(statService.createStat(statRequestDto,user, boardId));
    }

    // stat 수정
    @PutMapping("/{boardId}/stats/{statId}")
    public ResponseEntity<StatResponseDto> updateStat(@Valid @RequestBody StatRequestDto statRequestDto,
                                                      @PathVariable(value ="boardId") Long boardId,
                                                      @PathVariable(value ="statId") Long statId,
                                                      @LoginUser User user ){
        return ResponseEntity.ok().body(statService.updateStat(statRequestDto,boardId, user ,statId));
    }

    // stat 삭제
    @DeleteMapping("/{boardId}/stats/{statId}")
    public ResponseEntity<StatResponseDto> deleteStat(@PathVariable(value ="boardId") Long boardId,
                                                      @PathVariable (value = "statId") Long statId,
                                                      @LoginUser User user){
        StatResponseDto statResponseDto = statService.deleteStat(boardId, statId, user);
        return ResponseEntity.ok().body(statResponseDto);
    }
    // stat 순서 변경


}