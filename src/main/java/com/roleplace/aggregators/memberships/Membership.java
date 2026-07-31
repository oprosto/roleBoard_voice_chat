package com.roleplace.aggregators.memberships;

import com.roleplace.users.models.User;
import com.roleplace.voicechat.models.VoiceChat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Объединяет юзера и чат, разрывая связь manyToMany
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "chat_id"})
})
@AllArgsConstructor
public class Membership {
    @EmbeddedId
    private MembershipId id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("chatId")
    @JoinColumn(name = "chat_id")
    VoiceChat chat;
}
