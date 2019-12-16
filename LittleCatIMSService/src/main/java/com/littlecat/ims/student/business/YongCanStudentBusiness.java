package com.littlecat.ims.student.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.student.dao.YongCanStudentDao;
import com.littlecat.ims.student.model.StudentMO;
import com.littlecat.ims.student.model.YongCanStudentMO;

@Component
@Transactional
public class YongCanStudentBusiness
{
	@Autowired
	private YongCanStudentDao yongCanStudentDao;

	public void delete(List<String> students) throws LittleCatException
	{
		yongCanStudentDao.delete(students);
	}

	public void add(List<String> studentIds) throws LittleCatException
	{
		yongCanStudentDao.add(studentIds);
	}

	public List<YongCanStudentMO> getList(String key) throws LittleCatException
	{
		return yongCanStudentDao.getList(key);
	}

	// 获取未用餐学生列表
	public List<StudentMO> getNoYongCanStudents(List<String> studentIds, String key) throws LittleCatException
	{
		return yongCanStudentDao.getNoYongCanStudents(studentIds, key);
	}
}
