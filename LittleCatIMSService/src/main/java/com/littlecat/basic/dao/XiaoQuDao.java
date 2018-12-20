package com.littlecat.basic.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.basic.model.XiaoQuMO;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.cbb.utils.UUIDUtil;
import com.littlecat.common.consts.ErrorCode;
import com.littlecat.common.consts.TableName;
import com.littlecat.common.utils.DaoUtil;

@Component
public class XiaoQuDao
{
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	private final String TABLE_NAME = TableName.XiaoQu.getName();

	public XiaoQuMO getById(String id) throws LittleCatException
	{
		return DaoUtil.getById(TABLE_NAME, id, jdbcTemplate, new XiaoQuMO.MOMapper());
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

	public String add(XiaoQuMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,area,name,code) values(?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getArea(), mo.getName(), mo.getCode() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(ErrorCode.DataAccessException.getCode(), ErrorCode.DataAccessException.getMsg(), e);
		}

		return mo.getId();
	}

	public void modify(XiaoQuMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set name = ?,area = ?,code = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getName(), mo.getArea(), mo.getCode(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(ErrorCode.DataAccessException.getCode(), ErrorCode.DataAccessException.getMsg(), e);
		}
	}

	public List<XiaoQuMO> getListByArea(String area) throws LittleCatException
	{
		return null;
	}
}
