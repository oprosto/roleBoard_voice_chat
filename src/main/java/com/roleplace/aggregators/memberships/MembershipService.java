package com.roleplace.aggregators.memberships;

import com.roleplace.users.services.UserDataService;
import com.roleplace.voicechat.models.VoiceChat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MembershipService {
    private final UserDataService userDataService;

    public Membership createMembership(VoiceChat chat, UUID userId) {
        return new Membership(new MembershipId(userId, chat.getId()),
                userDataService.getReferenceById(userId), chat);
    }

    public Set<Membership> createMemberships(VoiceChat chat, Set<UUID> userIds) {
        Set<Membership> memberships = new HashSet<>();
        for (UUID userId : userIds)
            memberships.add(createMembership(chat, userId));
        return memberships;
    }
}
