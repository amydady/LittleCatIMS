package com.littlecat.ims.kecheng.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.rest.RestRsp;
import com.littlecat.ims.kecheng.business.JieZhuanRecordBusiness;
import com.littlecat.ims.kecheng.model.JieZhuanRecordMO;

@RestController
@RequestMapping("/rest/littlecat/ims/jiezhuanrecord")
public class JieZhuanRecordController
{
	@Autowired
	private JieZhuanRecordBusiness jieZhuanRecordBusiness;

	private static final Logger logger = LoggerFactory.getLogger(JieZhuanRecordController.class);

	@PostMapping(value = "/add")
	public RestRsp<String> add(@RequestBody JieZhuanRecordMO mo)
	{
		RestRsp<String> result = new RestRsp<String>();

		try
		{
			result.getData().add(jieZhuanRecordBusiness.add(mo));
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
	public RestRsp<JieZhuanRecordMO> getList(@RequestParam @Nullable String kechengs, @RequestParam @Nullable String kechengd, @RequestParam @Nullable String student, @RequestParam @Nullable String key)
	{
		RestRsp<JieZhuanRecordMO> result = new RestRsp<JieZhuanRecordMO>();

		try
		{
			result.getData().addAll(jieZhuanRecordBusiness.getList(kechengs, kechengd, student, key));
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
