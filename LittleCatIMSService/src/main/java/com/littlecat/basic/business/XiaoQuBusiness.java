package com.littlecat.basic.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.littlecat.basic.dao.XiaoQuDao;
import com.littlecat.basic.model.XiaoQuMO;
import com.littlecat.cbb.exception.LittleCatException;

@Component
public class XiaoQuBusiness
{
	@Autowired
	protected XiaoQuDao xiaoQuDao;

	public XiaoQuMO getById(String id) throws LittleCatException
	{
		return xiaoQuDao.getById(id);
	}

	public void enable(String id) throws LittleCatException
	{
		xiaoQuDao.enable(id);
	}

	public void enable(List<String> ids) throws LittleCatException
	{
		xiaoQuDao.enable(ids);
	}

	public void disable(String id) throws LittleCatException
	{
		xiaoQuDao.disable(id);
	}

	public void disable(List<String> ids) throws LittleCatException
	{
		xiaoQuDao.disable(ids);
	}

	public String add(XiaoQuMO mo) throws LittleCatException
	{
		return xiaoQuDao.add(mo);
	}

	public void modify(XiaoQuMO mo) throws LittleCatException
	{
		xiaoQuDao.modify(mo);
	}

	public List<XiaoQuMO> getList(String area,String name,String enable) throws LittleCatException
	{
		return xiaoQuDao.getList(area,name,enable);
	}
}
