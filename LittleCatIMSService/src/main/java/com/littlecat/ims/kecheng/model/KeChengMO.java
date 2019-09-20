package com.littlecat.ims.kecheng.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.ims.common.consts.KeChengState;

public class KeChengMO extends BaseMO
{
	private String name;
	private String teacher;
	private String remark;
	private String enable;
	private String shangkeshijian;
	private String createTime;

	private String enableV;
	private String teacherName;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public String getTeacher()
	{
		return teacher;
	}

	public void setTeacher(String teacher)
	{
		this.teacher = teacher;
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

	public static class MOMapper implements RowMapper<KeChengMO>
	{
		@Override
		public KeChengMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			KeChengMO mo = new KeChengMO();

			mo.setId(rs.getString("id"));
			mo.setName(rs.getString("name"));
			mo.setTeacher(rs.getString("teacher"));
			mo.setEnable(rs.getString("enable"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));
			mo.setRemark(rs.getString("remark"));
			mo.setShangkeshijian(rs.getString("shangkeshijian"));

			// for display
			mo.setEnableV(KeChengState.getNameByCode(mo.getEnable()));

			try
			{
				mo.setTeacherName(rs.getString("teacherName"));
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
