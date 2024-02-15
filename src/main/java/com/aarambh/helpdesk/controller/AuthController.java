package com.aarambh.helpdesk.controller;

import com.aarambh.helpdesk.dto.request.UserSigninReqDTO;
import com.aarambh.helpdesk.dto.response.BasicResDTO;
import com.aarambh.helpdesk.dto.response.ResponseDTO;
import com.aarambh.helpdesk.dto.response.TokenResDTO;
import com.aarambh.helpdesk.service.UserService;
import com.aarambh.helpdesk.service.implementation.TokenServiceImpl;
import com.aarambh.helpdesk.util.CommonConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenServiceImpl tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, TokenServiceImpl tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("login")
    ResponseDTO<TokenResDTO> login(@RequestBody @Valid UserSigninReqDTO userSigninReqDTO) {
        System.out.println(userSigninReqDTO);
        var credentials = new UsernamePasswordAuthenticationToken(userSigninReqDTO.username(),
                userSigninReqDTO.password());
        var authentication = authenticationManager.authenticate(credentials);
        var payload = tokenService.generateToken(authentication);
        return new ResponseDTO<TokenResDTO>(payload, new BasicResDTO(CommonConstants.LOGIN_SUCCESSFUL, HttpStatus.OK));
    }
    @GetMapping("renew-token")
    public ResponseDTO<TokenResDTO> refresh(Authentication authentication) {
        var payload = tokenService.generateToken(authentication);
        return new ResponseDTO<TokenResDTO>(payload, new BasicResDTO(CommonConstants.TOKEN_RENEWED, HttpStatus.OK));
    }
}
