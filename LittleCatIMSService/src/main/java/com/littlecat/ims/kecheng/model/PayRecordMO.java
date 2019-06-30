package com.littlecat.ims.kecheng.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;

public class PayRecordMO extends BaseMO
{
	private String student;
	private int fee;
	private int times;
	private String remark;
	private String createTime;

	// just for view
	private String studentName;

	public String getStudent()
	{
		return student;
	}

	public void setStudent(String student)
	{
		this.student = student;
	}

	public int getFee()
	{
		return fee;
	}

	public void setFee(int fee)
	{
		this.fee = fee;
	}

	public int getTimes()
	{
		return times;
	}

	public void setTimes(int times)
	{
		this.times = times;
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

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public static class MOMapper implements RowMapper<PayRecordMO>
	{
		@Override
		public PayRecordMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			PayRecordMO mo = new PayRecordMO();

			mo.setId(rs.getString("id"));
			mo.setStudent(rs.getString("student"));
			mo.setFee(rs.getInt("fee"));
			mo.setTimes(rs.getInt("times"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));
			mo.setRemark(rs.getString("remark"));

			// for display

			try
			{
				mo.setStudentName(rs.getString("studentName"));
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
