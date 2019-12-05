package com.littlecat.ims.system.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;

/**
 * 系统操作人员 amydady
 *
 */
public class QianDaoMO extends BaseMO
{
	private String userid;
	private String createTime;

	private String userName;

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public static class MOMapper implements RowMapper<QianDaoMO>
	{
		@Override
		public QianDaoMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			QianDaoMO mo = new QianDaoMO();

			mo.setId(rs.getString("id"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));

			try
			{
				mo.setUserName(rs.getString("userName"));
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
