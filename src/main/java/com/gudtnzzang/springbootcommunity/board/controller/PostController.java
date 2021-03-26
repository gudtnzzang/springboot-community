package com.gudtnzzang.springbootcommunity.board.controller;

import com.gudtnzzang.springbootcommunity.board.domain.entity.User;
import com.gudtnzzang.springbootcommunity.board.dto.CommentDto;
import com.gudtnzzang.springbootcommunity.board.dto.PostDto;
import com.gudtnzzang.springbootcommunity.board.service.CommentService;
import com.gudtnzzang.springbootcommunity.board.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<PostDto> postDtoList = postService.getBoardList(pageNum);
        Integer[] pageList = postService.getPageList(pageNum);

        model.addAttribute("postList", postDtoList);
        model.addAttribute("pageList", pageList);

        return "board/list.html";
    }

    @GetMapping("/post")
    public String displayPostWrite() {
        return "board/post.html";
    }

    @PostMapping("/post")
    public String writePost(PostDto postDto) {
        postService.savePost(postDto);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String displayPostDetail(@PathVariable("id") Long id, @AuthenticationPrincipal User currentUser, Model model) {
        PostDto postDto = postService.getPost(id);
        List<CommentDto> commentDtoList = commentService.getCommentList(id);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("post", postDto);
        model.addAttribute("commentList", commentDtoList);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{id}")
    public String displayPostEdit(@PathVariable("id") Long id, Model model) {
        PostDto postDto = postService.getPost(id);

        model.addAttribute("post", postDto);
        return "board/edit.html";
    }

    @PutMapping("/post/edit/{id}")
    public String editPost(PostDto postDto) {
        postService.savePost(postDto);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "redirect:/";
    }

    @GetMapping("/post/search")
    public String searchPost(@RequestParam(value="keyword") String keyword, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model) {
        List<PostDto> postDtoList = postService.getSearchResult(keyword, pageNum);
        Integer[] pageList = postService.getSearchResultPageList(pageNum, postService.getResultBoardCount(keyword));

        model.addAttribute("keyword", keyword);
        model.addAttribute("postList", postDtoList);
        model.addAttribute("pageList", pageList);
        return "board/list.html";
    }
}
