package com.littlecat.ims.personal.rest;

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
import com.littlecat.ims.personal.business.LeaveRecordBusiness;
import com.littlecat.ims.personal.model.LeaveRecordMO;

@RestController
@RequestMapping("/rest/littlecat/ims/personal/leaverecord")
public class LeaveRecordController
{
	@Autowired
	private LeaveRecordBusiness leaveRecordBusiness;

	private static final Logger logger = LoggerFactory.getLogger(LeaveRecordController.class);

	@PostMapping(value = "/add")
	public RestRsp<String> add(@RequestBody LeaveRecordMO mo)
	{
		RestRsp<String> result = new RestRsp<String>();

		try
		{
			result.getData().add(leaveRecordBusiness.add(mo));
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

	@PutMapping(value = "/modify")
	public RestSimpleRsp modify(@RequestBody LeaveRecordMO mo)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			leaveRecordBusiness.modify(mo);
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

	@GetMapping(value = "/getById")
	public RestRsp<LeaveRecordMO> getById(@RequestParam String id)
	{
		RestRsp<LeaveRecordMO> result = new RestRsp<LeaveRecordMO>();

		try
		{
			result.getData().add(leaveRecordBusiness.getById(id));
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
	public RestRsp<LeaveRecordMO> getList(@RequestParam @Nullable String staff, @RequestParam @Nullable String key, @RequestParam @Nullable String state, @RequestParam @Nullable String beginDate, @RequestParam @Nullable String endDate)
	{
		RestRsp<LeaveRecordMO> result = new RestRsp<LeaveRecordMO>();

		try
		{
			result.getData().addAll(leaveRecordBusiness.getList(staff, key, state, beginDate, endDate));
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

	@PutMapping(value = "/approve/{id}/{pass}")
	public RestSimpleRsp approve(@PathVariable String id, @PathVariable String pass)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			if ("1".equals(pass))
			{
				leaveRecordBusiness.approve(id, true);
			}
			else
			{
				leaveRecordBusiness.approve(id, false);
			}
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

	@PutMapping(value = "/jiesuan/{id}")
	public RestSimpleRsp jiesuan(@PathVariable String id)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			leaveRecordBusiness.jiesuan(id);
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

	@PutMapping(value = "/buban/{id}")
	public RestSimpleRsp buban(@PathVariable String id)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			leaveRecordBusiness.buban(id);
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
			leaveRecordBusiness.delete(id);
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
