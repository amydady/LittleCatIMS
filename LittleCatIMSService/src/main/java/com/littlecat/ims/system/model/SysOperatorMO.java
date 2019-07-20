package com.littlecat.ims.system.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;

/**
 * 系统操作人员
 * amydady
 *
 */
public class SysOperatorMO extends BaseMO
{
	private String username;
	private String password;
	private String name;
	private String remark;
	private String isboss;
	private String createTime;
	private String enable;

	public SysOperatorMO()
	{
		super();
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getEnable()
	{
		return enable;
	}

	public void setEnable(String enable)
	{
		this.enable = enable;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getIsboss()
	{
		return isboss;
	}

	public void setIsboss(String isboss)
	{
		this.isboss = isboss;
	}

	public static class MOMapper implements RowMapper<SysOperatorMO>
	{
		@Override
		public SysOperatorMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			SysOperatorMO mo = new SysOperatorMO();
			
			mo.setId(rs.getString("id"));
			mo.setUsername(rs.getString("username"));
			mo.setName(rs.getString("name"));
			mo.setRemark(rs.getString("remark"));
			mo.setIsboss(rs.getString("isboss"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));
			mo.setEnable(rs.getString("enable"));
			
			return mo;
		}
	}

}
