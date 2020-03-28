package com.littlecat.ims.kecheng.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.ims.common.consts.PaiKeCycleType;

public class KeChengBiaoMO extends BaseMO
{
	private String kecheng;
	private String cycle;
	private String fixdate;
	private String begindate;
	private String enddate;
	private String weekday;
	private String timebeginhour;
	private String timebeginmin;
	private String timeendhour;
	private String timeendmin;
	private String createTime;

	private String kechengName;
	private String teacherName;
	private String cycleName;
	private String weekdayName;
	private String shangkeshijian;

	public String getKecheng()
	{
		return kecheng;
	}

	public void setKecheng(String kecheng)
	{
		this.kecheng = kecheng;
	}

	public String getFixdate()
	{
		return fixdate;
	}

	public void setFixdate(String fixdate)
	{
		this.fixdate = fixdate;
	}

	public String getBegindate()
	{
		return begindate;
	}

	public void setBegindate(String begindate)
	{
		this.begindate = begindate;
	}

	public String getEnddate()
	{
		return enddate;
	}

	public void setEnddate(String enddate)
	{
		this.enddate = enddate;
	}

	public String getWeekday()
	{
		return weekday;
	}

	public void setWeekday(String weekday)
	{
		this.weekday = weekday;
	}

	public String getCycle()
	{
		return cycle;
	}

	public void setCycle(String cycle)
	{
		this.cycle = cycle;
	}

	public String getTimebeginhour()
	{
		return timebeginhour;
	}

	public void setTimebeginhour(String timebeginhour)
	{
		this.timebeginhour = timebeginhour;
	}

	public String getTimebeginmin()
	{
		return timebeginmin;
	}

	public void setTimebeginmin(String timebeginmin)
	{
		this.timebeginmin = timebeginmin;
	}

	public String getTimeendhour()
	{
		return timeendhour;
	}

	public void setTimeendhour(String timeendhour)
	{
		this.timeendhour = timeendhour;
	}

	public String getTimeendmin()
	{
		return timeendmin;
	}

	public void setTimeendmin(String timeendmin)
	{
		this.timeendmin = timeendmin;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getWeekdayName()
	{
		return weekdayName;
	}

	public void setWeekdayName(String weekdayName)
	{
		this.weekdayName = weekdayName;
	}

	public String getCycleName()
	{
		return cycleName;
	}

	public void setCycleName(String cycleName)
	{
		this.cycleName = cycleName;
	}

	public String getKechengName()
	{
		return kechengName;
	}

	public void setKechengName(String kechengName)
	{
		this.kechengName = kechengName;
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

	public static class MOMapper implements RowMapper<KeChengBiaoMO>
	{
		@Override
		public KeChengBiaoMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			KeChengBiaoMO mo = new KeChengBiaoMO();

			mo.setId(rs.getString("id"));
			mo.setKecheng(rs.getString("kecheng"));
			mo.setFixdate(rs.getString("fixdate"));
			mo.setBegindate(rs.getString("begindate"));
			mo.setEnddate(rs.getString("enddate"));
			mo.setWeekday(rs.getString("weekday"));
			mo.setCycle(rs.getString("cycle"));
			mo.setTimebeginhour(rs.getString("timebeginhour"));
			mo.setTimebeginmin(rs.getString("timebeginmin"));
			mo.setTimeendhour(rs.getString("timeendhour"));
			mo.setTimeendmin(rs.getString("timeendmin"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));

			// for display
			mo.setCycleName(PaiKeCycleType.getNameByCode(rs.getString("cycle")));
			mo.setShangkeshijian(mo.getTimebeginhour() + ":" + mo.getTimebeginmin() + "-" + mo.getTimeendhour() + ":" + mo.getTimeendmin());

			try
			{
				mo.setKechengName(rs.getString("kechengName"));
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
				mo.setWeekdayName(rs.getString("weekdayName"));
			}
			catch (Exception e)
			{

			}

			return mo;
		}
	}

}
