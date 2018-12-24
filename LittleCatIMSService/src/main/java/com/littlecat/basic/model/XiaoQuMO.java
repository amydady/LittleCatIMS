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
	private String enable;

	// just for view

	private String areaName;

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

	public String getEnable()
	{
		return enable;
	}

	public void setEnable(String enable)
	{
		this.enable = enable;
	}

	public String getAreaName()
	{
		return areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	public static class MOMapper implements RowMapper<XiaoQuMO>
	{
		@Override
		public XiaoQuMO mapRow(ResultSet rs, int num) throws SQLException
		{
			XiaoQuMO mo = new XiaoQuMO();
			mo.setId(rs.getString("id"));
			mo.setArea(rs.getString("area"));
			mo.setName(rs.getString("name"));
			mo.setEnable(rs.getString("enable"));

			try
			{
				mo.setAreaName(rs.getString("areaName"));
			}
			catch (Exception e)
			{

			}
			return mo;
		}
	}

}
