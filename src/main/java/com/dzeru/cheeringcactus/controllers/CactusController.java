package com.dzeru.cheeringcactus.controllers;

import com.dzeru.cheeringcactus.constants.Level;
import com.dzeru.cheeringcactus.entities.Cactus;
import com.dzeru.cheeringcactus.entities.User;
import com.dzeru.cheeringcactus.repos.CactusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

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
            long currentTime = System.currentTimeMillis();
            int age = (int) ((currentTime - cactus.getBirthday()) / 1000L / 60L / 60L / 24L);
            cactus.setAge(age);
            cactus.setMaxHp(Level.getInstance().getHpByAge(age));
            cactus.setLevel(Level.getInstance().getLevelByAge(age));

            // milliseconds -> seconds -> minutes -> hours
            int timeSinceLastVisit = (int) (currentTime - user.getLastVisit() / 1000L / 60L / 60L);
            int newCurrentHp = cactus.getCurrentHp();
            Random random = new Random();

            //summing up decrease of hp per hour
            for(int i = 0; i < timeSinceLastVisit; i++)
            {
                if(newCurrentHp > 8)
                    newCurrentHp -= random.nextInt() % 3 + 1; //3 is just small magic number
            }

            cactus.setCurrentHp(newCurrentHp);

            cactusRepo.save(cactus);

            return cactus;
        }
        else
        {
            return null;
        }
    }

    @RequestMapping(value = "/updatecactus", method = RequestMethod.POST)
    public ResponseEntity updateCactus(@RequestBody Cactus cactus, @AuthenticationPrincipal User user)
    {
        if(cactus.getUuid().equals(user.getUsername()))
        {
            cactusRepo.save(cactus);
        }
        else
        {
            return ResponseEntity.badRequest().body("Access is denied");
        }

        return ResponseEntity.ok().body("Cactus is successfully updated");
    }
}
