package com.littlecat.ims.personal.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;

public class GouTongJiLuMO extends BaseMO
{
	private String studentName;
	private String topic;
	private String content;
	private String laterplan;
	private String remark;
	private String state;
	private String operator;
	private String createTime;

	// just for view
	private String operatorName;

	public String getTopic()
	{
		return topic;
	}

	public void setTopic(String topic)
	{
		this.topic = topic;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getLaterplan()
	{
		return laterplan;
	}

	public void setLaterplan(String laterplan)
	{
		this.laterplan = laterplan;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
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

	public static class MOMapper implements RowMapper<GouTongJiLuMO>
	{
		@Override
		public GouTongJiLuMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			GouTongJiLuMO mo = new GouTongJiLuMO();

			mo.setId(rs.getString("id"));
			mo.setStudentName(rs.getString("studentName"));
			mo.setTopic(rs.getString("topic"));
			mo.setContent(rs.getString("content"));
			mo.setLaterplan(rs.getString("laterplan"));
			mo.setRemark(rs.getString("remark"));
			mo.setState(rs.getString("state"));
			mo.setOperator(rs.getString("operator"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));

			// for display

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
