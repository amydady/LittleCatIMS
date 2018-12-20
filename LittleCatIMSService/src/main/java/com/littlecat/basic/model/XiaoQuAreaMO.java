package com.littlecat.basic.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;

/**
 * 小区归属的区域信息
 * 
 * @author Administrator
 *
 */
public class XiaoQuAreaMO extends BaseMO
{
	private String name;
	private String enable;

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
	
	public static class MOMapper implements RowMapper<XiaoQuAreaMO>
	{
		@Override
		public XiaoQuAreaMO mapRow(ResultSet rs, int num) throws SQLException
		{
			XiaoQuAreaMO mo = new XiaoQuAreaMO();
			mo.setId(rs.getString("id"));
			mo.setName(rs.getString("name"));
			mo.setEnable(rs.getString("enable"));
			return mo;
		}
	}

}
