package com.littlecat.student.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;

public class StudentMO extends BaseMO
{
	private String name;
	private String mobile;
	private String xuexiao;
	private String nianji;
	private String banji;
	private String xiaoqu;
	private String remark;
	private String tuijianren;
	private String enable;
	private String createTime;
	

	private String enableV;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getXuexiao()
	{
		return xuexiao;
	}

	public void setXuexiao(String xuexiao)
	{
		this.xuexiao = xuexiao;
	}

	public String getNianji()
	{
		return nianji;
	}

	public void setNianji(String nianji)
	{
		this.nianji = nianji;
	}

	public String getBanji()
	{
		return banji;
	}

	public void setBanji(String banji)
	{
		this.banji = banji;
	}

	public String getXiaoqu()
	{
		return xiaoqu;
	}

	public void setXiaoqu(String xiaoqu)
	{
		this.xiaoqu = xiaoqu;
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



	public String getTuijianren()
	{
		return tuijianren;
	}

	public void setTuijianren(String tuijianren)
	{
		this.tuijianren = tuijianren;
	}



	public static class MOMapper implements RowMapper<StudentMO>
	{
		@Override
		public StudentMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			StudentMO mo = new StudentMO();

			mo.setId(rs.getString("id"));
			mo.setName(rs.getString("name"));
			mo.setMobile(rs.getString("mobile"));
			mo.setXuexiao(rs.getString("xuexiao"));
			mo.setNianji(rs.getString("nianji"));
			mo.setBanji(rs.getString("banji"));
			mo.setXiaoqu(rs.getString("xiaoqu"));
			mo.setTuijianren(rs.getString("tuijianren"));
			mo.setEnable(rs.getString("enable"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));
			mo.setRemark(rs.getString("remark"));

			// for display
			mo.setEnableV(mo.enable.equals("Y") ? "是" : "否");

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
