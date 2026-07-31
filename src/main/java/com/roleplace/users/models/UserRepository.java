package com.roleplace.users.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findFirstByUserTag(String tag);
    User findFirstById(UUID userId);

    @Query("SELECT u.id FROM User u WHERE u.id IN :ids")
    List<User> findAllByIdOrdered(@Param("ids") Collection<UUID> ids);
    @Query("SELECT u.id FROM User u WHERE u.id IN :ids")
    Set<User> findAllByIdFastSearch(@Param("ids") Collection<UUID> ids);

    boolean existsById(UUID userId);

}
