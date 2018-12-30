package com.dzeru.cheeringcactus.controllers;

import com.dzeru.cheeringcactus.entities.Cactus;
import com.dzeru.cheeringcactus.entities.Role;
import com.dzeru.cheeringcactus.entities.User;
import com.dzeru.cheeringcactus.repos.CactusRepo;
import com.dzeru.cheeringcactus.repos.UserRepo;
import com.dzeru.cheeringcactus.services.UuidService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

@Controller
public class PageController
{
	final static Logger logger = Logger.getLogger(PageController.class);

	@Autowired
	UuidService uuidService;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CactusRepo cactusRepo;

    @GetMapping("/")
    public String index(Model model) throws IOException
    {
    	logger.info("Get index page");

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/items.properties");
        Properties itemsProperties = new Properties();
        itemsProperties.load(inputStream);
        model.addAttribute("logo", itemsProperties.getProperty("logo"));

        return "index";
    }

    @GetMapping("/cactus")
	public String cactus(@AuthenticationPrincipal User user, Model model)
    {
    	logger.info("Get cactus page");
    	model.addAttribute("userUuid", user.getUsername());

    	return "cactus";
    }

    @GetMapping("/newcactus")
	public String newCactus(Model model)
    {
    	logger.info("Get new cactus page");

	    String uuid = uuidService.generateRandomUuid();
	    long birthday = System.currentTimeMillis();
	    String cactusColor = "#669900";
	    String potColor = "#663300";

	    Cactus newCactus = new Cactus(uuid, birthday, 0, 0, 200, cactusColor, potColor, false);
	    cactusRepo.save(newCactus);

	    User newUser = new User();
	    newUser.setUsername(uuid);
	    newUser.setPassword("");
	    newUser.setRoles(Collections.singleton(Role.USER));
	    userRepo.save(newUser);

	    model.addAttribute("userUuid", uuid);

		return "greeting";
    }
}
