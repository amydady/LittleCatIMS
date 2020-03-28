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
import com.littlecat.ims.kecheng.model.KeChengMO;

@Component
public class KeChengDao
{
	private final String TABLE_NAME = TableName.Kecheng.getName();
	private final String TABLE_NAME_SYSOPERATOR = TableName.SysOperator.getName();
	private final String TABLE_NAME_DICCONTENT = TableName.DicContent.getName();

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

	public void close(String id) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set enable='C' where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { id });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public String add(KeChengMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,name,teacher,nianji,kemu,remark,shangkeshijian) values(?,?,?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getName(), mo.getTeacher(), mo.getNianji(), mo.getKemu(), mo.getRemark(), mo.getShangkeshijian() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public void modify(KeChengMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set name=?,teacher=?,nianji = ?,kemu = ?,remark=?,shangkeshijian = ?,needremind = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getName(), mo.getTeacher(), mo.getNianji(), mo.getKemu(), mo.getRemark(), mo.getShangkeshijian(), mo.getNeedremind(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public void setRemindTag(String id, String tag) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set needremind=? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { tag, id });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public KeChengMO getById(String id) throws LittleCatException
	{
		return DaoUtil.getById(TABLE_NAME, id, jdbcTemplate, new KeChengMO.MOMapper());
	}

	public List<KeChengMO> getList(String key, String teacher, String enable, String needremind) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name teacherName,c.name nianjiName,d.name kemuName from ").append(TABLE_NAME).append(" a ")
				.append(" left join " + TABLE_NAME_SYSOPERATOR + " b on a.teacher = b.id ")
				.append(" left join " + TABLE_NAME_DICCONTENT + " c on a.nianji = c.id and c.typeid = '2' ")
				.append(" left join " + TABLE_NAME_DICCONTENT + " d on a.kemu = d.id and d.typeid = '6' ");

		sql.append(" where 1 = 1 ");

		if (StringUtil.isNotEmpty(teacher))
		{
			sql.append(" and a.teacher ='" + teacher + "'");
		}

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" and a.name like '%" + key + "%'");
		}

		if (StringUtil.isNotEmpty(enable))
		{
			sql.append(" and a.enable ='" + enable + "'");
		}

		if (StringUtil.isNotEmpty(needremind))
		{
			sql.append(" and a.needremind ='" + needremind + "'");
		}

		sql.append(" order by d.name,c.name ");

		return jdbcTemplate.query(sql.toString(), new KeChengMO.MOMapper());
	}
}
