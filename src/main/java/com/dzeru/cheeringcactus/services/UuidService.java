package com.dzeru.cheeringcactus.services;

import com.dzeru.cheeringcactus.entities.User;
import com.dzeru.cheeringcactus.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UuidService
{
    @Autowired
    UserRepo userRepo;

    public String generateRandomUuid()
    {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.substring(0, 5);

        User user = userRepo.findByUsername(uuid);

        while(user != null)
        {
            uuid = UUID.randomUUID().toString();
            uuid = uuid.substring(0, 5);
            user = userRepo.findByUsername(uuid);
        }

        return uuid;
    }
}
