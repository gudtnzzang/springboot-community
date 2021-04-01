package com.gudtnzzang.springbootcommunity.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Date timestamp;
}
