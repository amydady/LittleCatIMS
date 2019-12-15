package com.littlecat.ims.student.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;

public class YongCanStudentMO extends BaseMO
{
	private String student;
	private String remark;

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

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
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

			mo.setId(rs.getString("id"));
			mo.setStudent(rs.getString("student"));
			mo.setRemark(rs.getString("remark"));

			// for display

			try
			{
				mo.setStudentName(rs.getString("StudentName"));
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
