package com.littlecat.ims.student.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.rest.RestRsp;
import com.littlecat.cbb.rest.RestSimpleRsp;
import com.littlecat.ims.student.business.YongCanStudentBusiness;
import com.littlecat.ims.student.model.StudentMO;
import com.littlecat.ims.student.model.YongCanStudentMO;

@RestController
@RequestMapping("/rest/littlecat/ims/yongcanstudent")
public class YongcanStudentController
{
	@Autowired
	private YongCanStudentBusiness yongCanStudentBusiness;

	private static final Logger logger = LoggerFactory.getLogger(YongcanStudentController.class);

	
	@PutMapping(value = "/delete")
	public RestSimpleRsp delete(@RequestBody List<String> ids)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			yongCanStudentBusiness.delete(ids);
		}
		catch (LittleCatException e)
		{
			result.setCode(e.getErrorCode());
			result.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		catch (Exception e)
		{
			result.setCode(Consts.ERROR_CODE_UNKNOW);
			result.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}

		return result;
	}
	
	@PostMapping(value = "/add")
	public RestRsp<String> add(@RequestBody List<String> studentIds)
	{
		RestRsp<String> result = new RestRsp<String>();

		try
		{
			yongCanStudentBusiness.add(studentIds);
		}
		catch (LittleCatException e)
		{
			result.setCode(e.getErrorCode());
			result.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		catch (Exception e)
		{
			result.setCode(Consts.ERROR_CODE_UNKNOW);
			result.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	@GetMapping(value = "/getList")
	public RestRsp<YongCanStudentMO> getList(@RequestParam @Nullable String key)
	{
		RestRsp<YongCanStudentMO> result = new RestRsp<YongCanStudentMO>();

		try
		{
			result.getData().addAll(yongCanStudentBusiness.getList(key));
		}
		catch (LittleCatException e)
		{
			result.setCode(e.getErrorCode());
			result.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		catch (Exception e)
		{
			result.setCode(Consts.ERROR_CODE_UNKNOW);
			result.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	@PostMapping(value = "/getNoYongCanStudents")
	public RestRsp<StudentMO> getNoYongCanStudents(@RequestBody List<String> studentIds, @RequestParam @Nullable String key)
	{
		RestRsp<StudentMO> result = new RestRsp<StudentMO>();

		try
		{
			result.getData().addAll(yongCanStudentBusiness.getNoYongCanStudents(studentIds, key));
		}
		catch (LittleCatException e)
		{
			result.setCode(e.getErrorCode());
			result.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		catch (Exception e)
		{
			result.setCode(Consts.ERROR_CODE_UNKNOW);
			result.setMessage(e.getMessage());
			logger.error(e.getMessage(), e);
		}

		return result;
	}

}
