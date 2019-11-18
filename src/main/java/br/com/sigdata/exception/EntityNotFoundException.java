package br.com.sigdata.exception;

public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String mensagem) {
		super(mensagem);
	}

}
