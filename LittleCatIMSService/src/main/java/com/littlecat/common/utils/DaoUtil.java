package com.littlecat.common.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.query.QueryParam;
import com.littlecat.cbb.utils.CollectionUtil;

public final class DaoUtil
{
	private final static String FIELD_NAME_ENABLE = "enable";

	public static class TotalNumMapper implements RowMapper<Integer>
	{
		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			return rs.getInt(Consts.COMMON_DB_RESULT_FIELDS_TOTALNUM);
		}
	}

	private DaoUtil()
	{

	}

	public static void delete(String tableName, String id, JdbcTemplate jdbcTemplate) throws LittleCatException
	{
		String sql = "delete from " + tableName + " where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { id });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}

	public static void delete(String tableName, List<String> ids, JdbcTemplate jdbcTemplate) throws LittleCatException
	{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

		String sql = "delete from " + tableName + " where id in (:ids)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ids", ids);

		try
		{
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}

	public static void disable(String tableName, String id, JdbcTemplate jdbcTemplate) throws LittleCatException
	{
		String sql = "update " + tableName + " set " + FIELD_NAME_ENABLE + " = 'N' where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { id });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}

	public static void disable(String tableName, List<String> ids, JdbcTemplate jdbcTemplate) throws LittleCatException
	{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

		String sql = "update " + tableName + " set " + FIELD_NAME_ENABLE + " = 'N' where id in (:ids)";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ids", ids);

		try
		{
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}

	public static void enable(String tableName, String id, JdbcTemplate jdbcTemplate) throws LittleCatException
	{
		String sql = "update " + tableName + " set " + FIELD_NAME_ENABLE + " = 'Y' where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { id });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}

	public static void enable(String tableName, List<String> ids, JdbcTemplate jdbcTemplate) throws LittleCatException
	{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

		String sql = "update " + tableName + " set " + FIELD_NAME_ENABLE + " = 'Y' where id in (:ids)";

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ids", ids);

		try
		{
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}

	public static <T_MO> T_MO getById(String tableName, String id, JdbcTemplate jdbcTemplate, RowMapper<T_MO> rowMapper) throws LittleCatException
	{
		String sql = "select * from " + tableName + " where id = ?";

		try
		{
			return jdbcTemplate.queryForObject(sql, new Object[] { id }, rowMapper);
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}

	public static <T_MO> int getList(String tableName, QueryParam queryParam, List<T_MO> mos, JdbcTemplate jdbcTemplate, RowMapper<T_MO> rowMapper) throws LittleCatException
	{
		if (queryParam == null)
		{
			throw new LittleCatException(Consts.ERROR_CODE_QUERYPARAMISNULL, Consts.ERROR_CODE_QUERYPARAMISNULL);
		}

		if (mos == null)
		{
			throw new LittleCatException("the param mos for filling the data can not be null.");
		}

		String sql = "select * from " + tableName + queryParam.getQueryDataConditionString();

		try
		{
			mos.addAll(jdbcTemplate.query(sql, rowMapper));
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}

		return DaoUtil.getTotalNum(tableName, queryParam, jdbcTemplate);
	}

	public static <T_MO> T_MO getObject(String tableName, QueryParam queryParam, JdbcTemplate jdbcTemplate, RowMapper<T_MO> rowMapper) throws LittleCatException
	{
		if (queryParam == null)
		{
			throw new LittleCatException(Consts.ERROR_CODE_QUERYPARAMISNULL, Consts.ERROR_CODE_QUERYPARAMISNULL);
		}

		List<T_MO> mos = new ArrayList<T_MO>();
		getList(tableName, queryParam, mos, jdbcTemplate, rowMapper);

		if (CollectionUtil.isEmpty(mos))
		{
			return null;
		}

		return mos.get(0);
	}

	public static int getTotalNum(String tableName, QueryParam queryParam, JdbcTemplate jdbcTemplate) throws LittleCatException
	{
		String sql = "select count(*) totalNum	from " + tableName;

		if (queryParam != null)
		{
			sql += queryParam.getQueryCountConditionString();
		}

		try
		{
			return jdbcTemplate.queryForObject(sql, new DaoUtil.TotalNumMapper());
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, Consts.ERROR_CODE_DATAACCESSEXCEPTION, e);
		}
	}
}
