package com.littlecat.ims.basicinfo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.basicinfo.dao.DicTypeDao;
import com.littlecat.ims.basicinfo.model.DicTypeMO;

@Component
@Transactional
public class DicTypeBusiness
{
	@Autowired
	private DicTypeDao dicTypeDao;

	public List<DicTypeMO> getList() throws LittleCatException
	{
		return dicTypeDao.getList();
	}
}
