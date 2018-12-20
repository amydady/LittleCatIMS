package com.littlecat.basic.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.littlecat.basic.dao.XiaoQuAreaDao;
import com.littlecat.basic.model.XiaoQuAreaMO;
import com.littlecat.cbb.exception.LittleCatException;

@Component
public class XiaoQuAreaBusiness
{
	@Autowired
	protected XiaoQuAreaDao xiaoQuAreaDao;

	public XiaoQuAreaMO getById(String id) throws LittleCatException
	{
		return xiaoQuAreaDao.getById(id);
	}

	public void enable(String id) throws LittleCatException
	{
		xiaoQuAreaDao.enable(id);
	}

	public void enable(List<String> ids) throws LittleCatException
	{
		xiaoQuAreaDao.enable(ids);
	}

	public void disable(String id) throws LittleCatException
	{
		xiaoQuAreaDao.disable(id);
	}

	public void disable(List<String> ids) throws LittleCatException
	{
		xiaoQuAreaDao.disable(ids);
	}

	public String add(XiaoQuAreaMO mo) throws LittleCatException
	{
		return xiaoQuAreaDao.add(mo);
	}

	public void modify(XiaoQuAreaMO mo) throws LittleCatException
	{
		xiaoQuAreaDao.modify(mo);
	}

	public List<XiaoQuAreaMO> getList(String name,String enable) throws LittleCatException
	{
		return xiaoQuAreaDao.getList(name,enable);
	}
}
