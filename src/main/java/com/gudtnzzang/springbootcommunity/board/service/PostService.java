package com.gudtnzzang.springbootcommunity.board.service;

import com.gudtnzzang.springbootcommunity.board.domain.entity.Post;
import com.gudtnzzang.springbootcommunity.board.domain.repository.PostRepository;
import com.gudtnzzang.springbootcommunity.board.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4;       // 한 페이지에 존재하는 게시글 수

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private PostDto convertEntityToDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .build();
    }

    @Transactional
    public Long savePost(PostDto postDto) {
        return postRepository.save(postDto.toEntity()).getId();
    }


    @Transactional
    public List<PostDto> getBoardList(Integer pageNum) {
        Page<Post> page = postRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));

        List<Post> postList = page.getContent();
        List<PostDto> postDtoList = new ArrayList<>();

        for (Post post : postList) {
            postDtoList.add(this.convertEntityToDto(post));
        }

        return postDtoList;
    }

    @Transactional
    public List<PostDto> getSearchResult(String keyword, Integer pageNum) {
        Page<Post> page = postRepository.findByTitleContaining(keyword, PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
        List<Post> postList = page.getContent();

        List<PostDto> postDtoList = new ArrayList<>();

        for(Post post : postList) {
            postDtoList.add(this.convertEntityToDto(post));
        }

        return postDtoList;
    }

    @Transactional
    public Long getBoardCount() {
        return postRepository.count();
    }

    @Transactional
    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 갯수
        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

    @Transactional
    public Double getResultBoardCount(String keyword) {
        return Double.valueOf(postRepository.findByTitleContaining(keyword).size());
    }

    @Transactional
    public Integer[] getSearchResultPageList(Integer curPageNum, Double postsCount) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int)(Math.ceil((postsCount/PAGE_POST_COUNT)));

        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }

    @Transactional
    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id).get();

        PostDto postDto = PostDto.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .build();
        return postDto;
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
