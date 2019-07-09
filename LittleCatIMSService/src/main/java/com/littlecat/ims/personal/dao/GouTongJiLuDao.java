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
import com.littlecat.ims.common.consts.GouTongJiLuState;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.personal.model.GouTongJiLuMO;

@Component
public class GouTongJiLuDao
{
	private final String TABLE_NAME = TableName.GouTongJiLu.getName();
	private final String TABLE_NAME_SYSOPERATOR = TableName.SysOperator.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void setState(String id, GouTongJiLuState state) throws LittleCatException
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

	public String add(GouTongJiLuMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,studentName,topic,content,laterplan,remark,state,operator) values(?,?,?,?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getStudentName(), mo.getTopic(), mo.getContent(), mo.getLaterplan(), mo.getRemark(), GouTongJiLuState.jinxingzhong.getCode(), mo.getOperator() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public void modify(GouTongJiLuMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set studentName = ?,topic = ?,content = ?,laterplan = ?,remark = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getStudentName(), mo.getTopic(), mo.getContent(), mo.getLaterplan(), mo.getRemark(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public GouTongJiLuMO getById(String id) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name operatorName from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " b on a.operator = b.id ")
				.append(" where a.id = ?");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[] { id }, new GouTongJiLuMO.MOMapper());
	}

	public List<GouTongJiLuMO> getList(String key, String state) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name operatorName from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " b on a.operator = b.id ")
				.append(" where 1 = 1 ");

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" and a.studentName like '%" + key + "%'");
		}

		if (StringUtil.isNotEmpty(state))
		{
			sql.append(" and a.state = '" + state + "'");
		}

		sql.append(" order by a.createTime desc");

		return jdbcTemplate.query(sql.toString(), new GouTongJiLuMO.MOMapper());
	}
}
