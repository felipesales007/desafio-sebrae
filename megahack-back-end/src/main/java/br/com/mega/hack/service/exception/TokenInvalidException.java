package br.com.mega.hack.service.exception;

public class TokenInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;

	public TokenInvalidException(String msg) {
		super(msg);
	}

	public TokenInvalidException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
