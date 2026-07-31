package com.roleplace.voicechat.models.requests;


import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInteractRequest {
    @NonNull
    Set<UUID> userIds;
}
