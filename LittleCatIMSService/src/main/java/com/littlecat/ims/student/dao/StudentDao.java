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
import com.littlecat.ims.student.model.StudentMO;

@Component
public class StudentDao
{
	private final String TABLE_NAME = TableName.Student.getName();

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

	public String add(StudentMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,name,mobile,xuexiao,nianji,banji,xiaoqu,tuijianren,remark) values(?,?,?,?,?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getName(), mo.getMobile(), mo.getXuexiao(), mo.getNianji(), mo.getBanji(), mo.getXiaoqu(),mo.getTuijianren(), mo.getRemark() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public void modify(StudentMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set name=?,mobile=?,xuexiao=?,nianji=?,banji=?,xiaoqu=?,tuijianren=?,remark=? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getName(), mo.getMobile(), mo.getXuexiao(), mo.getNianji(), mo.getBanji(), mo.getXiaoqu(),mo.getTuijianren(), mo.getRemark(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public StudentMO getById(String id) throws LittleCatException
	{
		return DaoUtil.getById(TABLE_NAME, id, jdbcTemplate, new StudentMO.MOMapper());
	}

	public List<StudentMO> getList(String key) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.* from ").append(TABLE_NAME).append(" a ");

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" where a.name like '%" + key + "%'");
		}

		return jdbcTemplate.query(sql.toString(), new StudentMO.MOMapper());
	}
}
