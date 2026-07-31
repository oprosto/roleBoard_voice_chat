package com.roleplace.voicechat.controllers;

import com.roleplace.voicechat.models.requests.CreateChatRequest;
import com.roleplace.voicechat.models.requests.InitCallRequest;
import com.roleplace.voicechat.models.requests.UserInteractRequest;
import com.roleplace.voicechat.services.VoiceChatService;
import exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/voice/chat")
public class VoiceChatRESTController {
    VoiceChatService voiceChatService;

    @PostMapping("/create")
    public ResponseEntity<?> createChat(@RequestBody CreateChatRequest request)
    {
        return ResponseEntity.ok().body(voiceChatService.createChat(request));
    }

    @PostMapping("/init")
    public ResponseEntity<?> initCall(@RequestBody InitCallRequest request)
    {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{chatId}/delete")
    public ResponseEntity<?> removeChat(@PathVariable Long chatId)
    {
        voiceChatService.removeChat(chatId);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{chatId}/join")
    public ResponseEntity<?> joinChat(@PathVariable Long chatId, @RequestBody UserInteractRequest request) throws NotFoundException {
        voiceChatService.joinChat(chatId, request.getUserIds());
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{chatId}/leave")
    public ResponseEntity<?> leaveChat(@PathVariable Long chatId, @RequestBody UserInteractRequest request) throws NotFoundException {
        voiceChatService.joinChat(chatId, request.getUserIds());
        return ResponseEntity.ok(null);
    }

}
