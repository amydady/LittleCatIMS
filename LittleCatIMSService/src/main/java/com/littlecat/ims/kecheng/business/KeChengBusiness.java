package com.littlecat.ims.kecheng.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.kecheng.dao.KeChengDao;
import com.littlecat.ims.kecheng.model.KeChengMO;

@Component
@Transactional
public class KeChengBusiness
{
	@Autowired
	private KeChengDao keChengDao;

	public void enable(String id) throws LittleCatException
	{
		keChengDao.enable(id);
	}

	public void enable(List<String> ids) throws LittleCatException
	{
		keChengDao.enable(ids);
	}

	public void disable(String id) throws LittleCatException
	{
		keChengDao.disable(id);
	}
	

	public void disable(List<String> ids) throws LittleCatException
	{
		keChengDao.disable(ids);
	}

	public KeChengMO getById(String id) throws LittleCatException
	{
		return keChengDao.getById(id);
	}

	public void modify(KeChengMO mo) throws LittleCatException
	{
		keChengDao.modify(mo);
	}

	public String add(KeChengMO mo) throws LittleCatException
	{
		return keChengDao.add(mo);
	}

	public List<KeChengMO> getList(String key,String teacher,String enable) throws LittleCatException
	{
		return keChengDao.getList(key,teacher,enable);
	}
	
}
