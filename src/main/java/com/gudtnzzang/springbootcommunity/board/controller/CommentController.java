package com.gudtnzzang.springbootcommunity.board.controller;

import com.gudtnzzang.springbootcommunity.board.domain.entity.User;
import com.gudtnzzang.springbootcommunity.board.dto.CommentDto;
import com.gudtnzzang.springbootcommunity.board.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/{postId}")
    public String writeComment(@PathVariable(name = "postId") Long postId, CommentDto commentDto, @AuthenticationPrincipal User currentUser) {

        commentDto.setPostId(postId);
        commentDto.setAuthor(currentUser.getEmail());
        commentService.saveComment(commentDto);
        return "redirect:/post/" + postId;
    }
}
