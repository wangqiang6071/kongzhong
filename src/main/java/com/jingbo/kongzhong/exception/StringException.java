package com.jingbo.kongzhong.exception;

public class StringException extends RuntimeException{
	private static final long serialVersionUID = -8365683832285081795L;

	public StringException(String message) {
        super(message);
    }
    public StringException(Integer code, String message) {
        super(message);
    }

}
