package com.gudtnzzang.springbootcommunity.board.service;

import com.gudtnzzang.springbootcommunity.board.domain.entity.Comment;
import com.gudtnzzang.springbootcommunity.board.domain.repository.CommentRepository;
import com.gudtnzzang.springbootcommunity.board.dto.CommentDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    private CommentDto convertEntityToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .username(comment.getUser().getUsername())
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
}
