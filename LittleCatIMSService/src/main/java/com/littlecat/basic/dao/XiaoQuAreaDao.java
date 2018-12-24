package com.littlecat.basic.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.basic.model.XiaoQuAreaMO;
import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.cbb.utils.UUIDUtil;
import com.littlecat.common.consts.TableName;
import com.littlecat.common.utils.DaoUtil;

@Component
public class XiaoQuAreaDao
{
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	private final String TABLE_NAME = TableName.XiaoQuArea.getName();

	public XiaoQuAreaMO getById(String id) throws LittleCatException
	{
		return DaoUtil.getById(TABLE_NAME, id, jdbcTemplate, new XiaoQuAreaMO.MOMapper());
	}

	public void enable(String id) throws LittleCatException
	{
		DaoUtil.enable(TABLE_NAME, id, jdbcTemplate);
	}

	public void enable(List<String> ids) throws LittleCatException
	{
		DaoUtil.enable(TABLE_NAME, ids, jdbcTemplate);
	}

	public void disable(String id) throws LittleCatException
	{
		DaoUtil.disable(TABLE_NAME, id, jdbcTemplate);
	}

	public void disable(List<String> ids) throws LittleCatException
	{
		DaoUtil.disable(TABLE_NAME, ids, jdbcTemplate);
	}

	public String add(XiaoQuAreaMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,name) values(?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getName() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}

		return mo.getId();
	}

	public void modify(XiaoQuAreaMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set name = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getName(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}

	public List<XiaoQuAreaMO> getList(String name, String enable) throws LittleCatException
	{
		String sql = "select * from  " + TABLE_NAME + " where enable = ? ";

		if (StringUtil.isNotEmpty(name))
		{
			sql += " and name like '%" + name + "%'";
		}

		try
		{
			return jdbcTemplate.query(sql, new Object[] { enable }, new XiaoQuAreaMO.MOMapper());
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}
}
