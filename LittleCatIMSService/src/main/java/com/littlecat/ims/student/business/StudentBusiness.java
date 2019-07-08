package com.littlecat.ims.student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.basicinfo.business.DicContentBusiness;
import com.littlecat.ims.student.dao.StudentDao;
import com.littlecat.ims.student.model.StudentMO;

@Component
@Transactional
public class StudentBusiness
{
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private DicContentBusiness dicContentBusiness;

	public void enable(String id) throws LittleCatException
	{
		studentDao.enable(id);
	}

	public void enable(List<String> ids) throws LittleCatException
	{
		studentDao.enable(ids);
	}

	public void disable(String id) throws LittleCatException
	{
		studentDao.disable(id);
	}
	

	public void disable(List<String> ids) throws LittleCatException
	{
		studentDao.disable(ids);
	}

	public StudentMO getById(String id) throws LittleCatException
	{
		return studentDao.getById(id);
	}

	public void modify(StudentMO mo) throws LittleCatException
	{
		studentDao.modify(mo);
	}

	public String add(StudentMO mo) throws LittleCatException
	{
		return studentDao.add(mo);
	}

	public List<StudentMO> getList(String key) throws LittleCatException
	{
		List<StudentMO> dataList = studentDao.getList(key);
		
		for(StudentMO data:dataList)
		{
			data.setXiaoquName(dicContentBusiness.getById(data.getXiaoqu()).getName());
			data.setXuexiaoName(dicContentBusiness.getById(data.getXuexiao()).getName());
			data.setNianjiName(dicContentBusiness.getById(data.getNianji()).getName());
			data.setBanjiName(dicContentBusiness.getById(data.getBanji()).getName());
		}
		
		return dataList;
	}
	
}
