package com.gudtnzzang.springbootcommunity.board.service;

import com.gudtnzzang.springbootcommunity.board.domain.entity.Comment;
import com.gudtnzzang.springbootcommunity.board.domain.repository.CommentRepository;
import com.gudtnzzang.springbootcommunity.board.domain.repository.PostRepository;
import com.gudtnzzang.springbootcommunity.board.domain.repository.UserRepository;
import com.gudtnzzang.springbootcommunity.board.dto.CommentDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;

    }

    private CommentDto convertEntityToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .userId(comment.getUser().getId())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .build();
    }

    @Transactional
    public List<CommentDto> getCommentList(Long post_id) {
        List<Comment> commentList = commentRepository.findByPost_Id(post_id);
        List<CommentDto> commentDtoList = new ArrayList<CommentDto>();

        for(Comment comment : commentList) {
            commentDtoList.add(this.convertEntityToDto(comment));
        }

        return commentDtoList;
    }

    @Transactional
    public Long saveComment(CommentDto commentDto) {
        Comment newComment = Comment.builder()
                .id(commentDto.getId())
                .post(postRepository.getOne(commentDto.getPostId()))
                .user(userRepository.getOne(commentDto.getUserId()))
                .content(commentDto.getContent())
                .build();

        return commentRepository.save(newComment).getId();
    }
}
