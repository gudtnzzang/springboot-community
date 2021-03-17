package com.gudtnzzang.springbootcommunity.board.domain.repository;

import com.gudtnzzang.springbootcommunity.board.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_Id(@Param(value = "post_id") Long post_id);
}
