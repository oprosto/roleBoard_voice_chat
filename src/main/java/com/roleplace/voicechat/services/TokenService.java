package com.roleplace.voicechat.services;

import com.roleplace.aggregators.memberships.MembershipId;
import com.roleplace.aggregators.memberships.MembershipRepository;
import com.roleplace.users.services.UserDataService;
import com.roleplace.voicechat.models.VoiceChatRepository;
import exceptions.NotFoundException;
import io.livekit.server.AccessToken;
import io.livekit.server.RoomJoin;
import io.livekit.server.RoomName;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final VoiceChatRepository voiceChatRepository;
    private final UserDataService userDataService;
    private final MembershipRepository membershipRepository;

    @Value("${livekit.api.key}")
    private String LIVEKIT_API_KEY;

    @Value("${livekit.api.secret}")
    private String LIVEKIT_API_SECRET;

    @Value("${livekit.token-ttl-minutes:15}")
    private long tokenTtlMinutes;

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

    public AccessToken createToken(Long chatId, UUID user) throws NotFoundException {
        if (!voiceChatRepository.existsById(chatId)) {
            throw new NotFoundException("Voice chat", chatId.toString());
        }
        if (!userDataService.isExist(user)) {
            throw new NotFoundException("User", String.valueOf(user));
        }
        if (!membershipRepository.existsById(new MembershipId(user, chatId))) {
            throw new NotFoundException("Voice chat membership", user + ":" + chatId);
        }

        String roomName = chatId.toString();
        String participantName = user.toString();

        // Генерация JWT токена с помощью LiveKit SDK
        AccessToken token = new AccessToken(LIVEKIT_API_KEY, LIVEKIT_API_SECRET);
        token.setIdentity(participantName);
        token.setTtl(TimeUnit.MINUTES.toMillis(tokenTtlMinutes));
        token.addGrants(new RoomJoin(true), new RoomName(roomName));
        return token;
    }
}
