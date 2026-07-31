package com.roleplace.users.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String userTag;

    @Column(nullable = false)
    private Boolean locked;

    @OneToMany
    private Map<String, User> friends = new HashMap<>();

    private Boolean isActive = false;

    public User(UUID id, String nickname, String userTag, Boolean isLocked)
    {
        this.id = id;
        this.nickname = nickname;
        this.userTag = userTag;
        this.locked = isLocked;
    }
}
