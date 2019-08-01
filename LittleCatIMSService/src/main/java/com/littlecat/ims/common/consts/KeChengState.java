package com.littlecat.ims.common.consts;

/**
 * 课程状态
 * 
 * @author amydady
 *
 */
public enum KeChengState
{
	正常("Y"), 已关闭("C"), 已失效("N"),
	;

	private String code;

	KeChengState(String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
	
	public static String getNameByCode(String code)
	{
		for(KeChengState state:KeChengState.values())
		{
			if(state.code.equals(code))
			{
				return state.name();
			}
		}
		
		return "";
	}
}
