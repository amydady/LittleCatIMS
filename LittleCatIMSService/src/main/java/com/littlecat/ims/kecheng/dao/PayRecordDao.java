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
import com.littlecat.ims.kecheng.model.PayRecordMO;

@Component
public class PayRecordDao
{
	private final String TABLE_NAME = TableName.PayRecord.getName();
	private final String TABLE_NAME_KECHENG = TableName.Kecheng.getName();
	private final String TABLE_NAME_STUDENT = TableName.Student.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public String add(PayRecordMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,student,fee,times,remark) values(?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getStudent(), mo.getFee(),mo.getTimes(), mo.getRemark() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public void modify(PayRecordMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set student=?,fee=?,times=?,remark=? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getStudent(), mo.getFee(),mo.getTimes(), mo.getRemark(), mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public PayRecordMO getById(String id) throws LittleCatException
	{
		return DaoUtil.getById(TABLE_NAME, id, jdbcTemplate, new PayRecordMO.MOMapper());
	}

	public List<PayRecordMO> getList(String studentId,String studentName) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name studentName,c.name kechengName from ").append(TABLE_NAME).append(" a ")
				.append(" inner join "+TABLE_NAME_STUDENT+" b on a.student = b.id")
				.append(" inner join " +TABLE_NAME_KECHENG+" c on a.kecheng=c.id ");

		sql.append(" where 1 = 1 ");

		if (StringUtil.isNotEmpty(studentName))
		{
			sql.append(" and b.name like'%" + studentName + "%'");
		}

		if (StringUtil.isNotEmpty(studentId))
		{
			sql.append(" and b.id = '" + studentId + "'");
		}

		return jdbcTemplate.query(sql.toString(), new PayRecordMO.MOMapper());
	}
}
