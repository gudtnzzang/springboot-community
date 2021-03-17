package com.gudtnzzang.springbootcommunity.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String username;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public CommentDto(Long id, String username, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
