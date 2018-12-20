package com.littlecat.basic.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;

/**
 * 小区信息
 * 
 * @author Administrator
 *
 */
public class XiaoQuMO extends BaseMO
{

	private String area;
	private String name;
	private String code;
	private String enable;

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getEnable()
	{
		return enable;
	}

	public void setEnable(String enable)
	{
		this.enable = enable;
	}
	
	public static class MOMapper implements RowMapper<XiaoQuMO>
	{
		@Override
		public XiaoQuMO mapRow(ResultSet rs, int num) throws SQLException
		{
			XiaoQuMO mo = new XiaoQuMO();
			return mo;
		}
	}

}
