package com.littlecat.ims.kecheng.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.CollectionUtil;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.cbb.utils.UUIDUtil;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.kecheng.model.KeChengStudentMO;

@Component
public class KeChengStudentDao
{
	private final String TABLE_NAME = TableName.KeChengStudent.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public String add(KeChengStudentMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,kecheng,student,remark) values(?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getKecheng(), mo.getStudent(), mo.getRemark() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public List<KeChengStudentMO> getByKeCheng(String kecheng) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.* from ").append(TABLE_NAME).append(" a ");

		sql.append(" where a.kecheng = ? ");

		return jdbcTemplate.query(sql.toString(), new Object[] { kecheng }, new KeChengStudentMO.MOMapper());
	}

	public List<KeChengStudentMO> getByStudent(String student) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.* from ").append(TABLE_NAME).append(" a ");

		sql.append(" where a.student = ? ");

		return jdbcTemplate.query(sql.toString(), new Object[] { student }, new KeChengStudentMO.MOMapper());
	}

	public boolean exists(String kecheng, String student) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.* from ").append(TABLE_NAME).append(" a ")
				.append(" where a.kecheng = ? and a.student = ?");

		List<KeChengStudentMO> list = jdbcTemplate.query(sql.toString(), new Object[] { kecheng, student }, new KeChengStudentMO.MOMapper());

		return CollectionUtil.isNotEmpty(list);
	}

	public void modify(KeChengStudentMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set remaintimes = ?,remark = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getRemaintimes(), mo.getRemark(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	// public void delete(List<String> ids) throws LittleCatException
	// {
	// DaoUtil.delete(TABLE_NAME, ids, jdbcTemplate);
	// }
}
