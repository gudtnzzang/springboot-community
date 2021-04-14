package com.gudtnzzang.springbootcommunity.board.controller.api;

import com.gudtnzzang.springbootcommunity.board.domain.entity.Comment;
import com.gudtnzzang.springbootcommunity.board.dto.CommentDto;
import com.gudtnzzang.springbootcommunity.board.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentApiController {
    private CommentService commentService;

    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentList(@PathVariable Long postId) {
        List<CommentDto> commentDtoList = commentService.getCommentList(postId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveComment(@RequestBody CommentDto commentDto) {
        commentService.saveComment(commentDto);
        return new ResponseEntity("save successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateComment(@RequestBody CommentDto commentDto) {
        commentService.updateComment(commentDto);
        return new ResponseEntity("update successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("success");
    }
}
