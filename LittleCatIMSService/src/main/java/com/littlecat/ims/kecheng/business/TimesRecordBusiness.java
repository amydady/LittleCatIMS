package com.littlecat.ims.kecheng.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.kecheng.dao.TimesRecordDao;
import com.littlecat.ims.kecheng.model.TimesRecordMO;

@Component
@Transactional
public class TimesRecordBusiness
{
	@Autowired
	private TimesRecordDao timesRecordDao;

	public void modify(TimesRecordMO mo) throws LittleCatException
	{
		timesRecordDao.modify(mo);
	}

	public String add(TimesRecordMO mo) throws LittleCatException
	{
		return timesRecordDao.add(mo);
	}

	public TimesRecordMO getById(String id) throws LittleCatException
	{
		return timesRecordDao.getById(id);
	}

	public List<TimesRecordMO> getList(String kecheng, String student, String year, String month, String day, String operator) throws LittleCatException
	{
		return timesRecordDao.getList(kecheng, student, year, month, day, operator);
	}

	public void delete(List<String> ids) throws LittleCatException
	{
		timesRecordDao.delete(ids);
	}
}
