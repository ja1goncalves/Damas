package br.com.poli.damas;

import br.com.poli.enuns.*;

public class Casa {
    private CorCasa corCasa;
    private boolean ocupada;
    private Peca peca;
    private Dama dama;
    
    //contrutor 1
    public Casa() {
    	
    }
    // contrutor 2
    public Casa(Peca peca, boolean ocupada, CorCasa casa)
    {
        this.peca=peca;
        this.ocupada=ocupada;
        this.corCasa = casa;
    }
    
    //peca
    public Peca getPeca() {
    	return this.peca;
    }
    public void setPeca(Peca peca){
    	this.peca=peca;
    }
    
  //dama
  	public Dama getDama() {
  		return dama;
  	}
  	public void setDama(Dama dama) {
  		this.dama = dama;
  	}
    
    // ocupada
    public boolean getOcupada(){
        return this.ocupada;
    }
    public void setOcupada(boolean ocupada){
        this.ocupada=ocupada;
    }
    
    // cor
	public CorCasa getCorCasa() {
		return corCasa;
	}
	public void setCorCasa(CorCasa corCasa) {
		this.corCasa = corCasa;
	}
 
}