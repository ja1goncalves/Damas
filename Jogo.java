package br.com.poli.damas;

import br.com.poli.enuns.*;
import br.com.poli.exceptions.MovimentoInvalidoException;

import java.util.Date;


public class Jogo {
	private Jogador jogador1, jogador2, jogadorAtual, vencedor;
        private JogadorAutonomo autoPlayer = new JogadorAutonomo("Bruno");
	private Tabuleiro tabuleiro;
	private Date tempo = new Date();
	private int contadorJogadas, jogadasDama, jogadasParaFim, contClara, contEscura;
	private Resultado resultado;


	public Jogo(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) {
		this.setJogador1(jogador1);
		this.setJogador2(jogador2);
		this.tabuleiro = tabuleiro;
	}

	//  - - - - GETTERS E SETTERS - - - - 
	
	//Jogador1
	public Jogador getJogador1() {
		return jogador1;
	}
	public void setJogador1(Jogador jogador1) {
            this.jogador1 = jogador1;
	}
	//Jogador2
	public Jogador getJogador2() {
		return jogador2;
	}
	public void setJogador2(Jogador jogador2) {
            this.jogador2 = jogador2;    
	}
	//Jogador Atual
	public Jogador getJogadorAtual() {
		return this.jogadorAtual;
	}
	public void setJogadorAtual(Jogador atual) {
		this.jogadorAtual = atual;
	}
	
