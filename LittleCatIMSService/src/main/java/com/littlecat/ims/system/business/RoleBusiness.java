package com.littlecat.ims.system.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.query.QueryParam;
import com.littlecat.cbb.rest.RestRsp;
import com.littlecat.cbb.rest.RestSimpleRsp;
import com.littlecat.ims.system.model.SysOperatorMO;

@Component
@Transactional
public class RoleBusiness
{
	public RestRsp<SysOperatorMO> login(SysOperatorMO account)
	{
		return null;
	}

	public RestRsp<SysOperatorMO> getById(String id)
	{
		return null;
	}

	public RestSimpleRsp deleteById(String id)
	{
		return null;
	}

	public RestRsp<SysOperatorMO> modify(SysOperatorMO account)
	{
		return null;
	}

	public RestRsp<SysOperatorMO> add(SysOperatorMO account)
	{
		return null;
	}

	public RestRsp<SysOperatorMO> getList(QueryParam queryParam)
	{
		return null;
	}
}
