package com.littlecat.ims.student.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;

public class YongCanRecordMO extends BaseMO
{
	private String student;
	private String year;
	private String month;
	private String day;
	private String remark;
	private String operator;
	private String createTime;

	private String studentName;
	private String operatorName;

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

	public static class MOMapper implements RowMapper<YongCanRecordMO>
	{
		@Override
		public YongCanRecordMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			YongCanRecordMO mo = new YongCanRecordMO();

			mo.setId(rs.getString("id"));
			mo.setStudent(rs.getString("student"));
			mo.setYear(rs.getString("year"));
			mo.setMonth(rs.getString("month"));
			mo.setDay(rs.getString("day"));
			mo.setRemark(rs.getString("remark"));
			mo.setOperator(rs.getString("operator"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));

			// for display

			try
			{
				mo.setStudentName(rs.getString("StudentName"));
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
