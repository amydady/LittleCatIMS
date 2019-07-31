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
	private String year;
	private String month;
	private String day;
	private String starttime;
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

	public String getYear()
	{
		return year;
	}

	public void setYear(String year)
	{
		this.year = year;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public String getDay()
	{
		return day;
	}

	public void setDay(String day)
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

	public String getStarttime()
	{
		return starttime;
	}

	public void setStarttime(String starttime)
	{
		this.starttime = starttime;
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
			mo.setYear(rs.getString("year"));
			mo.setMonth(rs.getString("month"));
			mo.setDay(rs.getString("day"));
			mo.setStarttime(rs.getString("starttime"));
			mo.setRemark(rs.getString("remark"));
			mo.setOperator(rs.getString("operator"));
			mo.setEnable(rs.getString("enable"));
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
