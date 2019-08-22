package com.littlecat.ims.personal.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.ims.common.consts.LeaveRecordState;

public class LeaveRecordMO extends BaseMO
{
	private String staff;
	private String leavedate;
	private String remark;
	private String state;
	private String createTime;

	// just for view
	private String staffName;
	private String stateName;

	public String getStaff()
	{
		return staff;
	}

	public void setStaff(String staff)
	{
		this.staff = staff;
	}

	public String getLeavedate()
	{
		return leavedate;
	}

	public void setLeavedate(String leavedate)
	{
		this.leavedate = leavedate;
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

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getStaffName()
	{
		return staffName;
	}

	public void setStaffName(String staffName)
	{
		this.staffName = staffName;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public static class MOMapper implements RowMapper<LeaveRecordMO>
	{
		@Override
		public LeaveRecordMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			LeaveRecordMO mo = new LeaveRecordMO();

			mo.setId(rs.getString("id"));
			mo.setStaff(rs.getString("staff"));
			mo.setLeavedate(rs.getString("leavedate"));
			mo.setRemark(rs.getString("remark"));
			mo.setState(rs.getString("state"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));

			// for display
			mo.setStateName(LeaveRecordState.getNameByCode(mo.getState()));

			try
			{
				mo.setStaffName(rs.getString("staffName"));
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
