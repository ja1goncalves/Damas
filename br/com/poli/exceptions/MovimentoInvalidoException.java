/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poli.exceptions;

/**
 *
 * @author joaog
 */
public class MovimentoInvalidoException extends Exception {
	private int inicial, posterior;

	private static final long serialVersionUID = 1L;
	
	public MovimentoInvalidoException(int inicial, int posterior) {
		super();
		this.setInicial(inicial);
		this.setPosterior(posterior);
	}

	@Override
	public String toString() {
		return "As coordenadas "+inicial+" e "+posterior+" dadas são invalidas para movimento.";
	}


	private void setInicial(int inicialY) {
		if((inicial < 7) || (inicial > 0)) {
			this.inicial = inicialY;
		}
	}


	private void setPosterior(int posterior) {
		if((posterior < 7) || (posterior > 0))
			this.posterior = posterior;
	}



}
