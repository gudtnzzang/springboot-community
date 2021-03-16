package com.gudtnzzang.springbootcommunity.board.service;

import com.gudtnzzang.springbootcommunity.board.domain.entity.Board;
import com.gudtnzzang.springbootcommunity.board.domain.repository.BoardRepository;
import com.gudtnzzang.springbootcommunity.board.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    private BoardDto convertEntityToDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }


    @Transactional
    public List<BoardDto> getBoardList(Integer pageNum) {
        Page<Board> page = boardRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<Board> boardList = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList) {
            boardDtoList.add(this.convertEntityToDto(board));
        }

        return boardDtoList;
    }

    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    @Transactional
    public Integer[] getPageList(Integer pageNum) {
        Integer[] i = new Integer[]{1,2,3};
        return i;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
        return boardDto;
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public List<BoardDto> getSearchResult(String keyword) {
        List<Board> boardList = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            boardDtoList.add(BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build()
            );
        }

        return boardDtoList;
    }
}
