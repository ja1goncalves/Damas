package br.com.poli.damas;

import br.com.poli.enuns.*;

public class Peca {
    private Jogador jogador;
    private CorPeca pedra;
    
    //contrutor
    public Peca(Jogador jogador, CorPeca cor){
        this.jogador = jogador;
        this.pedra = cor;
    }
    
    //jogador
    public void setJogador(Jogador jogador){
        this.jogador = jogador;
    } 
    public Jogador getJogador(){
        return this.jogador;
    }

    //cor
	public CorPeca getPedra() {
		return pedra;
	}
	public void setPedra(CorPeca pedra) {
		this.pedra = pedra;
	}
}