package com.roleplace.voicechat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignalMessage {

    private String type;
    private String roomId;
    private String sender;
    private String target;

    private Object data;

    // getters/setters
}
