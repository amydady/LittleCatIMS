package com.littlecat.ims.kecheng.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;

public class JieZhuanRecordMO extends BaseMO
{
	private String student;
	private String kechengs;
	private String kechengd;
	private int times;
	private String createTime;
	private String operator;

	private String studentName;
	private String kechengsName;
	private String kechengdName;
	private String operatorName;

	public String getStudent()
	{
		return student;
	}

	public void setStudent(String student)
	{
		this.student = student;
	}

	public String getKechengs()
	{
		return kechengs;
	}

	public void setKechengs(String kechengs)
	{
		this.kechengs = kechengs;
	}

	public String getKechengd()
	{
		return kechengd;
	}

	public void setKechengd(String kechengd)
	{
		this.kechengd = kechengd;
	}

	public int getTimes()
	{
		return times;
	}

	public void setTimes(int times)
	{
		this.times = times;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public String getKechengsName()
	{
		return kechengsName;
	}

	public void setKechengsName(String kechengsName)
	{
		this.kechengsName = kechengsName;
	}

	public String getKechengdName()
	{
		return kechengdName;
	}

	public void setKechengdName(String kechengdName)
	{
		this.kechengdName = kechengdName;
	}

	public String getOperatorName()
	{
		return operatorName;
	}

	public void setOperatorName(String operatorName)
	{
		this.operatorName = operatorName;
	}

	public static class MOMapper implements RowMapper<JieZhuanRecordMO>
	{
		@Override
		public JieZhuanRecordMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			JieZhuanRecordMO mo = new JieZhuanRecordMO();

			mo.setId(rs.getString("id"));
			mo.setStudent(rs.getString("student"));
			mo.setKechengs(rs.getString("kechengs"));
			mo.setKechengd(rs.getString("kechengd"));
			mo.setTimes(rs.getInt("times"));
			mo.setOperator(rs.getString("operator"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));

			try
			{
				mo.setKechengsName(rs.getString("kechengsName"));
			}
			catch (Exception e)
			{

			}
			try
			{
				mo.setKechengdName(rs.getString("kechengdName"));
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
