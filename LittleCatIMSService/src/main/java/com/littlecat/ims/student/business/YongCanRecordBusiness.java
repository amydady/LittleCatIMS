package com.littlecat.ims.student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.student.dao.YongCanRecordDao;
import com.littlecat.ims.student.model.YongCanRecordMO;

@Component
@Transactional
public class YongCanRecordBusiness
{
	@Autowired
	private YongCanRecordDao yongCanRecordDao;

	public void delete(String id) throws LittleCatException
	{
		yongCanRecordDao.delete(id);
	}

	public void delete(List<String> ids) throws LittleCatException
	{
		yongCanRecordDao.delete(ids);
	}

	public String add(YongCanRecordMO mo) throws LittleCatException
	{
		return yongCanRecordDao.add(mo);
	}

	public void add(List<YongCanRecordMO> mos) throws LittleCatException
	{
		for (YongCanRecordMO mo : mos)
		{
			add(mo);
		}
	}

	public List<YongCanRecordMO> getList(String student, String year, String month, String day, String operator, String key) throws LittleCatException
	{
		return yongCanRecordDao.getList( student, year, month, day, operator, key);
	}
}
