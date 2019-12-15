package com.littlecat.ims.student.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.cbb.utils.UUIDUtil;
import com.littlecat.common.consts.CommissionState;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.common.utils.DaoUtil;
import com.littlecat.ims.student.model.YongCanStudentMO;

@Component
public class YongCanStudentDao
{
	private final String TABLE_NAME = TableName.YongCanStudent.getName();
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

	public String add(YongCanStudentMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,student,remark) values(?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getStudent(),  mo.getRemark() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}
	
	public void add(List<String> studentIds) throws LittleCatException
	{

		String sql = "insert into " + TABLE_NAME + "(id,student) values(?,?)";
		
		List<Object[]> batchParams = new ArrayList<Object[]>();
		for(String student:studentIds)
		{
			batchParams.add(new Object[] { UUIDUtil.createUUID(), student });
		}

		try
		{
			jdbcTemplate.batchUpdate(sql, batchParams);
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public List<YongCanStudentMO> getList(String key) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name StudentName from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_STUDENT + " b on a.student = b.id ");

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" where b.name like '%" + key + "%'");
		}

		sql.append(" order by b.name desc ");

		return jdbcTemplate.query(sql.toString(), new YongCanStudentMO.MOMapper());
	}
}
