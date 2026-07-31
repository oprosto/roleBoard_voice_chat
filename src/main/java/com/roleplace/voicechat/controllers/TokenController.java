package com.roleplace.voicechat.controllers;

import com.roleplace.voicechat.models.requests.UserIdDTO;
import com.roleplace.voicechat.models.requests.UserInteractRequest;
import com.roleplace.voicechat.services.TokenService;
import exceptions.NotFoundException;
import io.livekit.server.AccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/voice/chat/token")
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/{chatId}/get")
    public ResponseEntity<?> getToken(@PathVariable Long chatId, @RequestBody UserIdDTO request) {
        AccessToken accessToken = tokenService.createToken(chatId, request.userId());
        return ResponseEntity.ok().body(Map.of("token",accessToken.toJwt()));
    }
}
