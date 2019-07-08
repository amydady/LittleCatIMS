package com.littlecat.ims.personal.rest;

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

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.rest.RestRsp;
import com.littlecat.cbb.rest.RestSimpleRsp;
import com.littlecat.ims.personal.business.GouTongJiLuBusiness;
import com.littlecat.ims.personal.model.GouTongJiLuMO;

@RestController
@RequestMapping("/rest/littlecat/ims/personal/goutongjilu")
public class GouTongJiLuController
{
	@Autowired
	private GouTongJiLuBusiness gouTongJiLuBusiness;

	private static final Logger logger = LoggerFactory.getLogger(GouTongJiLuController.class);

	@PostMapping(value = "/add")
	public RestRsp<String> add(@RequestBody GouTongJiLuMO mo)
	{
		RestRsp<String> result = new RestRsp<String>();

		try
		{
			result.getData().add(gouTongJiLuBusiness.add(mo));
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
	public RestSimpleRsp modify(@RequestBody GouTongJiLuMO mo)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			gouTongJiLuBusiness.modify(mo);
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
	public RestRsp<GouTongJiLuMO> getById(@RequestParam String id)
	{
		RestRsp<GouTongJiLuMO> result = new RestRsp<GouTongJiLuMO>();

		try
		{
			result.getData().add(gouTongJiLuBusiness.getById(id));
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
	public RestRsp<GouTongJiLuMO> getList(@RequestParam @Nullable String key, @RequestParam @Nullable String state)
	{
		RestRsp<GouTongJiLuMO> result = new RestRsp<GouTongJiLuMO>();

		try
		{
			result.getData().addAll(gouTongJiLuBusiness.getList(key, state));
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

	@PutMapping(value = "/zanting/{id}")
	public RestSimpleRsp zanting(@PathVariable String id)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			gouTongJiLuBusiness.zanting(id);
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

	@PutMapping(value = "/jixu/{id}")
	public RestSimpleRsp jixu(@PathVariable String id)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			gouTongJiLuBusiness.jixu(id);
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

	@PutMapping(value = "/close/{id}")
	public RestSimpleRsp close(@PathVariable String id)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			gouTongJiLuBusiness.close(id);
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
