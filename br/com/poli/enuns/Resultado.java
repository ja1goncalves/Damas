
package br.com.poli.enuns;
public enum Resultado {
	EMPATE(1), COMVENCEDOR(2);
	
	private int resultado;
	
	private Resultado(int resultado) {
		this.resultado = resultado;
	}
	
	public int getResultado() {
		return this.resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	
}
