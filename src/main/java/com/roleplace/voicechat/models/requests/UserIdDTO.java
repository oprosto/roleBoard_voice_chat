package com.roleplace.voicechat.models.requests;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UserIdDTO (@NotNull UUID userId)
{}
