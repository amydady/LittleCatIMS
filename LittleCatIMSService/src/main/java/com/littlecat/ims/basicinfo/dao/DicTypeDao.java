package com.littlecat.ims.basicinfo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.basicinfo.model.DicTypeMO;
import com.littlecat.ims.common.consts.TableName;

@Component
public class DicTypeDao
{
	private final String TABLE_NAME = TableName.DicType.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public List<DicTypeMO> getList() throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.* from ").append(TABLE_NAME).append(" a ");

		return jdbcTemplate.query(sql.toString(), new DicTypeMO.MOMapper());
	}
}
