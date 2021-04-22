package com.gudtnzzang.springbootcommunity.board.controller.api;

import com.gudtnzzang.springbootcommunity.board.dto.PostDto;
import com.gudtnzzang.springbootcommunity.board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostApiController {
    private PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long postId) {
        return new ResponseEntity(postService.getPost(postId), HttpStatus.OK);
    }
}

