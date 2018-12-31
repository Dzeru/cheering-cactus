package com.dzeru.cheeringcactus;

import com.dzeru.cheeringcactus.constants.Level;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class LevelTests
{

	@Test
	public void testGetAllLevelAgeHp()
	{
		int[][] testLevelAgeHp = {{0, 200}, {1, 205}, {5, 215}, {10, 225}, {20, 240},
				{30, 255}, {35, 260}, {40, 265}, {50, 280}, {60, 295}, {65, 300}, {70, 310}, {80, 320}};

		Level level = Level.getInstance();

		assertArrayEquals(testLevelAgeHp, level.getAllLevelAgeHp());
	}

	@Test
	public void testGetLevelByAge()
	{
		Level level = Level.getInstance();

		assertEquals(0, level.getLevelByAge(0));
		assertEquals(1, level.getLevelByAge(1));
		assertEquals(1, level.getLevelByAge(4));
		assertEquals(11, level.getLevelByAge(75));
	}

	@Test
	public void testGetHpByAge()
	{
		Level level = Level.getInstance();

		assertEquals(200, level.getHpByAge(0));
		assertEquals(205, level.getHpByAge(1));
		assertEquals(205, level.getHpByAge(4));
		assertEquals(310, level.getHpByAge(75));
		assertEquals(-1, level.getHpByAge(-1));
	}

	@Test
	public void testGetLevelByHp()
	{
		Level level = Level.getInstance();

		assertEquals(0, level.getLevelByHp(200));
		assertEquals(1, level.getLevelByHp(205));
		assertEquals(3, level.getLevelByHp(225));
		assertEquals(11, level.getLevelByHp(310));
		assertEquals(-1, level.getLevelByHp(-1));
	}

	@Test
	public void testGetAgeByHp()
	{
		Level level = Level.getInstance();

		assertEquals(0, level.getAgeByHp(200));
		assertEquals(1, level.getAgeByHp(205));
		assertEquals(10, level.getAgeByHp(225));
		assertEquals(70, level.getAgeByHp(310));
		assertEquals(-1, level.getAgeByHp(-1));
	}
}
