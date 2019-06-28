package com.littlecat.ims.system.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.system.model.SysParamMO;

@Component
public class SysParamDao
{
	private final String TABLE_NAME = TableName.SysParam.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void modify(SysParamMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set value = ? where name = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getValue(), mo.getName() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public SysParamMO getValueByName(String name) throws LittleCatException
	{
		String sql = "select * from " + TABLE_NAME + " where name = ?";

		try
		{
			return jdbcTemplate.queryForObject(sql, new Object[] { name }, new SysParamMO.MOMapper());
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public List<SysParamMO> getList() throws LittleCatException
	{
		String sql = "select * from " + TABLE_NAME;

		try
		{
			return jdbcTemplate.query(sql, new SysParamMO.MOMapper());
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}
}
