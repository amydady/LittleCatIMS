package com.littlecat.ims.kecheng.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;

public class TimesRecordMO extends BaseMO
{
	private String kecheng;
	private String student;
	private int year;
	private int month;
	private int day;
	private String remark;
	private String operator;
	private String createTime;
	private String enable;

	private String kechengName;
	private String studentName;
	private String operatorName;
	private String enableV;

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

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public int getDay()
	{
		return day;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getKechengName()
	{
		return kechengName;
	}

	public void setKechengName(String kechengName)
	{
		this.kechengName = kechengName;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public String getOperatorName()
	{
		return operatorName;
	}

	public void setOperatorName(String operatorName)
	{
		this.operatorName = operatorName;
	}

	public String getEnable()
	{
		return enable;
	}

	public void setEnable(String enable)
	{
		this.enable = enable;
	}

	public String getEnableV()
	{
		return enableV;
	}

	public void setEnableV(String enableV)
	{
		this.enableV = enableV;
	}

	public static class MOMapper implements RowMapper<TimesRecordMO>
	{
		@Override
		public TimesRecordMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			TimesRecordMO mo = new TimesRecordMO();

			mo.setId(rs.getString("id"));
			mo.setKecheng(rs.getString("kecheng"));
			mo.setStudent(rs.getString("student"));
			mo.setYear(rs.getInt("year"));
			mo.setMonth(rs.getInt("month"));
			mo.setDay(rs.getInt("day"));
			mo.setRemark(rs.getString("remark"));
			mo.setOperator(rs.getString("operator"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));

			// for display
			mo.setEnableV(mo.enable.equals("Y") ? "是" : "否");
			
			try
			{
				mo.setKechengName(rs.getString("kechengName"));
			}
			catch (Exception e)
			{

			}

			try
			{
				mo.setStudentName(rs.getString("studentName"));
			}
			catch (Exception e)
			{

			}
			
			try
			{
				mo.setOperatorName(rs.getString("operatorName"));
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
