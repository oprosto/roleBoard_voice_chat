package com.roleplace.voicechat.models;

import com.roleplace.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface VoiceChatRepository extends JpaRepository<VoiceChat, Long> {
    VoiceChat findFirstById(Long chatId);

    @Query("SELECT vc.id FROM VoiceChat vc WHERE vc.id IN :ids")
    List<Long> findAllById(@Param("ids") List<Long> ids);

    boolean existsById(Long chatId);
    void deleteById(Long chatId);
}
