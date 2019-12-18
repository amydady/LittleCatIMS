package com.littlecat.ims.student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.common.Consts;
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
	
	public List<StudentMO> getByTuiJianren(String tuijianren) throws LittleCatException
	{
		return studentDao.getByTuiJianren(tuijianren);
	}

	public List<StudentMO> getList(String key) throws LittleCatException
	{
		List<StudentMO> dataList = studentDao.getList(key);

		for (StudentMO data : dataList)
		{
			if (!Consts.DEFAULT_NONE_SELECTVALUE.equals(data.getXiaoqu()))
			{
				data.setXiaoquName(dicContentBusiness.getById(data.getXiaoqu()).getName());
			}

			if (!Consts.DEFAULT_NONE_SELECTVALUE.equals(data.getXuexiao()))
			{
				data.setXuexiaoName(dicContentBusiness.getById(data.getXuexiao()).getName());
			}

			if (!Consts.DEFAULT_NONE_SELECTVALUE.equals(data.getNianji()))
			{
				data.setNianjiName(dicContentBusiness.getById(data.getNianji()).getName());
			}

			if (!Consts.DEFAULT_NONE_SELECTVALUE.equals(data.getBanji()))
			{
				data.setBanjiName(dicContentBusiness.getById(data.getBanji()).getName());
			}
		}

		return dataList;
	}
	
	public List<StudentMO> getYongcanList(String need,String key) throws LittleCatException
	{
		List<StudentMO> dataList = studentDao.getYongcanList(need,key);

		for (StudentMO data : dataList)
		{
			if (!Consts.DEFAULT_NONE_SELECTVALUE.equals(data.getXiaoqu()))
			{
				data.setXiaoquName(dicContentBusiness.getById(data.getXiaoqu()).getName());
			}

			if (!Consts.DEFAULT_NONE_SELECTVALUE.equals(data.getXuexiao()))
			{
				data.setXuexiaoName(dicContentBusiness.getById(data.getXuexiao()).getName());
			}

			if (!Consts.DEFAULT_NONE_SELECTVALUE.equals(data.getNianji()))
			{
				data.setNianjiName(dicContentBusiness.getById(data.getNianji()).getName());
			}

			if (!Consts.DEFAULT_NONE_SELECTVALUE.equals(data.getBanji()))
			{
				data.setBanjiName(dicContentBusiness.getById(data.getBanji()).getName());
			}
		}

		return dataList;
	}
	
	public void setNeedYongCan(List<String> ids) throws LittleCatException
	{
		studentDao.setNeedYongCan(ids);
	}
	public void setNotNeedYongCan(List<String> ids) throws LittleCatException
	{
		studentDao.setNotNeedYongCan(ids);
	}
}
