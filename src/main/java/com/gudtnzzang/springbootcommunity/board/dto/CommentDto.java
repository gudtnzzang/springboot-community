package com.gudtnzzang.springbootcommunity.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private Long postId;
    private String author;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public CommentDto(Long id, Long postId, String author, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.postId = postId;
        this.author = author;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
