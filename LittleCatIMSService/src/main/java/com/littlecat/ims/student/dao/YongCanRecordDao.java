package com.littlecat.ims.student.dao;

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
import com.littlecat.ims.student.model.YongCanRecordMO;

@Component
public class YongCanRecordDao
{
	private final String TABLE_NAME = TableName.YongCanRecord.getName();
	private final String TABLE_NAME_STUDENT = TableName.Student.getName();
	private final String TABLE_NAME_SYSOPERATOR = TableName.SysOperator.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void delete(String id) throws LittleCatException
	{
		DaoUtil.delete(TABLE_NAME, id, jdbcTemplate);
	}

	public void delete(List<String> ids) throws LittleCatException
	{
		DaoUtil.delete(TABLE_NAME, ids, jdbcTemplate);
	}

	public String add(YongCanRecordMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,student,year,month,day,remark,operator) values(?,?,?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getStudent(), mo.getYear(), mo.getMonth(), mo.getDay(), mo.getRemark(), mo.getOperator() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public List<YongCanRecordMO> getList(String student, String year, String month, String day, String operator, String key) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name studentName,d.name operatorName  from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_STUDENT + " b on a.student = b.id ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " d on a.operator = d.id ")
				.append(" where 1 = 1 ");

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
			sql.append(" and (b.name like '%" + key + "%' or c.name like '%" + key + "%') ");
		}

		sql.append(" order by a.year desc,a.month desc ,a.day asc,b.name");

		return jdbcTemplate.query(sql.toString(), new YongCanRecordMO.MOMapper());
	}
}
