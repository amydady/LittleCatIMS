package com.littlecat.ims.student.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class YongCanStudentMO
{
	private String student;

	private String studentName;
	private String xuexiaoName;
	private String nianjiName;
	private String banjiName;

	public String getXuexiaoName()
	{
		return xuexiaoName;
	}

	public void setXuexiaoName(String xuexiaoName)
	{
		this.xuexiaoName = xuexiaoName;
	}

	public String getNianjiName()
	{
		return nianjiName;
	}

	public void setNianjiName(String nianjiName)
	{
		this.nianjiName = nianjiName;
	}

	public String getBanjiName()
	{
		return banjiName;
	}

	public void setBanjiName(String banjiName)
	{
		this.banjiName = banjiName;
	}

	public String getStudent()
	{
		return student;
	}

	public void setStudent(String student)
	{
		this.student = student;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public static class MOMapper implements RowMapper<YongCanStudentMO>
	{
		@Override
		public YongCanStudentMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			YongCanStudentMO mo = new YongCanStudentMO();

			mo.setStudent(rs.getString("student"));

			// for display

			try
			{
				mo.setStudentName(rs.getString("studentName"));
			}
			catch (Exception e)
			{

			}

			try
			{
				mo.setXuexiaoName(rs.getString("xuexiaoName"));
			}
			catch (Exception e)
			{

			}

			try
			{
				mo.setNianjiName(rs.getString("nianjiName"));
			}
			catch (Exception e)
			{

			}

			try
			{
				mo.setBanjiName(rs.getString("banjiName"));
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
