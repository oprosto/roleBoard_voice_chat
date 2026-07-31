package com.roleplace.voicechat.services;

import com.roleplace.users.services.UserDataService;
import com.roleplace.voicechat.models.VoiceChat;
import com.roleplace.voicechat.models.VoiceChatRepository;
import com.roleplace.voicechat.models.requests.CreateChatRequest;
import com.roleplace.voicechat.models.requests.UserInteractRequest;
import exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoiceChatService {

    VoiceChatRepository voiceChatRepository;
    UserDataService userDataService;

    public void joinChat(Long chatId, Set<UUID> userIds) throws NotFoundException {
        VoiceChat chat = voiceChatRepository.findFirstById(chatId);
        if (chat == null)
            throw new NotFoundException("Voice chat", chatId.toString());

        chat.addMembers(userDataService.getExistedUsers(userIds));
    }

    public void leaveChat(Long chatId, Set<UUID> userIds) throws NotFoundException {
        VoiceChat chat = voiceChatRepository.findFirstById(chatId);
        if (chat == null)
            throw new NotFoundException("Voice chat", chatId.toString());

        chat.removeMembers(userDataService.getExistedUsers(userIds));

    }

    public long createChat(CreateChatRequest request) {

        VoiceChat voiceChat = new VoiceChat(request.getChatName());
        //Установить дефолтные настройки в будущем
        return voiceChat.getId();
    }

    public void removeChat(Long chatId) {

        voiceChatRepository.deleteById(chatId);
    }

    public void initChat(UserInteractRequest request) {

        //VoiceChat voiceChat = new VoiceChat(request.getChatName());

    }
}
