package br.com.mega.hack.service.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String msg;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException() {
		super("Registro não encontrado");
	}
	
	

	public ObjectNotFoundException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
