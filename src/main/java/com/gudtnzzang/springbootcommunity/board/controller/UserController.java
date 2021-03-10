package com.gudtnzzang.springbootcommunity.board.controller;

import com.gudtnzzang.springbootcommunity.board.dto.UserDto;
import com.gudtnzzang.springbootcommunity.board.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/signup")
    public String displaySignup() {
        return "board/signup.html";
    }
    @PostMapping("/signup")
    public String signup(UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String displayLogin() {
        return "board/login.html";
    }

    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "myinfo";
    }

    @GetMapping("/admin")
    public String dispAdmin() {
        return "admin";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
