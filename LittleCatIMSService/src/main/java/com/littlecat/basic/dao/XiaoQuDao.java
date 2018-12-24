package com.littlecat.basic.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.basic.model.XiaoQuMO;
import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.cbb.utils.UUIDUtil;
import com.littlecat.common.consts.TableName;
import com.littlecat.common.utils.DaoUtil;

@Component
public class XiaoQuDao
{
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	private final String TABLE_NAME = TableName.XiaoQu.getName();
	private final String TABLE_NAME_XIAOQUAREA = TableName.XiaoQuArea.getName();

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

		String sql = "insert into " + TABLE_NAME + "(id,area,name) values(?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getArea(), mo.getName() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}

		return mo.getId();
	}

	public void modify(XiaoQuMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set name = ?,area = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getName(), mo.getArea(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}

	public List<XiaoQuMO> getList(String area, String name, String enable) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name areaName from  " + TABLE_NAME + " a ")
				.append(" left join " + TABLE_NAME_XIAOQUAREA + " b on a.area=b.id ")
				.append(" where a.enable = ?  ");

		if (!"-1".equals(area))
		{
			sql.append(" and a.area ='" + area + "'");
		}

		if (StringUtil.isNotEmpty(name))
		{
			sql.append(" and a.name like '%" + name + "%' ");
		}

		sql.append(" order by a.name");

		try
		{
			return jdbcTemplate.query(sql.toString(), new Object[] { enable }, new XiaoQuMO.MOMapper());
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}
}
