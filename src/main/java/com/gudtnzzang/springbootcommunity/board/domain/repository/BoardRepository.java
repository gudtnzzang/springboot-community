package com.gudtnzzang.springbootcommunity.board.domain.repository;

import com.gudtnzzang.springbootcommunity.board.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository
        extends JpaRepository<Board, Long> {
    List<Board> findByTitleContaining(String keyword);
}
