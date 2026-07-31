package com.roleplace.users.services;

import com.roleplace.aggregators.memberships.MembershipRepository;
import com.roleplace.users.models.User;
import com.roleplace.voicechat.models.VoiceChat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserDataService userDataService;
    private final MembershipRepository membershipRepository;

    //TODO добавить кэширование
    public List<VoiceChat> getAllChats(UUID userId) {
        return membershipRepository.findUserChats(userId);
    }

    public User findById(UUID id) {
        return userDataService.findById(id);
    }
}
