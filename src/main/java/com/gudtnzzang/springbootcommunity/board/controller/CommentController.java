package com.gudtnzzang.springbootcommunity.board.controller;

import com.gudtnzzang.springbootcommunity.board.domain.entity.User;
import com.gudtnzzang.springbootcommunity.board.dto.CommentDto;
import com.gudtnzzang.springbootcommunity.board.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/{postId}")
    public String writeComment(@PathVariable Long postId, CommentDto commentDto, @AuthenticationPrincipal User currentUser) {

        commentDto.setPostId(postId);
        commentDto.setAuthor(currentUser.getEmail());
        commentService.saveComment(commentDto);
        return "redirect:/post/" + postId;
    }

    @PutMapping("/comment")
    public String editComment(CommentDto commentDto) {
        commentService.updateComment(commentDto);
        return "redirect:/post/" + commentDto.getPostId();
    }

    @DeleteMapping("/comment/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/post/" + postId;
    }
}
