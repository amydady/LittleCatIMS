package com.littlecat.ims.personal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.cbb.utils.UUIDUtil;
import com.littlecat.ims.common.consts.LeaveRecordState;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.common.utils.DaoUtil;
import com.littlecat.ims.personal.model.LeaveRecordMO;

@Component
public class LeaveRecordDao
{
	private final String TABLE_NAME = TableName.LeaveRecord.getName();
	private final String TABLE_NAME_SYSOPERATOR = TableName.SysOperator.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void setState(String id,LeaveRecordState state) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set state = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { state.getCode(), id });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public String add(LeaveRecordMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,staff,leavedate,remark,state) values(?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getStaff(), mo.getLeavedate(), mo.getRemark(), LeaveRecordState.待审批.getCode() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public void modify(LeaveRecordMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set leavedate = ?,remark = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getLeavedate(), mo.getRemark(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public LeaveRecordMO getById(String id) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name staffName  from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " b on a.staff = b.id ")
				.append(" where a.id = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[] { id }, new LeaveRecordMO.MOMapper());
	}
	
	public void delete(String id) throws LittleCatException
	{
		DaoUtil.delete(TABLE_NAME, id, jdbcTemplate);
	}

	public List<LeaveRecordMO> getList(String staff, String key, String state, String beginDate, String endDate) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name staffName  from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " b on a.staff = b.id ")
				.append(" where 1 = 1 ");

		if (StringUtil.isNotEmpty(staff))
		{
			sql.append(" and a.staff ='" + staff + "'");
		}

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" and b.name like '%" + key + "%'");
		}

		if (StringUtil.isNotEmpty(state))
		{
			sql.append(" and a.state = '" + state + "'");
		}

		if (StringUtil.isNotEmpty(beginDate))
		{
			sql.append(" and date_format(a.leavedate,'%Y%m%d') >= " + "date_format('" + beginDate + "','%Y%m%d')");
		}

		if (StringUtil.isNotEmpty(endDate))
		{
			sql.append(" and date_format(a.leavedate,'%Y%m%d') <= " + "date_format('" + endDate + "','%Y%m%d')");
		}

		sql.append(" order by a.createTime desc");

		return jdbcTemplate.query(sql.toString(), new LeaveRecordMO.MOMapper());
	}
}
