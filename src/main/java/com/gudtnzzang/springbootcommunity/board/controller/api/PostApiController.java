package com.gudtnzzang.springbootcommunity.board.controller.api;

import com.gudtnzzang.springbootcommunity.board.dto.CommentDto;
import com.gudtnzzang.springbootcommunity.board.dto.PostDto;
import com.gudtnzzang.springbootcommunity.board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> saveComment(@RequestBody PostDto postDto) {
        postService.savePost(postDto);
        return new ResponseEntity("save successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateComment(@RequestBody PostDto postDto) {
        postService.updatePost(postDto);
        return new ResponseEntity("update successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("success");
    }
}