	//Vencedor
	public Jogador getVencedor() {
		return vencedor;
	}
	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}
        
        //Jogador autonomo
        public JogadorAutonomo getAutoPlayer(){
            return (JogadorAutonomo) this.autoPlayer;
        }
        public void setAutoPlayer(JogadorAutonomo player){
            this.autoPlayer = player;
        }
	
	//Tabuleiro
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	//Tempo
	public Date getTempo() {
		return tempo;
	}
	
	//Contador de jogadas
	public int getContadorJogadas() {
		return contadorJogadas;
	}
	public void setContadorJogadas(int contadorJogadas) {
		this.contadorJogadas = contadorJogadas;
	}
        
        //Jogadas Para fim
        public int getJogadasParaFim(){
            return this.jogadasParaFim;
        }
	public void setJogadasParaFim(int jogadasParaFim) {
		this.jogadasParaFim = jogadasParaFim;
	}
	
	// Resultado
	public Resultado getResultado() {
		return resultado;
	}
	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
        
        //contador de peças claras (com a dama)
        public int getContClara(){
            return this.contClara;
        }
        public void setContClara(int claras){
            this.contClara = claras;
        }
        
        //contador de peças escuras (com as damas)
        
        public int getContEscura(){
            return this.contEscura;
        }
        public void setContEscura(int escuras){
            this.contEscura = escuras;
        }
	
	
	// - - - - -  MÉTODOS - - - - - - 
        
	//Analise da peca para verificar se é dama

    private boolean verificaDama(int inicialY, int inicialX) {
            if(this.tabuleiro.getGrid()[inicialY][inicialX].getOcupada()) {
                if((this.tabuleiro.getGrid()[inicialY][inicialX].getPeca() == this.getTabuleiro().getDamaT())) {
                    return true;
                    }
            }
            return false;
	}
	
    private boolean movimentoDama(int finalY, int finalX, int inicialY, int inicialX) {
		return (((finalY - inicialY) == (finalX - inicialX)) || ((-1)*(finalY - inicialY) == (finalX - inicialX)));
	}
	
    private int capturaDama(int finalY, int finalX, int inicialY, int inicialX) {
		int pecasEntreJogada = 0;
		if((this.movimentoDama(finalY, finalX, inicialY, inicialX)) && (this.verificaDama(inicialY, inicialX))) {
			if(((finalY - inicialY) == (finalX - inicialX)) && (((finalY - inicialY) < 0) && (finalX - inicialX) < 0)) { // for up and left
				for(int y = -1; y >= ((finalY - inicialY)-1); y--) {
					for(int x = -1; x >= ((finalX - inicialX)-1); x--) {
						if(y == x) {
                                                    if(getTabuleiro().movimentar(inicialY+y, inicialX+x)){
							if(this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].getOcupada()) {
								pecasEntreJogada++;
							}
                                                    }
						}
					}
				}
			} else if(((finalY - inicialY) == ((-1)*(finalX - inicialX))) && ((finalY - inicialY) < 0)) { // for up and right
				for(int y = -1; y >= ((finalY - inicialY)-1); y--) {
					for(int x = 1; x <= ((finalX - inicialX)+1); x++) {
						if(y == ((-1)*(x))) {
                                                    if(getTabuleiro().movimentar(inicialY+y,inicialX+x)){
							if(this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].getOcupada()) {
								pecasEntreJogada++;
							}
                                                    }
						}
					}
				}
			} else if(((finalY - inicialY) == ((-1)*(finalX - inicialX))) && ((finalX - inicialX) < 0)) { // for down and right
				for(int y = 1; y <= ((finalY - inicialY)+1); y++) {
					for(int x = -1; x >= ((finalX - inicialX)-1); x--) {
						if(y == ((-1)*(x))) {
                                                    if(getTabuleiro().movimentar(inicialY+y,inicialX+x)){
							if(this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].getOcupada()) {
								pecasEntreJogada++;
							}
                                                    }
						}
					}
				}
			} else if (((finalY - inicialY) == (finalX - inicialX)) && (((finalY - inicialY) > 0) && (finalX - inicialX) > 0)) {
				for(int y = 1; y <= ((finalY - inicialY)+1); y++) {
					for(int x = 1; x <= ((finalX - inicialX)+1); x++) {
						if (y == x) {
                                                    if(getTabuleiro().movimentar(inicialY+y,inicialX+x)){
							if(this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].getOcupada()) {
								pecasEntreJogada++;
							}
                                                    }
						}
					}
				}
			}
                    try{
			if(pecasEntreJogada == 0)
				return 1;
			else if (pecasEntreJogada == 1)
				return 2;
			else if (pecasEntreJogada > 1){
                            throw new MovimentoInvalidoException(finalX, finalY);
                        }
                    }catch(MovimentoInvalidoException e){
                        System.out.println(e.toString());
                    }
		}
		return 0;
	}

    private void capturaValidaDama(int finalY, int finalX, int inicialY, int inicialX) throws MovimentoInvalidoException {
		if((this.getTabuleiro().getGrid()[inicialY][inicialX].getOcupada()) && (this.verificaDama(inicialY, inicialX))) {
                    if ((this.capturaDama(finalY, finalX, inicialY, inicialX) == 2) || (this.capturaDama(finalY, finalX, inicialY, inicialX) == 1)) {
                        if (((finalY - inicialY) == (finalX - inicialX)) && (((finalY - inicialY) < 0) && (finalX - inicialX) < 0)) {
                                //movimento para a diagonal superior esquerda
                                for(int y = -1; y > (finalY - inicialY); y--) {
                                        for(int x = -1; x > (finalX - inicialX); x--) {
                                                if(y == x) {
                                                        this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].setPeca(null);
                                                        this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].setOcupada(false);
                                                }
                                        }
                                }
                                if((this.getTabuleiro().getGrid()[finalY][finalX].getOcupada())) {
                                    if((this.tabuleiro.movimentar(finalY-1, finalX-1))){
                                            this.getTabuleiro().getGrid()[finalY-1][finalX-1].setPeca(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca());
                                            this.getTabuleiro().getGrid()[finalY-1][finalX-1].setOcupada(true);
                                            this.getTabuleiro().getGrid()[finalY][finalX].setPeca(null);
                                            this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(false);
                                    }
                                }else {
                                        this.getTabuleiro().getGrid()[finalY][finalX].setPeca(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca());
                                        this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(true);
                                }

                        } else if(((finalY - inicialY) == ((-1)*(finalX - inicialX))) && ((finalY - inicialY) < 0)) {
                                // movimento para a diagonal superior direita
                                for(int y = -1; y > (finalY - inicialY); y--) {
                                        for(int x = 1; x < (finalX - inicialX); x++) {
                                                if(y == ((-1)*(x))) {
                                                        this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].setPeca(null);
                                                        this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].setOcupada(false);
                                                }
                                        }
                                }
                                if((this.getTabuleiro().getGrid()[finalY][finalX].getOcupada())) {
                                    if((this.tabuleiro.movimentar(finalY-1, finalX+1))){
                                        this.getTabuleiro().getGrid()[finalY-1][finalX+1].setPeca(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca());
                                        this.getTabuleiro().getGrid()[finalY-1][finalX+1].setOcupada(true);
                                        this.getTabuleiro().getGrid()[finalY][finalX].setPeca(null);
                                        this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(false);
                                    }
                                }else {
                                        this.getTabuleiro().getGrid()[finalY][finalX].setPeca(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca());
                                        this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(true);
                                }
                        } else if(((finalY - inicialY) == ((-1)*(finalX - inicialX))) && ((finalX - inicialX) < 0)) { 
                                 // movimento para a diagonal inferior esquerda
                                for(int y = 1; y < (finalY - inicialY); y++) {
                                        for(int x = -1; x > (finalX - inicialX); x--) {
                                                if(y == ((-1)*(x))) {
                                                this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].setPeca(null);
                                                this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].setOcupada(false);
                                                }
                                        }
                                }
                                if((this.getTabuleiro().getGrid()[finalY][finalX].getOcupada())) {
                                    if((this.tabuleiro.movimentar(finalY+1, finalX-1))){
                                        this.getTabuleiro().getGrid()[finalY+1][finalX-1].setPeca(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca());
                                        this.getTabuleiro().getGrid()[finalY+1][finalX-1].setOcupada(true);
                                        this.getTabuleiro().getGrid()[finalY][finalX].setPeca(null);
                                        this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(false);
                                    }
                                }else {
                                        this.getTabuleiro().getGrid()[finalY][finalX].setPeca(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca());
                                        this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(true);
                                }
                        } else if (((finalY - inicialY) == (finalX - inicialX)) && (((finalY - inicialY) > 0) && (finalX - inicialX) > 0)) {
                                //movimento para a a diagonal inferior direita
                                for(int y = 1; y < (finalY - inicialY); y++) {
                                        for(int x = 1; x < (finalX - inicialX); x++) {
                                                if (y == x) {
                                                this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].setPeca(null);
                                                this.getTabuleiro().getGrid()[inicialY+y][inicialX+x].setOcupada(false);
                                                }
                                        }
                                }
                                if((this.getTabuleiro().getGrid()[finalY][finalX].getOcupada())) {
                                    if((this.tabuleiro.movimentar(finalY+1, finalX+1))){
                                        this.getTabuleiro().getGrid()[finalY+1][finalX+1].setPeca(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca());
                                        this.getTabuleiro().getGrid()[finalY+1][finalX+1].setOcupada(true);
                                        this.getTabuleiro().getGrid()[finalY][finalX].setPeca(null);
                                        this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(false);
                                    }
                                }else {
                                        this.getTabuleiro().getGrid()[finalY][finalX].setPeca(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca());
                                        this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(true);
                                }
                            }
                            this.getTabuleiro().getGrid()[inicialY][inicialX].setPeca(null);
                            this.getTabuleiro().getGrid()[inicialY][inicialX].setOcupada(false);
			}
		}
	}

    private boolean capturaDeNovoDama(int inicialY, int inicialX) throws MovimentoInvalidoException {
            if ((this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca()) == (this.getTabuleiro().getDamaT())) {
                for(int y = 0; y < getTabuleiro().getGrid().length; y++) {
                    for(int x = 0; x < getTabuleiro().getGrid().length; x++) {
                        if(this.getTabuleiro().getGrid()[y][x].getOcupada()) { 
                                if(this.getTabuleiro().getGrid()[y][x].getPeca().getPedra() != this.getTabuleiro().getDamaT().getPedra()) {
                                    if((this.capturaDama(y, x, inicialY, inicialX) == 1)||(this.capturaDama(y, x, inicialY, inicialX) == 2)) {
                                        this.capturaValidaDama(y, x, inicialY, inicialX);
                                        return true;
                                }
                            }
                        }
                    }
                }
        }
        return false;
    }

    private boolean obrigarCapturaDama(Jogador jogador) throws MovimentoInvalidoException{
            for(int y = 0; y < tabuleiro.getGrid().length; y++) {
            	for(int x = 0; x < tabuleiro.getGrid().length; x++) {
                    if(this.getTabuleiro().getGrid()[y][x].getOcupada()){
                        if(this.getTabuleiro().getGrid()[y][x].getPeca() instanceof Dama){
                            if(this.getTabuleiro().getGrid()[y][x].getPeca().getJogador() == jogador){
                                while(this.capturaDeNovoDama(y, x)){
                                    this.capturaDeNovoDama(y,x);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        
    public int poderMovimentar(int finalY, int finalX, int inicialY, int inicialX) throws MovimentoInvalidoException {
        try{
            if((this.tabuleiro.movimentar(finalY, finalX)) && (this.tabuleiro.movimentar(inicialY, inicialX))){
                if((this.tabuleiro.getGrid()[inicialY][inicialX].getCorCasa() == CorCasa.PRETA)){ 
                    if((this.tabuleiro.getGrid()[inicialY][inicialX].getOcupada()) && (this.tabuleiro.getGrid()[finalY][finalX].getOcupada() == false)) {
                        if ((this.verificaDama(inicialY, inicialX)) && (this.movimentoDama(finalY, finalX, inicialY, inicialX))) {
                             if(this.capturaDama(finalY, finalX, inicialY, inicialX) == 2)
                            	 return 3; // poder capturar
                             else if(this.capturaDama(finalY, finalX, inicialY, inicialX) == 1)
                            	 return 4; // apenas movimenta
                            }else if(this.tabuleiro.getGrid()[inicialY][inicialX].getPeca().getPedra() == CorPeca.CLARA) {
                                if ((((inicialY-1) == finalY)|| (inicialY+1 == finalY)) && ((inicialX-1) == finalX)) {
                                    return 1; // poder movimentar peça clara
                                }
                            }else if(this.tabuleiro.getGrid()[inicialY][inicialX].getPeca().getPedra() == CorPeca.ESCURA) {
                                if ((((inicialY+1) == finalY)|| (inicialY-1 == finalY)) && ((inicialX+1) == finalX)) {
                                    return 1; // poder movimentar peça escura
                                }
                            } 
                        }else if((this.tabuleiro.getGrid()[inicialY][inicialX].getOcupada()) && (this.tabuleiro.getGrid()[finalY][finalX].getOcupada())) {	
                        	if((this.verificaDama(inicialY, inicialX)) && (this.movimentoDama(finalY, finalX, inicialY, inicialX))) {
                        		if(this.capturaDama(finalY, finalX, inicialY, inicialX) == 2)
                        			return 3; // poder capturar dama
                        		else if (this.capturaDama(finalY, finalX, inicialY, inicialX) == 1)
                        			return 4; // poder movimentar dama
                        	}
                            if(this.tabuleiro.getGrid()[inicialY][inicialX].getPeca().getPedra() != this.tabuleiro.getGrid()[finalY][finalX].getPeca().getPedra())
                                return 2; // poder capturar
                            }else if(!(this.tabuleiro.getGrid()[inicialY][inicialX].getOcupada())){
                                System.out.println("O local de movimento não esta ocupado por uma peça.\n");
                                throw new MovimentoInvalidoException(finalY, finalX);
                            }
                    }else
                        throw new MovimentoInvalidoException(inicialY, inicialX);
                    
                }else {
                    System.out.println("Local invalido para movimento.\n");
                    throw new MovimentoInvalidoException(finalY, finalX);
                    }
        }catch(MovimentoInvalidoException e){
            System.out.println(e.toString());
        }
        return 0; // do nothing

	} 
	
    private boolean capturar (int finalY, int finalX, int inicialY, int inicialX) throws MovimentoInvalidoException {   
            if (finalY == (inicialY-1)) { // Peça de cima
                    if((finalX+1) == inicialX) { // movimento para esquerda
                        if(this.tabuleiro.movimentar(finalY-1, finalX-1)){
                            if (!(this.getTabuleiro().getGrid()[finalY-1][finalX-1].getOcupada())) {
                                if(this.getTabuleiro().executarMovimento(finalY-1, finalX-1, inicialY, inicialX)){
                                    this.getTabuleiro().executarMovimento(finalY-1, finalX-1, inicialY, inicialX);
                                    this.getTabuleiro().getGrid()[finalY][finalX].setPeca(null);
                                    this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(false);
                                    return true;
                                }else
                                    return false;
                            }
                        }
                }
                    else if (finalX == (inicialX+1)) { // movimento para direita
                        if(this.tabuleiro.movimentar(finalY-1, finalX+1)){
                            if (!(this.getTabuleiro().getGrid()[finalY-1][finalX+1].getOcupada())) {
                                if(this.getTabuleiro().executarMovimento(finalY-1, finalX+1, inicialY, inicialX)){
                                    this.getTabuleiro().executarMovimento(finalY-1, finalX+1, inicialY, inicialX);
                                    this.getTabuleiro().getGrid()[finalY][finalX].setPeca(null);
                                    this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(false);
                                    return true;
                                }else
                                    return false;
                            }
                        }
                }
            }
            if ((finalY-1) == inicialY) { // Peça de baixo
                if ((finalX-1) == inicialX) { // Movimento para direita
                    if(this.tabuleiro.movimentar(finalY+1, finalX+1)){
                        if (!(this.getTabuleiro().getGrid()[finalY+1][finalX+1].getOcupada())) {
                            if(this.getTabuleiro().executarMovimento(finalY+1, finalX+1, inicialY, inicialX)){
                                this.getTabuleiro().executarMovimento(finalY+1, finalX+1, inicialY, inicialX);
                                this.getTabuleiro().getGrid()[finalY][finalX].setPeca(null);
                                this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(false);
                                return true;
                            }else 
                                return false;
                        }
                    }
                }
                if ((finalX+1) == inicialX) { // Movimento para esquerda
                    if(this.tabuleiro.movimentar(finalY+1, finalX-1)){
                        if (!(this.getTabuleiro().getGrid()[finalY+1][finalX-1].getOcupada())) {
                            if(this.getTabuleiro().executarMovimento(finalY+1, finalX-1, inicialY, inicialX)){
                                this.getTabuleiro().executarMovimento(finalY+1, finalX-1, inicialY, inicialX);
                                this.getTabuleiro().getGrid()[finalY][finalX].setPeca(null);
                                this.getTabuleiro().getGrid()[finalY][finalX].setOcupada(false);
                                return true;
                            }else
                                return false;
                        }
                    }
                }
            }
        return false;
    }
    
    private boolean capturarNovamente(int inicialY, int inicialX) throws MovimentoInvalidoException{
        for(int y = 0; y < tabuleiro.getGrid().length; y++) {
        	for(int x = 0; x < tabuleiro.getGrid().length; x++) {
                if(this.getTabuleiro().getGrid()[y][x].getCorCasa() == CorCasa.PRETA){
                    if((this.getTabuleiro().getGrid()[y][x].getOcupada()) && (this.getTabuleiro().getGrid()[inicialY][inicialX].getOcupada())) {
                        if(this.getTabuleiro().getGrid()[y][x].getPeca().getPedra() != this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca().getPedra()) {
                            if(this.capturar(y, x, inicialY, inicialX)){
                                this.capturar(y, x, inicialY, inicialX);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;   
    }
   	
    private boolean obrigarCaptura(Jogador jogador) throws MovimentoInvalidoException{
        for(int y = 0; y < tabuleiro.getGrid().length; y++) {
            for(int x = 0; x < tabuleiro.getGrid().length; x++) {
                if(this.getTabuleiro().getGrid()[y][x].getOcupada()){
                    if(this.getTabuleiro().getGrid()[y][x].getPeca().getJogador() == jogador){
                        while(this.capturarNovamente(y, x)){
                            this.capturarNovamente(y, x);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean isFimDeJogo( int inicialY, int inicialX) {
	int adversario = 0, contadorDamaClara = 0,
	contadorDamaEscura = 0, pedras = 0, pClara = 0, pEscura = 0;
	
	if ((this.jogadasParaFim == 20) || (this.jogadasDama == 20)) {
        this.setResultado(Resultado.EMPATE);
        return true;
	}
	
        for (int y = 0; y < getTabuleiro().getGrid().length; y++) {
            for (int x = 0; x < getTabuleiro().getGrid().length; x++) {
                    if(this.getTabuleiro().getGrid()[y][x].getOcupada()) {
                            if(this.getTabuleiro().getGrid()[y][x].getPeca() == this.getTabuleiro().getDamaT()) {
                                    if(this.getTabuleiro().getDamaT().getPedra() == CorPeca.CLARA) {
                                            contadorDamaClara++;
                                    } else if(this.getTabuleiro().getDamaT().getPedra() == CorPeca.ESCURA) {
                                            contadorDamaEscura++;
                                    }
                                    }else {
                                    if ((this.getTabuleiro().getGrid()[y][x].getOcupada())) {
                                            pedras++;
                                            if(this.getTabuleiro().getGrid()[y][x].getPeca().getPedra() == CorPeca.CLARA) {
                                                    pClara++;
                                            }else if(this.getTabuleiro().getGrid()[y][x].getPeca().getPedra() == CorPeca.ESCURA) {
                                                    pEscura++;
                                            }
                                    }
                                    }
                                setContClara(contadorDamaClara+pClara);
                                setContEscura(contadorDamaEscura+pEscura);
                            }

                if((this.getTabuleiro().getGrid()[y][x].getOcupada()) && (this.getTabuleiro().getGrid()[y][x].getPeca() != null)) {
                    if(this.getTabuleiro().getGrid()[inicialY][inicialX].getOcupada()) {
                            if (this.getTabuleiro().getGrid()[y][x].getPeca().getPedra() != this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca().getPedra())
                                    adversario++;
                    }
                }
            }
        }
    
    if((adversario == 0)) {
    	this.setResultado(Resultado.COMVENCEDOR);
    	if(this.getTabuleiro().getGrid()[inicialY][inicialX].getOcupada()) {
    	this.setVencedor(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca().getJogador());
        vencedor = this.jogadorAtual;
        return true;
    	}
    }
    if(((contadorDamaClara == 2) && (contadorDamaEscura == 2)) && (pedras == 0)) {
    	this.setResultado(Resultado.EMPATE);
        return true;
    }
    if(((contadorDamaClara == 2) || (contadorDamaClara == 1)) && ((pEscura == 1) && (pedras == 0))){
    	this.setResultado(Resultado.EMPATE);
        return true;
    }
    if(((contadorDamaEscura == 2) || (contadorDamaEscura == 1)) && ((pClara == 1) && (pedras == 0))) {
    	this.setResultado(Resultado.EMPATE);
        return true;
    }
    if(((contadorDamaClara == 1) && (contadorDamaEscura == 1)) && (pedras == 0)) {
    	this.setResultado(Resultado.EMPATE);
        return true;
    }
    
    return false;
 }

    private int pecaJogador(int inicialY, int inicialX) {
            if(this.getTabuleiro().getGrid()[inicialY][inicialX].getCorCasa() == CorCasa.PRETA){
		if((this.getTabuleiro().getGrid()[inicialY][inicialX].getOcupada()) && (this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca().getPedra() == CorPeca.CLARA)) {
			//this.tabuleiro.getGrid()[inicialY][inicialX].getPeca().setJogador(this.jogador1);
			return 1;
		}else if((this.getTabuleiro().getGrid()[inicialY][inicialX].getOcupada()) && (this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca().getPedra() == CorPeca.ESCURA)) {
			//this.tabuleiro.getGrid()[inicialY][inicialX].getPeca().setJogador(this.jogador2);
			return 2;
		}
            }else
                 return 0;
            return 0;
	}
	
    private void jogando(int finalY, int finalX, int inicialY, int inicialX) throws MovimentoInvalidoException {
        try{
        if (this.poderMovimentar(finalY, finalX, inicialY, inicialX) == 1) {
                 this.tabuleiro.executarMovimento(finalY, finalX, inicialY, inicialX);
                 contadorJogadas++;
                 jogadasParaFim++;
         } else if (this.poderMovimentar(finalY, finalX, inicialY, inicialX) == 2) { // e perde a jogada
                 try {
                     if(this.capturar(finalY, finalX, inicialY, inicialX)) { // e continua com a jogada
                         this.capturar(finalY, finalX, inicialY, inicialX);
                         this.contadorJogadas++;
                         this.setJogadasParaFim(0);
                     }else
                        throw new MovimentoInvalidoException(finalY, finalX);
                 }catch(MovimentoInvalidoException e) {
                     e.printStackTrace();
                 }
         }else if(this.poderMovimentar(finalY, finalX, inicialY, inicialX) == 3) {
                 this.capturaValidaDama(finalY, finalX, inicialY, inicialX);
                 contadorJogadas++;
                 finalY = inicialY;
                 finalX = inicialX;
         } else if(this.poderMovimentar(finalY, finalX, inicialY, inicialX) == 4) {
                 if (this.movimentoDama(finalY, finalX, inicialY, inicialX)) {
                    if(this.capturaDama(finalY, finalX, inicialY, inicialX) == 1)
                        this.tabuleiro.executarMovimento(finalY, finalX, inicialY, inicialX);
                        contadorJogadas++;
                        jogadasDama++;
                 }
         }else if (this.poderMovimentar(finalY, finalX, inicialY, inicialX) == 0){
            System.out.println("Não pode movimentar dessa forma.");
            throw new MovimentoInvalidoException(inicialY, inicialX);
         }
        }catch(MovimentoInvalidoException e){
            System.out.println(e.toString());
        }

        if(this.isFimDeJogo(inicialY, inicialX)){
            System.out.println("Fim de jogo");
            System.out.print("O resultado é: ");
            if(resultado == Resultado.EMPATE) {
                    System.out.println(getResultado());
            }else if (resultado == Resultado.COMVENCEDOR){
                    System.out.println(getVencedor().getNome()+" como vencedor");
            }
        }
}
        	
    private int jogadas(int jogadas) {
        if((jogadas%2) == 0) {
            setJogadorAtual(jogador1);
            return 1;
        } else {
            setJogadorAtual(jogador2);
            return 2;
        }
    }
    
    public int irJogar(CorPeca cor, int finalY, int finalX, int inicialY, int inicialX) throws MovimentoInvalidoException{
        if(this.obrigarCapturaDama(this.jogadorAtual)){
            this.obrigarCapturaDama(this.jogadorAtual);
            contadorJogadas++;
            this.setJogadasParaFim(0);
            return 3;
        }else if(this.obrigarCaptura(jogadorAtual)) {
            this.obrigarCaptura(jogadorAtual);
            contadorJogadas++;
            this.setJogadasParaFim(0);
            return 3;
        }else {
            if(this.getTabuleiro().getGrid()[inicialY][inicialX].getOcupada()){
                if(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca() == this.getTabuleiro().getDamaT()) {
                    if(this.getTabuleiro().getGrid()[inicialY][inicialX].getPeca().getPedra() == cor) {
                        if(this.capturaDeNovoDama(inicialY, inicialX)){
                            this.capturaDeNovoDama(inicialY, inicialX);contadorJogadas++;
                            this.setJogadasParaFim(0);
                            return 3;
                        }else
                            this.jogando(finalY, finalX, inicialY, inicialX);
                            return 1;
                    }else {
                        System.out.println("A dama é invalida pra esse jogador.");
                        return 2;
                    }           
                }else {
                        this.jogando(finalY, finalX, inicialY, inicialX);
                        return 1;
                }
            }
        }
            return 0;
    }
	
    public int jogar (int finalY, int finalX, int inicialY, int inicialX) throws MovimentoInvalidoException {
        if((this.getTabuleiro().movimentar(inicialX, finalX)) && (this.getTabuleiro().movimentar(inicialY, finalY))) {
            if(!(this.isFimDeJogo(inicialY, inicialX))) {
                if(this.tabuleiro.getGrid()[inicialY][inicialX].getOcupada()){
                    if(this.jogadas(this.contadorJogadas) == 1) {
                        if((this.pecaJogador(inicialY, inicialX)) == 1) {
                            System.out.println("Jogadas: "+this.getContadorJogadas());
                            System.out.println("A jogada foi de "+this.getJogador1().getNome()+"\n\n");
                            return this.irJogar(CorPeca.CLARA, finalY, finalX, inicialY, inicialX);
                        }else if((this.pecaJogador(inicialY, inicialX)) == 2) {
                                System.out.println("Peça invalida para esse jogador");
                        }else if(this.pecaJogador(inicialY, inicialX) == 0){
                        System.out.println("Não existe peça nessa casa.\n");
                        }
                    }else if(this.jogadas(this.contadorJogadas) == 2) {
                        if((this.pecaJogador(inicialY, inicialX)) == 2) { 
                                System.out.println("Jogadas: "+this.getContadorJogadas());
                                System.out.println("A jogada foi de "+this.jogadorAtual.getNome()+"\n\n");
                                return this.irJogar(CorPeca.ESCURA, finalY, finalX, inicialY, inicialX);
                        }else if((this.pecaJogador(inicialY, inicialX)) == 1) {
                            System.out.println("Peça invalida para esse jogador");
                        }else if(this.pecaJogador(inicialY, inicialX) == 0)
                            System.out.println("Não existe peça nessa casa.\n");
                    }
                }
                if (this.isFimDeJogo(inicialY, inicialX)){
                    System.out.println("Fim de jogo");
                    System.out.print("O resultado é: ");
                    if(resultado == Resultado.EMPATE) {
                            System.out.println(getResultado());
                    }else if (resultado == Resultado.COMVENCEDOR){
                    System.out.println(getVencedor().getNome()+" como vencedor.");
                    }
                }
            }else {
                System.out.println("Fim de jogo");
                System.out.print("O resultado é: ");
                if(resultado == Resultado.EMPATE) {
                        System.out.println(getResultado());
                }else if (resultado == Resultado.COMVENCEDOR){
                        System.out.println(getVencedor().getNome()+" como vencedor");
                }
            }
	}
        return 0;
    }
	
    public void autoPlay(int finalY, int finalX, int inicialY, int inicialX) throws MovimentoInvalidoException{
        this.jogar(finalY, finalX, inicialY, inicialX);
        if(this.contadorJogadas%2 != 0){
            setJogadorAtual(autoPlayer);
            if(this.getJogador2().equals(autoPlayer)){
                autoPlayer.autoMov(this);
            }
        }
    }
    
    public void iniciarPartida(Jogador jogador1, Jogador jogador2,Tabuleiro tabuleiro) {
        this.setJogador2(jogador2);
        this.setJogador1(jogador1);

        this.setTabuleiro(tabuleiro);
        if(jogador1 instanceof JogadorAutonomo){
            this.setJogadorAtual(jogador2);
        this.setContadorJogadas(1);
        }else if(jogador2 instanceof JogadorAutonomo){
            this.setJogadorAtual(jogador1);
        this.setContadorJogadas(0);
        }
        
        this.tabuleiro.getPecaClara().setJogador(jogador2);
        this.tabuleiro.getPecaEscura().setJogador(jogador1);
        
        tabuleiro.gerarTabuleiro(tabuleiro.getGrid());
        
    }
}