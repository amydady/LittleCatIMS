package com.littlecat.common.consts;

/**
 * 性别
 * 
 * @author amydady
 *
 */
public enum Sex
{
	male("男"), // 男
	female("女"); // 女

	private String displayName;

	Sex(String displayName)
	{
		this.setDisplayName(displayName);
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}
}
