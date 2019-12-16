package com.littlecat.ims.student.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.student.model.StudentMO;
import com.littlecat.ims.student.model.YongCanStudentMO;

@Component
public class YongCanStudentDao
{
	private final String TABLE_NAME = TableName.YongCanStudent.getName();
	private final String TABLE_NAME_STUDENT = TableName.Student.getName();
	private final String TABLE_NAME_DICCONTENT = TableName.DicContent.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void delete(List<String> students) throws LittleCatException
	{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

		String sql = "delete from " + TABLE_NAME + " where student in (:ids)";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ids", students);

		try
		{
			namedParameterJdbcTemplate.update(sql, parameters);
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public void add(List<String> studentIds) throws LittleCatException
	{

		String sql = "insert into " + TABLE_NAME + "(student) values(?)";

		List<Object[]> batchParams = new ArrayList<Object[]>();
		for (String student : studentIds)
		{
			batchParams.add(new Object[] { student });
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
				.append("select a.*,b.name studentName,c.name xuexiaoName,d.name nianjiName,e.name banjiName from ").append(TABLE_NAME).append(" a ")
				.append(" inner join " + TABLE_NAME_STUDENT + " b on a.student = b.id ")
				.append(" left join " + TABLE_NAME_DICCONTENT + " c on b.xuexiao = c.id and c.typeid = '1'")
				.append(" left join " + TABLE_NAME_DICCONTENT + " d on b.nianji = d.id and c.typeid = '2'")
				.append(" left join " + TABLE_NAME_DICCONTENT + " e on b.banji = e.id and c.typeid = '3'");

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" where b.name like '%" + key + "%'");
		}

		sql.append(" order by b.name desc ");

		return jdbcTemplate.query(sql.toString(), new YongCanStudentMO.MOMapper());
	}

	// 获取未用餐学生列表
	public List<StudentMO> getNoYongCanStudents(List<String> studentIds, String key) throws LittleCatException
	{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

		StringBuilder sql = new StringBuilder()
				.append("select a.*,b.name xuexiaoName,c.name nianjiName,d.name banjiName from ").append(TABLE_NAME_STUDENT).append(" a ")

				.append(" left join " + TABLE_NAME_DICCONTENT + " b on a.xuexiao = b.id and c.typeid = '1'")
				.append(" left join " + TABLE_NAME_DICCONTENT + " c on a.nianji = c.id and c.typeid = '2'")
				.append(" left join " + TABLE_NAME_DICCONTENT + " d on a.banji = d.id and c.typeid = '3'")
				.append(" where id not in (:ids) ");

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("ids", studentIds);

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" and a.name like '%" + key + "%'");
		}

		sql.append(" order by a.name desc ");

		return namedParameterJdbcTemplate.query(sql.toString(), parameters, new StudentMO.MOMapper());
	}

}
