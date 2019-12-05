package com.littlecat.ims.system.dao;

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
import com.littlecat.ims.common.utils.DaoUtil;
import com.littlecat.ims.system.model.QianDaoMO;

@Component
public class QianDaoDao
{
	private final String TABLE_NAME = TableName.QianDao.getName();
	private final String TABLE_NAME_SYSOPERATOR = TableName.SysOperator.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public List<QianDaoMO> getList(String userId, String userName, String date) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name userName from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " b on a.userid = b.id ")
				.append(" where 1 = 1");

		if (StringUtil.isNotEmpty(userId))
		{
			sql.append(" and a.userid = '" + userId + "'");
		}

		if (StringUtil.isNotEmpty(userName))
		{
			sql.append(" and b.name like '%" + userName + "%'");
		}

		if (StringUtil.isNotEmpty(date))
		{
			date += "01";
			sql.append(" and date_format(a.createTime,'%Y%m')=date_format('" + date + "','%Y%m')");
		}

		sql.append(" order by a.createTime desc");

//		System.out.println(sql);
		
		return jdbcTemplate.query(sql.toString(), new QianDaoMO.MOMapper());
	}

	public boolean exist(String userid, String date) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*  from ").append(TABLE_NAME).append(" a ");
		sql.append(" where a.userid = '" + userid + "' and  date_format(a.createTime,'%Y%m%d')=date_format('" + date + "','%Y%m%d')");

		return CollectionUtil.isNotEmpty(jdbcTemplate.query(sql.toString(), new QianDaoMO.MOMapper()));
	}

	public void delete(String id) throws LittleCatException
	{
		DaoUtil.delete(TABLE_NAME, id, jdbcTemplate);
	}

	public String add(QianDaoMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,userid) values(?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getUserid() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}
}
