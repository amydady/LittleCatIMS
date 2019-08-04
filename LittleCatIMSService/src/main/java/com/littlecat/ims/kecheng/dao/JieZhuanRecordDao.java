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
import com.littlecat.ims.kecheng.model.JieZhuanRecordMO;

@Component
public class JieZhuanRecordDao
{
	private final String TABLE_NAME = TableName.JieZhuanRecord.getName();
	private final String TABLE_NAME_STUDENT = TableName.Student.getName();
	private final String TABLE_NAME_KECHENG = TableName.Kecheng.getName();
	private final String TABLE_NAME_SYSOPERATOR = TableName.SysOperator.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public String add(JieZhuanRecordMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,student,kechengs,kechengd,times,operator) values(?,?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getStudent(), mo.getKechengs(), mo.getKechengd(), mo.getTimes(), mo.getOperator() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public List<JieZhuanRecordMO> getList(String kechengs, String kechengd, String student, String key) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name studentName,c.name kechengsName,d.name kechengdName,e.name operatorName  from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_STUDENT + " b on a.student = b.id ")
				.append(" inner join " + TABLE_NAME_KECHENG + " c on a.kechengs = c.id ")
				.append(" inner join " + TABLE_NAME_KECHENG + " d on a.kechengd = d.id ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " e on a.operator = e.id ")
				.append(" where 1=1 ");

		if (StringUtil.isNotEmpty(student))
		{
			sql.append(" and a.student = '" + student + "' ");
		}

		if (StringUtil.isNotEmpty(kechengs))
		{
			sql.append(" and a.kechengs = '" + kechengs + "' ");
		}

		if (StringUtil.isNotEmpty(kechengd))
		{
			sql.append(" and a.kechengd = '" + kechengd + "' ");
		}

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" and (b.name like '%" + key + "%' or c.name like '%" + key + "%' or d.name like '%" + key + "%' )");
		}

		return jdbcTemplate.query(sql.toString(), new JieZhuanRecordMO.MOMapper());
	}
}
