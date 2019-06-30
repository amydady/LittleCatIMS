package com.littlecat.ims.basicinfo.rest;

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
import com.littlecat.ims.basicinfo.business.DicContentBusiness;
import com.littlecat.ims.basicinfo.business.DicTypeBusiness;
import com.littlecat.ims.basicinfo.model.DicContentMO;
import com.littlecat.ims.basicinfo.model.DicTypeMO;

@RestController
@RequestMapping("/rest/littlecat/ims/basicinfo")
public class BasicInfoController
{
	@Autowired
	private DicTypeBusiness dicTypeBusiness;

	@Autowired
	private DicContentBusiness dicContentBusiness;

	private static final Logger logger = LoggerFactory.getLogger(BasicInfoController.class);

	@GetMapping(value = "/getDicTypeList")
	public RestRsp<DicTypeMO> getDicTypeList()
	{
		RestRsp<DicTypeMO> result = new RestRsp<DicTypeMO>();

		try
		{
			result.getData().addAll(dicTypeBusiness.getList());
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

	@PostMapping(value = "/addDicContent")
	public RestRsp<String> addDicContent(@RequestBody DicContentMO mo)
	{
		RestRsp<String> result = new RestRsp<String>();

		try
		{
			result.getData().add(dicContentBusiness.add(mo));
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

	@PutMapping(value = "/modifyDicContent")
	public RestSimpleRsp modifyDicContent(@RequestBody DicContentMO mo)
	{
		RestSimpleRsp result = new RestSimpleRsp();

		try
		{
			dicContentBusiness.modify(mo);
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

	@GetMapping(value = "/getDicContentById")
	public RestRsp<DicContentMO> getDicContentById(@RequestParam String id)
	{
		RestRsp<DicContentMO> result = new RestRsp<DicContentMO>();

		try
		{
			result.getData().add(dicContentBusiness.getById(id));
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

	@GetMapping(value = "/getDicContentList")
	public RestRsp<DicContentMO> getDicContentList(@RequestParam @Nullable String dictype)
	{
		RestRsp<DicContentMO> result = new RestRsp<DicContentMO>();

		try
		{
			result.getData().addAll(dicContentBusiness.getList(dictype));
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
