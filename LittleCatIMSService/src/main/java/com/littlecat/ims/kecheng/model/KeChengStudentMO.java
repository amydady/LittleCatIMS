package com.littlecat.ims.kecheng.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;

public class KeChengStudentMO extends BaseMO
{
	private String kecheng;
	private String student;
	private int remaintimes;
	private String remark;
	private String state;
	private String createTime;

	public String getKecheng()
	{
		return kecheng;
	}

	public void setKecheng(String kecheng)
	{
		this.kecheng = kecheng;
	}

	public String getStudent()
	{
		return student;
	}

	public void setStudent(String student)
	{
		this.student = student;
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

	public int getRemaintimes()
	{
		return remaintimes;
	}

	public void setRemaintimes(int remaintimes)
	{
		this.remaintimes = remaintimes;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public static class MOMapper implements RowMapper<KeChengStudentMO>
	{
		@Override
		public KeChengStudentMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			KeChengStudentMO mo = new KeChengStudentMO();

			mo.setId(rs.getString("id"));
			mo.setKecheng(rs.getString("kecheng"));
			mo.setStudent(rs.getString("student"));
			mo.setRemaintimes(rs.getInt("remaintimes"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));
			mo.setRemark(rs.getString("remark"));
			mo.setState(rs.getString("state"));


			// for display

			try
			{
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
