package com.dzeru.cheeringcactus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.log4j.Logger;

@SpringBootApplication
public class CheeringCactusApplication
{
	final static Logger logger = Logger.getLogger(CheeringCactusApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(CheeringCactusApplication.class, args);
		logger.info("START CHEERING CACTUS APPLICATION");
	}

}

