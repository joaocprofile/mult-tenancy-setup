package br.com.sigdata.exception;

public class EntityAlreadyExistsException  extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException(String mensagem) {
		super(mensagem);
	}
}
