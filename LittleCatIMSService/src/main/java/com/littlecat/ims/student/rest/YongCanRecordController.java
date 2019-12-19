package com.littlecat.ims.student.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.littlecat.ims.student.business.YongCanRecordBusiness;
import com.littlecat.ims.student.model.YongCanRecordMO;

@RestController
@RequestMapping("/rest/littlecat/ims/yongcanrecord")
public class YongCanRecordController
{
	@Autowired
	private YongCanRecordBusiness yongCanRecordBusiness;

	private static final Logger logger = LoggerFactory.getLogger(YongCanRecordController.class);

	@PostMapping(value = "/add")
	public RestRsp<String> add(@RequestBody YongCanRecordMO mo)
	{
		RestRsp<String> result = new RestRsp<String>();

		try
		{
			result.getData().add(yongCanRecordBusiness.add(mo));
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

	@PostMapping(value = "/batchadd")
	public RestSimpleRsp batchadd(@RequestBody List<YongCanRecordMO> mos)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			yongCanRecordBusiness.add(mos);
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
	public RestRsp<YongCanRecordMO> getList(@RequestParam @Nullable String student, @RequestParam @Nullable String year, @RequestParam @Nullable String month, @RequestParam @Nullable String day, @RequestParam @Nullable String operator, @RequestParam @Nullable String key)
	{
		RestRsp<YongCanRecordMO> result = new RestRsp<YongCanRecordMO>();

		try
		{
			result.getData().addAll(yongCanRecordBusiness.getList(student, year, month, day, operator, key));
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

	@DeleteMapping(value = "/delete/{id}")
	public RestSimpleRsp delete(@PathVariable String id)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			yongCanRecordBusiness.delete(id);
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

	@PutMapping(value = "/batchdelete")
	public RestSimpleRsp delete(@RequestBody List<String> ids)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			yongCanRecordBusiness.delete(ids);
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
