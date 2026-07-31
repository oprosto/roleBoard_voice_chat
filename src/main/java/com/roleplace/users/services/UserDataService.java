package com.roleplace.users.services;

import com.roleplace.users.models.User;
import com.roleplace.users.models.UserRepository;
import exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.CollectionTools;
import tools.ExceptionTools;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final UserRepository userRepository;

    public void save(User user)
    {
        userRepository.save(user);
    }

    public User findById(UUID id)
    {
        return userRepository.findFirstById(id);
    }

    public User getReferenceById(UUID id){return userRepository.getReferenceById(id);}

    public List<User> getUsersById(List<UUID> ids)
    {
        if (CollectionTools.isEmpty(ids))
            return new ArrayList<>(0);
        return userRepository.findAllById(ids);
    }
    public Boolean isExist(UUID userId)
    {
        if (userId == null)
            return false;
        return userRepository.existsById(userId);
    }

    public Set<User> getExistedUsers(Collection<UUID> userIds) throws NotFoundException {
        Set<User> foundedUsers = userRepository.findAllByIdFastSearch(userIds);
        List<Object> notExistUsers = List.of(foundedUsers.stream()
                .filter(userIds::contains)
                .toList());
        if (CollectionTools.isEmpty(notExistUsers))
        {
            throw new NotFoundException(ExceptionTools.createMessage("Users ", notExistUsers, " not found"));
        }
        return foundedUsers;
    }
}
