package com.dzeru.cheeringcactus.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cactus")
public class Cactus
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String uuid;

    private long birthday; // in milliseconds
    private int age; // in days
    private int hp;
    private String cactusColor; // in hex
    private String potColor; // in hex
    private boolean isEvilMode;

    public Cactus()
    {

    }

    public Cactus(String uuid, long birthday, int age, int hp, String cactusColor, String potColor, boolean isEvilMode)
    {
        this.uuid = uuid;
        this.birthday = birthday;
        this.age = age;
        this.hp = hp;
        this.cactusColor = cactusColor;
        this.potColor = potColor;
        this.isEvilMode = isEvilMode;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public long getBirthday()
    {
        return birthday;
    }

    public void setBirthday(long birthday)
    {
        this.birthday = birthday;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public String getCactusColor()
    {
        return cactusColor;
    }

    public void setCactusColor(String cactusColor)
    {
        this.cactusColor = cactusColor;
    }

    public String getPotColor()
    {
        return potColor;
    }

    public void setPotColor(String potColor)
    {
        this.potColor = potColor;
    }

    public boolean isEvilMode()
    {
        return isEvilMode;
    }

    public void setEvilMode(boolean evilMode)
    {
        isEvilMode = evilMode;
    }
}
