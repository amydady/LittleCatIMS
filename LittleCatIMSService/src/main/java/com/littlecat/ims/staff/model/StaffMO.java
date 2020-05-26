package com.littlecat.ims.staff.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.littlecat.cbb.common.BaseMO;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.ims.common.consts.Sex;
import com.littlecat.ims.common.consts.StaffState;
import com.littlecat.ims.common.consts.StaffWorkType;
import com.littlecat.ims.common.consts.XueLi;

public class StaffMO extends BaseMO
{
	private String name;
	private String mobile;
	private String idcard;
	private String worktype;
	private String sex;
	private int age;
	private String biyexuexiao;
	private String biyeshijian;
	private String xueli;
	private String zhuanye;
	private String techangjirongyu;
	private String joindate;
	private int shiyongqi;
	private String zhuanzhengdate;
	private int shiyonggongzi;
	private int zhengshigongzi;
	private String keshigongzi;
	private String baoxianxinxi;
	private String remark;
	private String leavedate;
	private String state;
	private String createTime;

	private String worktypeName;
	private String sexName;
	private String xueliName;
	private String stateName;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getWorktype()
	{
		return worktype;
	}

	public void setWorktype(String worktype)
	{
		this.worktype = worktype;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getBiyexuexiao()
	{
		return biyexuexiao;
	}

	public void setBiyexuexiao(String biyexuexiao)
	{
		this.biyexuexiao = biyexuexiao;
	}

	public String getBiyeshijian()
	{
		return biyeshijian;
	}

	public void setBiyeshijian(String biyeshijian)
	{
		this.biyeshijian = biyeshijian;
	}

	public String getXueli()
	{
		return xueli;
	}

	public void setXueli(String xueli)
	{
		this.xueli = xueli;
	}

	public String getZhuanye()
	{
		return zhuanye;
	}

	public void setZhuanye(String zhuanye)
	{
		this.zhuanye = zhuanye;
	}

	public String getTechangjirongyu()
	{
		return techangjirongyu;
	}

	public void setTechangjirongyu(String techangjirongyu)
	{
		this.techangjirongyu = techangjirongyu;
	}

	public String getJoindate()
	{
		return joindate;
	}

	public void setJoindate(String joindate)
	{
		this.joindate = joindate;
	}

	public int getShiyongqi()
	{
		return shiyongqi;
	}

	public void setShiyongqi(int shiyongqi)
	{
		this.shiyongqi = shiyongqi;
	}

	public String getZhuanzhengdate()
	{
		return zhuanzhengdate;
	}

	public void setZhuanzhengdate(String zhuanzhengdate)
	{
		this.zhuanzhengdate = zhuanzhengdate;
	}

	public int getShiyonggongzi()
	{
		return shiyonggongzi;
	}

	public void setShiyonggongzi(int shiyonggongzi)
	{
		this.shiyonggongzi = shiyonggongzi;
	}

	public int getZhengshigongzi()
	{
		return zhengshigongzi;
	}

	public void setZhengshigongzi(int zhengshigongzi)
	{
		this.zhengshigongzi = zhengshigongzi;
	}

	public String getKeshigongzi()
	{
		return keshigongzi;
	}

	public void setKeshigongzi(String keshigongzi)
	{
		this.keshigongzi = keshigongzi;
	}

	public String getBaoxianxinxi()
	{
		return baoxianxinxi;
	}

	public void setBaoxianxinxi(String baoxianxinxi)
	{
		this.baoxianxinxi = baoxianxinxi;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getLeavedate()
	{
		return leavedate;
	}

	public void setLeavedate(String leavedate)
	{
		this.leavedate = leavedate;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getWorktypeName()
	{
		return worktypeName;
	}

	public void setWorktypeName(String worktypeName)
	{
		this.worktypeName = worktypeName;
	}

	public String getSexName()
	{
		return sexName;
	}

	public void setSexName(String sexName)
	{
		this.sexName = sexName;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getXueliName()
	{
		return xueliName;
	}

	public void setXueliName(String xueliName)
	{
		this.xueliName = xueliName;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getIdcard()
	{
		return idcard;
	}

	public void setIdcard(String idcard)
	{
		this.idcard = idcard;
	}

	public static class MOMapper implements RowMapper<StaffMO>
	{
		@Override
		public StaffMO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			StaffMO mo = new StaffMO();

			mo.setId(rs.getString("id"));
			mo.setName(rs.getString("name"));
			mo.setMobile(rs.getString("mobile"));
			mo.setIdcard(rs.getString("idcard"));
			mo.setWorktype(rs.getString("worktype"));
			mo.setSex(rs.getString("sex"));
			mo.setAge(rs.getInt("age"));
			mo.setBiyexuexiao(rs.getString("biyexuexiao"));
			mo.setBiyeshijian(rs.getString("biyeshijian"));
			mo.setXueli(rs.getString("xueli"));
			mo.setZhuanye(rs.getString("zhuanye"));
			mo.setTechangjirongyu(rs.getString("techangjirongyu"));
			mo.setJoindate(rs.getString("joindate"));
			mo.setShiyongqi(rs.getInt("shiyongqi"));
			mo.setZhuanzhengdate(rs.getString("zhuanzhengdate"));
			mo.setShiyonggongzi(rs.getInt("shiyonggongzi"));
			mo.setZhengshigongzi(rs.getInt("zhengshigongzi"));
			mo.setKeshigongzi(rs.getString("keshigongzi"));
			mo.setBaoxianxinxi(rs.getString("baoxianxinxi"));
			mo.setRemark(rs.getString("remark"));
			mo.setLeavedate(rs.getString("leavedate"));
			mo.setState(rs.getString("state"));
			mo.setCreateTime(StringUtil.replace(rs.getString("createTime"), ".0", ""));

			// for display
			mo.setWorktypeName(StaffWorkType.getNameByCode(mo.getWorktype()));
			mo.setSexName(Sex.getNameByCode(mo.getSex()));
			mo.setXueliName(XueLi.getNameByCode(mo.getXueli()));
			mo.setStateName(StaffState.getNameByCode(mo.getState()));

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
