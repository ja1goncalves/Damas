package br.com.poli.damas;
import br.com.poli.enuns.*;
import br.com.poli.exceptions.*;

public class Tabuleiro {
	private Casa[][] grid = new Casa[8][8];
	private Peca pecaClara, pecaEscura;
        private Dama dama;

	//contrutor
	public Tabuleiro(Peca peca1, Peca peca2, Dama dama) {
		this.pecaEscura = peca1;
		this.pecaClara = peca2;
                this.dama = dama;
	}
	
	//grid
	public Casa[][] getGrid(){
		return this.grid;
	}
	
	//Peça escura
	public Peca getPecaEscura() {
		return pecaEscura;
	}
	public void setPecaEscura(Peca pecaEscura) {
		this.pecaEscura = pecaEscura;
	}

	//Peça clara
	public Peca getPecaClara() {
		return pecaClara;
	}
	public void setPecaClara(Peca pecaClara) {
		this.pecaClara = pecaClara;
	}
        
        //dama
        public Dama getDamaT(){
            return this.dama;
        }
        public void setDamaT(Dama dama){
            this.dama = dama;
        }
	
	//confirmador do espaço do tabuleiro
	public boolean movimentar(int posicaoY, int posicaoX) {  
            return (((posicaoY <= 7)&&(posicaoY >= 0)) && ((posicaoX <= 7)&&(posicaoX >= 0)));
	}
	
	//gerar tabuleiro
	public void gerarTabuleiro(Casa[][] grid){
           for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++){
                 if(!((x+y)%2 == 0)){
                    if((x>=5)&&(x<=7)){
                        grid[y][x] = new Casa(this.pecaEscura, true, CorCasa.PRETA);
                    }else if((x>=0)&&(x<=2)){
                        grid[y][x] = new Casa(this.pecaClara, true, CorCasa.PRETA);
                    }else
                        grid[y][x] = new Casa(null, false, CorCasa.PRETA);
                }else{
                     grid[y][x] = new Casa(null, false, CorCasa.BRANCA);
                }
            }
        }
	}
	
	public boolean executarMovimento(int finalY, int finalX, int inicialY, int inicialX) throws MovimentoInvalidoException {
            if((this.movimentar(finalY, finalX)) && (this.movimentar(inicialY, inicialX))){
                if(this.grid[inicialY][inicialX].getOcupada()) {
                        this.grid[finalY][finalX].setPeca(this.grid[inicialY][inicialX].getPeca());
                        this.grid[finalY][finalX].setOcupada(true);
                        this.grid[inicialY][inicialX].setPeca(null);
                        this.grid[inicialY][inicialX].setOcupada(false);
                    if((this.grid[finalY][finalX].getPeca().getPedra().equals(CorPeca.CLARA)) && (finalX == 0)) {
                        this.getDamaT().setPedra(this.getGrid()[finalY][finalX].getPeca().getPedra());
                        this.getDamaT().setJogador(this.getGrid()[finalY][finalX].getPeca().getJogador());
                        this.getGrid()[finalY][finalX].setPeca(this.getDamaT());
                        return true;
                    }if ((this.grid[finalY][finalX].getPeca().getPedra() == CorPeca.ESCURA) && (finalX == 7)) {
                        this.getDamaT().setPedra(this.getGrid()[finalY][finalX].getPeca().getPedra());
                        this.getDamaT().setJogador(this.getGrid()[finalY][finalX].getPeca().getJogador());
                        this.getGrid()[finalY][finalX].setPeca(this.getDamaT());
                        return true;
                }
                        return true;
                }
                
            } 
           return false;
	}

    public void desenharTabuleiro() {	
        for(int i = 0; i < this.getGrid().length; i++) {
            for(int j = 0; j < this.getGrid().length; j++) {
                if(this.grid[i][j].getOcupada()) {	
                    if(this.getGrid()[i][j].getPeca() == this.getDamaT()) {
                        if(this.getDamaT().getPedra() == CorPeca.ESCURA) {
                                System.out.print("[Ê] ");
                        } else if(this.getDamaT().getPedra() == CorPeca.CLARA) {
                                System.out.print("[Õ] ");
                        }
                    }else {
                        if(this.grid[i][j].getPeca().getPedra() == CorPeca.ESCURA) {
                                System.out.print("[e] ");

                        } else if(this.grid[i][j].getPeca().getPedra() == CorPeca.CLARA) {
                                System.out.print("[o] ");	
                        }
                    }
                }else
                        System.out.print("[ ] ");
            }
            System.out.println("\n");
        }
    }
}