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
	private String state;
	private String createTime;

	private String kechengName;
	private String studentName;
	private String teacherName;
	private String shangkeshijian;

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

	public String getTeacherName()
	{
		return teacherName;
	}

	public void setTeacherName(String teacherName)
	{
		this.teacherName = teacherName;
	}

	public String getShangkeshijian()
	{
		return shangkeshijian;
	}

	public void setShangkeshijian(String shangkeshijian)
	{
		this.shangkeshijian = shangkeshijian;
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
			mo.setState(rs.getString("state"));

			// for display
			
			

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
				mo.setTeacherName(rs.getString("teacherName"));
			}
			catch (Exception e)
			{

			}
			
			try
			{
				mo.setShangkeshijian(rs.getString("shangkeshijian"));
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
