package com.dzeru.cheeringcactus.constants;

public class Level
{
	private static Level instance = new Level();

	private Level()
	{

	}

	public static Level getInstance()
	{
		return instance;
	}

	/*
	array's position: level
	0 column: days to reach a level
	1 column: hp of cactus depends on level
	*/
	private final static int[][] LevelAgeHp = {{0, 200}, {1, 205}, {5, 215}, {10, 225}, {20, 240},
			{30, 255}, {35, 260}, {40, 265}, {50, 280}, {60, 295}, {65, 300}, {70, 310}, {80, 320}};

	public int[][] getAllLevelAgeHp()
	{
		return LevelAgeHp;
	}

	public int getLevelByAge(int days)
	{
		if(days < 0)
			return -1;

		for(int i = 0; i < LevelAgeHp.length; i++)
		{
			if(LevelAgeHp[i][0] == days)
				return i;
			if(LevelAgeHp[i][0] > days)
				return i - 1;
		}
		return -1;
	}

	public int getHpByAge(int days)
	{
		if(days < 0)
			return -1;

		for(int i = 0; i < LevelAgeHp.length; i++)
		{
			if(LevelAgeHp[i][0] == days)
				return LevelAgeHp[i][1];
			if(LevelAgeHp[i][0] > days)
				return LevelAgeHp[i - 1][1];
		}
		return -1;
	}

	public int getLevelByHp(int hp)
	{
		if(hp < 0)
			return -1;

		for(int i = 0; i < LevelAgeHp.length; i++)
		{
			if(LevelAgeHp[i][1] == hp)
				return i;
			if(LevelAgeHp[i][1] > hp)
				return i - 1;
		}
		return -1;
	}

	public int getAgeByHp(int hp)
	{
		if(hp < 0)
			return -1;

		for(int i = 0; i < LevelAgeHp.length; i++)
		{
			if(LevelAgeHp[i][1] == hp)
				return LevelAgeHp[i][0];
			if(LevelAgeHp[i][1] > hp)
				return LevelAgeHp[i - 1][0];
		}
		return -1;
	}
}
