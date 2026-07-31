package com.roleplace.voicechat.models.requests;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInteractRequest {
    @NotEmpty
    private Set<UUID> userIds;
}
