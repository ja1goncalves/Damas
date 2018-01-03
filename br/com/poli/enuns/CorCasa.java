
package br.com.poli.enuns;

public enum CorCasa {
	BRANCA(1), PRETA(0);
	
	private int cor;
	
	private CorCasa(int cor) {
                            this.cor = cor;
	}
	
	public int getCorCasa() {
                            return this.cor;
	}
        
            public void setCorCasa(int cor){
                            this.cor = cor;
            }
} 
