package com.littlecat.ims.basicinfo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.basicinfo.dao.DicContentDao;
import com.littlecat.ims.basicinfo.model.DicContentMO;

@Component
@Transactional
public class DicContentBusiness
{
	@Autowired
	private DicContentDao dicContentDao;

	public void enable(String id) throws LittleCatException
	{
		dicContentDao.enable(id);
	}

	public void enable(List<String> ids) throws LittleCatException
	{
		dicContentDao.enable(ids);
	}

	public void disable(String id) throws LittleCatException
	{
		dicContentDao.disable(id);
	}

	public void disable(List<String> ids) throws LittleCatException
	{
		dicContentDao.disable(ids);
	}

	public DicContentMO getById(String id) throws LittleCatException
	{
		return dicContentDao.getById(id);
	}

	public void modify(DicContentMO mo) throws LittleCatException
	{
		dicContentDao.modify(mo);
	}

	public String add(DicContentMO mo) throws LittleCatException
	{
		return dicContentDao.add(mo);
	}

	public List<DicContentMO> getList(String typeid) throws LittleCatException
	{
		return dicContentDao.getList(typeid);
	}
}
