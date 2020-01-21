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
import com.littlecat.ims.kecheng.model.KeChengBiaoMO;

@Component
public class KeChengBiaoDao
{
	private final String TABLE_NAME = TableName.KeChengBiao.getName();
	private final String TABLE_NAME_KECHENG = TableName.Kecheng.getName();
	private final String TABLE_NAME_DICCONTENT = TableName.DicContent.getName();
	private final String TABLE_NAME_KECHENGSTUDENT = TableName.KeChengStudent.getName();
	private final String TABLE_NAME_SYSOPERATOR = TableName.SysOperator.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void delete(String id) throws LittleCatException
	{
		DaoUtil.delete(TABLE_NAME, id, jdbcTemplate);
	}

	public String add(KeChengBiaoMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,kecheng,fixdate,begindate,enddate,weekday,cycle,timebeginhour,timebeginmin,timeendhour,timeendmin) values(?,?,?,?,?,?,?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getKecheng(), mo.getFixdate(), mo.getBegindate(), mo.getEnddate(), mo.getWeekday(), mo.getCycle(), mo.getTimebeginhour(), mo.getTimebeginmin(), mo.getTimeendhour(), mo.getTimeendmin() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public KeChengBiaoMO getById(String id) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name kechengName,d.name teacherName,e.name weekdayName from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_KECHENG + " b on a.kecheng = b.id ")
				.append(" left join " + TABLE_NAME_KECHENGSTUDENT + " c on a.kecheng = c.kecheng ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " d on b.teacher = d.id ")
				.append(" left join " + TABLE_NAME_DICCONTENT + " e on a.weekday = e.id and e.typeid = '5'")
				.append(" where a.id = " + id);

		return jdbcTemplate.queryForObject(sql.toString(), new KeChengBiaoMO.MOMapper());
	}

	public List<KeChengBiaoMO> getList(String kecheng, String teacher, String student) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select distinct a.*,b.name kechengName,d.name teacherName,e.name weekdayName from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_KECHENG + " b on a.kecheng = b.id ")
				.append(" left join " + TABLE_NAME_KECHENGSTUDENT + " c on a.kecheng = c.kecheng ")
				.append(" inner join " + TABLE_NAME_SYSOPERATOR + " d on b.teacher = d.id ")
				.append(" left join " + TABLE_NAME_DICCONTENT + " e on a.weekday = e.id and e.typeid = '5'");

		sql.append(" where 1 = 1 ");

		if (StringUtil.isNotEmpty(kecheng))
		{
			sql.append(" and a.kecheng ='" + kecheng + "'");
		}

		if (StringUtil.isNotEmpty(teacher))
		{
			sql.append(" and b.teacher ='" + teacher + "'");
		}

		if (StringUtil.isNotEmpty(student))
		{
			sql.append(" and c.student ='" + student + "'");
		}

		sql.append(" order by b.name,a.begindate,a.weekday,a.fixdate ");
		
//		System.out.print(sql);

		return jdbcTemplate.query(sql.toString(), new KeChengBiaoMO.MOMapper());
	}
}
