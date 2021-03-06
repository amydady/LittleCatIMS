package com.littlecat.ims.system.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.query.QueryParam;
import com.littlecat.cbb.utils.CollectionUtil;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.cbb.utils.UUIDUtil;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.common.utils.DaoUtil;
import com.littlecat.ims.system.model.SysOperatorMO;
@Component
public class SysOperatorDao
{
	private final String TABLE_NAME = TableName.SysOperator.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

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

	public SysOperatorMO login(String identity, String pwd) throws LittleCatException
	{
		String sql = "select * from " + TABLE_NAME + " where username=? and enable = 'Y' and password=password(?)";

		try
		{
			List<SysOperatorMO> mos = jdbcTemplate.query(sql, new Object[] { identity, pwd }, new SysOperatorMO.MOMapper());

			if (CollectionUtil.isEmpty(mos))
			{
				throw new LittleCatException("login error" + " identity=" + identity);
			}

			return mos.get(0);
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public void setPassword(String id, String pwd) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set password = password(?) where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { pwd, id });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public void changePassword(String id, String oldPwd, String pwd) throws LittleCatException
	{
		String sql = "select count(1) from " + TABLE_NAME + " where password = password(?) and id = ?";

		int count = jdbcTemplate.queryForObject(sql, new Object[] { oldPwd, id }, Integer.class);

		if (count < 1)
		{
			throw new LittleCatException(Consts.ERROR_CODE_OLDPWDISERROR, "old password is error");
		}

		sql = "update " + TABLE_NAME + " set password = password(?) where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { pwd, id });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public String add(SysOperatorMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,username,password,name) values(?,?,password(?),?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getUsername(), mo.getPassword(), mo.getName() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public void modify(SysOperatorMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set name = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getName(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public SysOperatorMO getById(String id) throws LittleCatException
	{
		return DaoUtil.getById(TABLE_NAME, id, jdbcTemplate, new SysOperatorMO.MOMapper());
	}

	public int getList(QueryParam queryParam, List<SysOperatorMO> mos) throws LittleCatException
	{
		return DaoUtil.getList(TABLE_NAME, queryParam, mos, jdbcTemplate, new SysOperatorMO.MOMapper());
	}
}
