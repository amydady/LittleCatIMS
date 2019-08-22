package com.littlecat.ims.common.consts;

/**
 * 请假记录状态
 * 
 * @author amydady
 *
 */
public enum LeaveRecordState
{
	待审批("1"), 审批通过("2"), 已结算("3"),已补班("4"),审批不通过("9"),
	
	;

	private String code;

	LeaveRecordState(String code)
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
		for(LeaveRecordState state:LeaveRecordState.values())
		{
			if(state.code.equals(code))
			{
				return state.name();
			}
		}
		
		return "";
	}
}
