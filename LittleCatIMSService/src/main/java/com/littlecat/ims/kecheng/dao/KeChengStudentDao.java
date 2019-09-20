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
import com.littlecat.ims.common.consts.StudentKeChengState;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.kecheng.model.KeChengStudentMO;

@Component
public class KeChengStudentDao
{
	private final String TABLE_NAME = TableName.KeChengStudent.getName();
	private final String TABLE_NAME_STUDENT = TableName.Student.getName();
	private final String TABLE_NAME_KECHENG = TableName.Kecheng.getName();
	private final String TABLE_NAME_SYSOPERATOR = TableName.SysOperator.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public String add(KeChengStudentMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,kecheng,student,remaintimes,state) values(?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getKecheng(), mo.getStudent(), mo.getRemaintimes(), mo.getState() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public KeChengStudentMO getById(String id) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.* from ").append(TABLE_NAME).append(" a ");

		sql.append(" where a.id = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[] { id }, new KeChengStudentMO.MOMapper());
	}

	public List<KeChengStudentMO> getByKeCheng(String kecheng, String state, String key) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name studentName  from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_STUDENT + " b on a.student = b.id ");

		sql.append(" where a.kecheng = ? ");

		if (StringUtil.isNotEmpty(state))
		{
			sql.append(" and a.state = '" + state + "'");
		}

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" and b.name like '%" + key + "%' ");
		}
		
		sql.append(" order by a.remaintimes desc");

		return jdbcTemplate.query(sql.toString(), new Object[] { kecheng }, new KeChengStudentMO.MOMapper());
	}

	public KeChengStudentMO getByKeChengAndStudent(String kecheng, String student) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name studentName  from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_STUDENT + " b on a.student = b.id ");

		sql.append(" where a.kecheng = ? and a.student = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[] { kecheng, student }, new KeChengStudentMO.MOMapper());
	}

	public List<KeChengStudentMO> getByStudent(String student, String state, String key) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name kechengName,b.shangkeshijian shangkeshijian,c.name teacherName  from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_KECHENG + " b on a.kecheng = b.id ")
				.append(" left join " + TABLE_NAME_SYSOPERATOR + " c on b.teacher = c.id");
		
		

		sql.append(" where a.student = ? ");

		if (StringUtil.isNotEmpty(state))
		{
			sql.append(" and a.state = ? ");
		}

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" and b.name like '%" + key + "%' ");
		}
		
		sql.append(" order by a.remaintimes desc");

		return jdbcTemplate.query(sql.toString(), new Object[] { student, state }, new KeChengStudentMO.MOMapper());
	}

	public List<KeChengStudentMO> getNoEndTimesByKecheng(String kecheng) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.* from ").append(TABLE_NAME).append(" a ");

		sql.append(" where a.kecheng = ? and a.remaintimes != 0 ");

		return jdbcTemplate.query(sql.toString(), new Object[] { kecheng }, new KeChengStudentMO.MOMapper());
	}

	public List<KeChengStudentMO> getTimesRemainInfo(String key) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name studentName,c.name kechengName  from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_STUDENT + " b on a.student = b.id ")
				.append(" inner join " + TABLE_NAME_KECHENG + " c on a.kecheng = c.id ");

		sql.append(" where a.remaintimes >0 and a.remaintimes <= 3 and c.enable='Y' ");

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" and (b.name like '%" + key + "%' or  c.name like '%" + key + "%')");
		}

		sql.append(" order by c.name,a.remaintimes");

		return jdbcTemplate.query(sql.toString(), new KeChengStudentMO.MOMapper());
	}

	public String exists(String kecheng, String student) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.* from ").append(TABLE_NAME).append(" a ")
				.append(" where a.kecheng = ? and a.student = ?");

		List<KeChengStudentMO> list = jdbcTemplate.query(sql.toString(), new Object[] { kecheng, student }, new KeChengStudentMO.MOMapper());

		if (CollectionUtil.isNotEmpty(list))
		{
			return list.get(0).getId();
		}

		return null;
	}

	public void modify(KeChengStudentMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set kecheng = ?,student=?,remaintimes = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getKecheng(), mo.getStudent(), mo.getRemaintimes(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public void zanting(String id) throws LittleCatException
	{
		setState(id, StudentKeChengState.zanting);
	}

	public void huifu(String id) throws LittleCatException
	{
		setState(id, StudentKeChengState.zhengchang);
	}

	public void end(String id) throws LittleCatException
	{
		setState(id, StudentKeChengState.jieshu);
	}

	private void setState(String id, StudentKeChengState state) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set state = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { state.getCode(), id });
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
