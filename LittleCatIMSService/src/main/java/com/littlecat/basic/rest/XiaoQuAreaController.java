package com.littlecat.basic.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.littlecat.basic.business.XiaoQuAreaBusiness;
import com.littlecat.basic.model.XiaoQuAreaMO;
import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.rest.RestRsp;
import com.littlecat.cbb.rest.RestSimpleRsp;

@RestController
@RequestMapping("/rest/littlecat/ims/xiaoquarea")
public class XiaoQuAreaController
{
	@Autowired
	private XiaoQuAreaBusiness xiaoQuAreaBusiness;

	private static final Logger logger = LoggerFactory.getLogger(XiaoQuAreaController.class);

	@GetMapping(value = "/getbyid")
	public RestRsp<XiaoQuAreaMO> getById(@RequestParam String id)
	{
		RestRsp<XiaoQuAreaMO> result = new RestRsp<XiaoQuAreaMO>();

		try
		{
			XiaoQuAreaMO mo = xiaoQuAreaBusiness.getById(id);
			result.getData().add(mo);
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
	public RestRsp<String> add(@RequestBody XiaoQuAreaMO mo)
	{
		RestRsp<String> result = new RestRsp<String>();

		try
		{
			result.getData().add(xiaoQuAreaBusiness.add(mo));
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
	public RestSimpleRsp modify(@RequestBody XiaoQuAreaMO mo)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			xiaoQuAreaBusiness.modify(mo);
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
	public RestRsp<XiaoQuAreaMO> getList(@RequestParam @Nullable String name)
	{
		RestRsp<XiaoQuAreaMO> result = new RestRsp<XiaoQuAreaMO>();

		try
		{
			result.getData().addAll(xiaoQuAreaBusiness.getList(name));
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

	@PutMapping(value = "/disable/{id}")
	public RestSimpleRsp disable(@PathVariable String id)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			xiaoQuAreaBusiness.disable(id);
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

	@PutMapping(value = "/batchdisable")
	public RestSimpleRsp batchDisable(@RequestBody List<String> ids)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			xiaoQuAreaBusiness.disable(ids);
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

	@PutMapping(value = "/enable/{id}")
	public RestSimpleRsp enable(@PathVariable String id)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			xiaoQuAreaBusiness.enable(id);
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

	@PutMapping(value = "/batchenable")
	public RestSimpleRsp batchEnable(@RequestBody List<String> ids)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			xiaoQuAreaBusiness.enable(ids);
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
