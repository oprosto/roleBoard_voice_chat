package com.roleplace.voicechat.services;

import com.roleplace.aggregators.memberships.Membership;
import com.roleplace.aggregators.memberships.MembershipId;
import com.roleplace.aggregators.memberships.MembershipRepository;
import com.roleplace.aggregators.memberships.MembershipService;
import com.roleplace.users.models.User;
import com.roleplace.users.services.UserDataService;
import com.roleplace.voicechat.models.VoiceChat;
import com.roleplace.voicechat.models.VoiceChatRepository;
import com.roleplace.voicechat.models.requests.CreateChatRequest;
import com.roleplace.voicechat.models.requests.UserInteractRequest;
import exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoiceChatService {

    private final VoiceChatRepository voiceChatRepository;
    private final UserDataService userDataService;
    private final MembershipRepository membershipRepository;
    private final MembershipService membershipService;

    @Transactional
    public void joinChat(Long chatId, Set<UUID> userIds) throws NotFoundException {
        VoiceChat chat = getChat(chatId);
        Set<User> users = userDataService.getExistedUsers(userIds);

        List<Membership> newMemberships = users.stream()
                .filter(user -> !membershipRepository.existsById(new MembershipId(user.getId(), chatId)))
                .map(user -> membershipService.createMembership(chat, user.getId()))
                .toList();
        membershipRepository.saveAll(newMemberships);
    }

    @Transactional
    public void leaveChat(Long chatId, Set<UUID> userIds) throws NotFoundException {
        getChat(chatId);
        userDataService.getExistedUsers(userIds);

        Set<MembershipId> membershipIds = userIds.stream()
                .map(userId -> new MembershipId(userId, chatId))
                .collect(Collectors.toSet());
        membershipRepository.deleteAllByIdInBatch(membershipIds);
    }

    @Transactional
    public long createChat(CreateChatRequest request) {
        VoiceChat voiceChat = voiceChatRepository.save(new VoiceChat(request.getChatName()));
        return voiceChat.getId();
    }

    @Transactional
    public void removeChat(Long chatId) {
        voiceChatRepository.deleteById(chatId);
    }

    public void initChat(UserInteractRequest request) {
        // Reserved for call initialization separate from room creation.
    }

    private VoiceChat getChat(Long chatId) throws NotFoundException {
        VoiceChat chat = voiceChatRepository.findFirstById(chatId);
        if (chat == null) {
            throw new NotFoundException("Voice chat", chatId.toString());
        }
        return chat;
    }
}
