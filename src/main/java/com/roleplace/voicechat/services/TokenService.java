package com.roleplace.voicechat.services;

import com.roleplace.users.services.UserDataService;
import com.roleplace.voicechat.models.VoiceChatRepository;
import com.roleplace.voicechat.models.requests.UserInteractRequest;
import exceptions.NotFoundException;
import io.livekit.server.AccessToken;
import io.livekit.server.RoomJoin;
import io.livekit.server.RoomName;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final VoiceChatRepository voiceChatRepository;
    private final UserDataService userDataService;

    @Value("${livekit.api.key}")
    private String LIVEKIT_API_KEY;

    @Value("${livekit.api.secret}")
    private String LIVEKIT_API_SECRET;

//    public Map<UUID, AccessToken> createTokens(Long chatId, Set<UUID> users) throws NotFoundException {
//        if (!voiceChatRepository.existsById(chatId))
//            throw new NotFoundException("VoiceChat", chatId.toString());
//        userDataService.getExistedUsers(users);
//
//        Map<UUID, AccessToken> tokens = new HashMap<>();
//        for (UUID user : users) {
//            tokens.put(user, createToken(chatId, user));
//        }
//        return tokens;
//    }

    public AccessToken createToken(Long chatId, UUID user) {
        String roomName = chatId.toString();
        String participantName = user.toString();

        // Генерация JWT токена с помощью LiveKit SDK
        AccessToken token = new AccessToken(LIVEKIT_API_KEY, LIVEKIT_API_SECRET);
        token.setIdentity(participantName);
        token.addGrants(new RoomJoin(true), new RoomName(roomName));
        return token;
    }
}