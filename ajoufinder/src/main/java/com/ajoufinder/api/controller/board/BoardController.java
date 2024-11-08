package com.ajoufinder.api.controller.board;

import com.ajoufinder.api.controller.board.dto.request.BoardCreateRequestDto;
import com.ajoufinder.api.controller.board.dto.response.BoardCreateResponseDto;
import com.ajoufinder.api.controller.board.dto.response.BoardDetailInfoResponseDto;
import com.ajoufinder.api.service.board.BoardCommandService;
import com.ajoufinder.api.service.board.BoardQueryService;
import com.ajoufinder.domain.board.entity.Board;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/boards")
@RequiredArgsConstructor
public class BoardController {
  private final BoardQueryService boardQueryService;
  private final BoardCommandService boardCommandService;

  @PostMapping("/lost")
  public ResponseEntity<BoardCreateResponseDto> createLostBoard(@RequestBody @Valid BoardCreateRequestDto requestDto) {
    Board board=boardCommandService.createLostBoard(requestDto);
    BoardCreateResponseDto responseDto = BoardCreateResponseDto.from(board);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @GetMapping("/{boardId}")
  public ResponseEntity<BoardDetailInfoResponseDto> getBoard(@PathVariable Long boardId) {
    BoardDetailInfoResponseDto boardDetailInfoResponseDto=boardQueryService.getBoard(boardId);
    return new ResponseEntity<>(boardDetailInfoResponseDto, HttpStatus.OK);
  }

  @PostMapping("/found")
  public ResponseEntity<BoardCreateResponseDto> createFoundBoard(@RequestBody @Valid BoardCreateRequestDto requestDto) {
    Board board=boardCommandService.createFoundBoard(requestDto);
    BoardCreateResponseDto responseDto = BoardCreateResponseDto.from(board);
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  @DeleteMapping("/{boardId}")
  public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId){
    boardCommandService.deleteBoard(boardId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
