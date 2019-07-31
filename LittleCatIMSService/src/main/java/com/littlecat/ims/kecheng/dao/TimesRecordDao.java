package com.littlecat.ims.kecheng.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.cbb.utils.UUIDUtil;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.common.utils.DaoUtil;
import com.littlecat.ims.kecheng.model.TimesRecordMO;

@Component
public class TimesRecordDao
{
	private final String TABLE_NAME = TableName.TimesRecord.getName();
	private final String TABLE_NAME_STUDENT = TableName.Student.getName();
	private final String TABLE_NAME_KECHENG = TableName.Kecheng.getName();
	private final String TABLE_NAME_SYSOPERATOR = TableName.SysOperator.getName();

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
	
	public String add(TimesRecordMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,kecheng,student,year,month,day,starttime,remark,operator) values(?,?,?,?,?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getKecheng(), mo.getStudent(), mo.getYear(), mo.getMonth(), mo.getDay(),mo.getStarttime(), mo.getRemark(), mo.getOperator() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public TimesRecordMO getById(String id) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.* from ").append(TABLE_NAME).append(" a ");

		sql.append(" where a.id = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[] { id }, new TimesRecordMO.MOMapper());
	}

	public List<TimesRecordMO> getList(String kecheng, String student, String year, String month, String day, String operator,String key) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name studentName,c.name kechengName,d.name operatorName  from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_STUDENT + " b on a.student = b.id ")
				.append(" inner join " + TABLE_NAME_KECHENG + " c on a.kecheng = c.id ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " d on a.operator = d.id ")
				.append(" where 1 = 1 ");

		if (StringUtil.isNotEmpty(kecheng))
		{
			sql.append(" and a.kecheng = '" + kecheng + "' ");
		}

		if (StringUtil.isNotEmpty(student))
		{
			sql.append(" and a.student = '" + student + "' ");
		}

		if (StringUtil.isNotEmpty(year))
		{
			sql.append(" and a.year = '" + year + "' ");
		}

		if (StringUtil.isNotEmpty(month))
		{
			sql.append(" and a.month = '" + month + "' ");
		}

		if (StringUtil.isNotEmpty(day))
		{
			sql.append(" and a.day = '" + day + "' ");
		}

		if (StringUtil.isNotEmpty(operator))
		{
			sql.append(" and a.operator = '" + operator + "' ");
		}
		
		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" and (b.name like '%" + key + "%' or c.name like '%"+key+"%') ");
		}
		
		sql.append(" order by b.name,a.year,a.month,a.day");
		
		return jdbcTemplate.query(sql.toString(), new TimesRecordMO.MOMapper());
	}

	public void modify(TimesRecordMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set year = ?,month = ?,day = ?,starttime = ?,remark = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getYear(), mo.getMonth(), mo.getDay(),mo.getStarttime(), mo.getRemark(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public void delete(List<String> ids) throws LittleCatException
	{
		DaoUtil.delete(TABLE_NAME, ids, jdbcTemplate);
	}
}
