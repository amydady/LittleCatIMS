package com.littlecat.ims.system.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.system.dao.QianDaoDao;
import com.littlecat.ims.system.model.QianDaoMO;

@Component
@Transactional
public class QianDaoBusiness
{
	@Autowired
	private QianDaoDao qianDaoDao;

	public String add(QianDaoMO mo) throws LittleCatException
	{
		return qianDaoDao.add(mo);
	}

	public void delete(String id) throws LittleCatException
	{
		qianDaoDao.delete(id);
	}

	public List<QianDaoMO> getList(String userId, String userName, String date) throws LittleCatException
	{
		return qianDaoDao.getList(userId, userName, date);
	}

	public boolean exist(String userid, String date) throws LittleCatException
	{
		return qianDaoDao.exist(userid, date);
	}
}
