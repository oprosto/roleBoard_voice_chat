package com.roleplace.voicechat.models;

import com.roleplace.users.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoiceChat {
    //Подумать над NoArgsConstructor могут быть баги
    //id long т.к. предполагается, что будет использоваться только внутри chat-service
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column
    private String name;

    @Transient
    private Set<User> members = new HashSet<>();

    public VoiceChat(String name)
    {
        this.name = name;
    }

    public void addMember(User user)
    {
        members.add(user);
    }
    public void addMembers(Collection<User> users)
    {
        members.addAll(users);
    }
    public void removeMember(User user)
    {
        members.remove(user);
    }
    public void removeMembers(Collection<User> users)
    {
        members.removeAll(users);
    }
}
