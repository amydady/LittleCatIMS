package com.littlecat.ims.kecheng.rest;

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
import com.littlecat.ims.kecheng.business.PayRecordBusiness;
import com.littlecat.ims.kecheng.model.PayRecordMO;

@RestController
@RequestMapping("/rest/littlecat/ims/payrecord")
public class PayRecordController
{
	@Autowired
	private PayRecordBusiness payRecordBusiness;

	private static final Logger logger = LoggerFactory.getLogger(PayRecordController.class);

	@PostMapping(value = "/add")
	public RestRsp<String> add(@RequestBody PayRecordMO mo)
	{
		RestRsp<String> result = new RestRsp<String>();

		try
		{
			result.getData().add(payRecordBusiness.add(mo));
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
	public RestSimpleRsp modify(@RequestBody PayRecordMO mo)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			payRecordBusiness.modify(mo);
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
	public RestRsp<PayRecordMO> getById(@RequestParam String id)
	{
		RestRsp<PayRecordMO> result = new RestRsp<PayRecordMO>();

		try
		{
			result.getData().add(payRecordBusiness.getById(id));
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
	public RestRsp<PayRecordMO> getList(@RequestParam @Nullable String studentId, @RequestParam @Nullable String studentName,@RequestParam @Nullable String beginDate,@RequestParam @Nullable String endDate)
	{
		RestRsp<PayRecordMO> result = new RestRsp<PayRecordMO>();

		try
		{
			result.getData().addAll(payRecordBusiness.getList(studentId, studentName,beginDate,endDate));
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
