package com.littlecat.ims.basicinfo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;

/**
 * 字典类型MO
 * 
 * @author amydady
 *
 */
public class DicTypeMO extends BaseMO
{
	private String name;
	private int sortid;
	private String enable;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSortid()
	{
		return sortid;
	}

	public void setSortid(int sortid)
	{
		this.sortid = sortid;
	}

	public String getEnable()
	{
		return enable;
	}

	public void setEnable(String enable)
	{
		this.enable = enable;
	}

	public static class MOMapper implements RowMapper<DicTypeMO>
	{
		@Override
		public DicTypeMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			DicTypeMO mo = new DicTypeMO();

			mo.setId(rs.getString("id"));
			mo.setName(rs.getString("name"));
			mo.setSortid(rs.getInt("sortid"));
			mo.setEnable(rs.getString("enable"));

			return mo;
		}
	}
}
