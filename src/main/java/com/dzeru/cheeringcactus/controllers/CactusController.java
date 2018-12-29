package com.dzeru.cheeringcactus.controllers;

import com.dzeru.cheeringcactus.entities.Cactus;
import com.dzeru.cheeringcactus.entities.User;
import com.dzeru.cheeringcactus.repos.CactusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
TODO:
1. Pick up colors for cactus
2. Pick up hp for cactus
*/

@RestController
public class CactusController
{
    @Autowired
    CactusRepo cactusRepo;

    @RequestMapping(value = "/loadcactus", method = RequestMethod.GET)
    public Cactus loadCactus(@RequestParam(value = "uuid") String uuid, @AuthenticationPrincipal User user)
    {
        if(uuid.equals(user.getUsername()))
        {
            user.setLastVisit(System.currentTimeMillis());
            Cactus cactus = cactusRepo.findByUuid(uuid);
            // milliseconds -> seconds -> minutes -> hours -> days
            int age = (int) ((System.currentTimeMillis() - cactus.getBirthday()) / 1000L / 60L / 60L / 24L);
            cactus.setAge(age);
            return cactus;
        }
        else
        {
            return null;
        }
    }
}
