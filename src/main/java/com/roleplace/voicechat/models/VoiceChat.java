package com.roleplace.voicechat.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public VoiceChat(String name)
    {
        this.name = name;
    }

}
