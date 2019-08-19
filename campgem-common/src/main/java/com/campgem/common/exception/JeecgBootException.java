package com.campgem.common.exception;

import com.campgem.common.enums.StatusEnum;

public class JeecgBootException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private StatusEnum statusEnum;

	public JeecgBootException(String message){
		super(message);
	}

	public JeecgBootException(StatusEnum statusEnum){
		this.statusEnum = statusEnum;
	}
	
	public JeecgBootException(Throwable cause)
	{
		super(cause);
	}
	
	public JeecgBootException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
