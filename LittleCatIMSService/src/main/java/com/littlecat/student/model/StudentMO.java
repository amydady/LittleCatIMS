package com.littlecat.student.model;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.common.consts.Sex;

public class StudentMO extends BaseMO
{
	private String name;
	private Sex sex;
	private String birthdayYear;
	private String birthdayMonth;
	private String birthdayDay;
	private String babaName;
	private String babaMobile;
	private String mamaName;
	private String mamaMobile;
	private String xuexiao;
	private String nianji;
	private String banji;
	private String xiaoqu;
	private String hobby;
	private String remark;
	private String createTime;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Sex getSex()
	{
		return sex;
	}

	public void setSex(Sex sex)
	{
		this.sex = sex;
	}

	public String getBirthdayYear()
	{
		return birthdayYear;
	}

	public void setBirthdayYear(String birthdayYear)
	{
		this.birthdayYear = birthdayYear;
	}

	public String getBirthdayMonth()
	{
		return birthdayMonth;
	}

	public void setBirthdayMonth(String birthdayMonth)
	{
		this.birthdayMonth = birthdayMonth;
	}

	public String getBirthdayDay()
	{
		return birthdayDay;
	}

	public void setBirthdayDay(String birthdayDay)
	{
		this.birthdayDay = birthdayDay;
	}

	public String getBabaName()
	{
		return babaName;
	}

	public void setBabaName(String babaName)
	{
		this.babaName = babaName;
	}

	public String getBabaMobile()
	{
		return babaMobile;
	}

	public void setBabaMobile(String babaMobile)
	{
		this.babaMobile = babaMobile;
	}

	public String getMamaName()
	{
		return mamaName;
	}

	public void setMamaName(String mamaName)
	{
		this.mamaName = mamaName;
	}

	public String getMamaMobile()
	{
		return mamaMobile;
	}

	public void setMamaMobile(String mamaMobile)
	{
		this.mamaMobile = mamaMobile;
	}

	public String getXuexiao()
	{
		return xuexiao;
	}

	public void setXuexiao(String xuexiao)
	{
		this.xuexiao = xuexiao;
	}

	public String getNianji()
	{
		return nianji;
	}

	public void setNianji(String nianji)
	{
		this.nianji = nianji;
	}

	public String getBanji()
	{
		return banji;
	}

	public void setBanji(String banji)
	{
		this.banji = banji;
	}

	public String getXiaoqu()
	{
		return xiaoqu;
	}

	public void setXiaoqu(String xiaoqu)
	{
		this.xiaoqu = xiaoqu;
	}

	public String getHobby()
	{
		return hobby;
	}

	public void setHobby(String hobby)
	{
		this.hobby = hobby;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

}
