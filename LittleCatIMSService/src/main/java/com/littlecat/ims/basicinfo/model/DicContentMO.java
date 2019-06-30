package com.littlecat.ims.basicinfo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;

/**
 * 品牌MO
 * 
 * @author amydady
 *
 */
public class DicContentMO extends BaseMO
{
	private String typeid;
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

	public String getTypeid()
	{
		return typeid;
	}

	public void setTypeid(String typeid)
	{
		this.typeid = typeid;
	}

	public static class MOMapper implements RowMapper<DicContentMO>
	{
		@Override
		public DicContentMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			DicContentMO mo = new DicContentMO();

			mo.setId(rs.getString("id"));
			mo.setTypeid(rs.getString("typeid"));
			mo.setName(rs.getString("name"));
			mo.setSortid(rs.getInt("sortid"));
			mo.setEnable(rs.getString("enable"));

			return mo;
		}
	}
}
