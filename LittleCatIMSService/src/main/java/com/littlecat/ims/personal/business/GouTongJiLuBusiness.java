package com.littlecat.ims.personal.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.common.consts.GouTongJiLuState;
import com.littlecat.ims.personal.dao.GouTongJiLuDao;
import com.littlecat.ims.personal.model.GouTongJiLuMO;

@Component
@Transactional
public class GouTongJiLuBusiness
{
	@Autowired
	private GouTongJiLuDao gouTongJiLuDao;

	public void zanting(String id) throws LittleCatException
	{
		gouTongJiLuDao.setState(id, GouTongJiLuState.guaqi);
	}

	public void jixu(String id) throws LittleCatException
	{
		gouTongJiLuDao.setState(id, GouTongJiLuState.jinxingzhong);
	}

	public void close(String id) throws LittleCatException
	{
		gouTongJiLuDao.setState(id, GouTongJiLuState.yiwancheng);
	}

	public GouTongJiLuMO getById(String id) throws LittleCatException
	{
		return gouTongJiLuDao.getById(id);
	}

	public void modify(GouTongJiLuMO mo) throws LittleCatException
	{
		gouTongJiLuDao.modify(mo);
	}

	public String add(GouTongJiLuMO mo) throws LittleCatException
	{
		return gouTongJiLuDao.add(mo);
	}

	public List<GouTongJiLuMO> getList(String key, String state) throws LittleCatException
	{
		return gouTongJiLuDao.getList(key, state);
	}

}
