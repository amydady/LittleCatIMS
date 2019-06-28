package com.littlecat.ims.kecheng.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.student.dao.StudentDao;
import com.littlecat.ims.student.model.StudentMO;

@Component
@Transactional
public class KeChengBusiness
{
	@Autowired
	private StudentDao studentDao;

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
		return studentDao.getList(key);
	}
	
}
