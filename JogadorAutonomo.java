/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poli.damas;

import br.com.poli.enuns.*;
import br.com.poli.exceptions.MovimentoInvalidoException;
import br.com.poli.interfacesDama.AutoPlayer;
import java.util.Random;

/**
 *
 * @author joaog
 */
public class JogadorAutonomo extends Jogador implements AutoPlayer {
    
    private int y0 = 0, x0 = 0;
    
    public JogadorAutonomo(String nome){
        super(nome);
    }

    @Override
    public boolean jogarAuto(Jogo jogo) throws MovimentoInvalidoException{
        for(int x = 0;x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 0; y< jogo.getTabuleiro().getGrid().length; y++){
                if(jogo.getTabuleiro().getGrid()[y][x].getOcupada()){
                    if(jogo.getTabuleiro().getGrid()[y][x].getPeca().getJogador() instanceof JogadorAutonomo){
                        if(jogo.getTabuleiro().getGrid()[y][x].getPeca() instanceof Dama){
                            Random escolher = new Random();
                            if(autoCapturaNormal(jogo) != 0){
                                if(autoCapturaNormal(jogo) == 1){
                                    jogo.jogar(y0+1, x0+1, y0, x0);
                                    return true;
                                }else if(autoCapturaNormal(jogo) == 2){
                                    jogo.jogar(y0-1, x0+1, y0, x0);
                                    return true;
                                }else if(autoCapturaNormal(jogo) == 3){
                                    jogo.jogar(y0-1, x0-1, y0, x0);
                                    return true;
                                }else if (autoCapturaNormal(jogo) == 4){
                                    jogo.jogar(y0+1, x0-1, y0, x0);
                                    return true;        
                                }
                            }else if(escolher.nextInt()%2 == 0){
                                Random escolherDeNovo = new Random();
                                if(escolherDeNovo.nextInt()%2 == 0){
                                    if(jogo.getTabuleiro().movimentar(y+1, x+1)){
                                        jogo.jogar(y+1, x+1, y, x);
                                        return true;
                                    } if(jogo.getTabuleiro().movimentar(y-1, x-1)){
                                        jogo.jogar(y-1, x-1, y, x);
                                        return true;
                                    }
                                } if(escolherDeNovo.nextInt()%2 != 0)
                                    if(jogo.getTabuleiro().movimentar(y-1, x-1)){
                                        jogo.jogar(y-1, x-1, y, x);
                                        return true;
                                    } if(jogo.getTabuleiro().movimentar(y+1, x+1)){
                                        jogo.jogar(y+1, x+1, y, x);
                                        return true;
                                    }
                            } if (escolher.nextInt()%2 != 0){
                                Random escolherDeNovo = new Random();
                                if(escolherDeNovo.nextInt()%2 == 0){
                                    if(jogo.getTabuleiro().movimentar(y+1, x-1)){
                                        jogo.jogar(y+1, x-1, y, x);
                                        return true;
                                    } if(jogo.getTabuleiro().movimentar(y-1, x+1)){
                                        jogo.jogar(y-1, x+1, y, x);
                                        return true;
                                    }
                                } if(escolherDeNovo.nextInt()%2 != 0)
                                    if(jogo.getTabuleiro().movimentar(y-1, x+1)){
                                        jogo.jogar(y-1, x+1, y, x);
                                        return true;
                                    } if(jogo.getTabuleiro().movimentar(y+1, x-1)){
                                        jogo.jogar(y+1, x-1, y, x);
                                        return true;
                                    }
                            }
                        }else{
                            if(autoCapturaNormal(jogo) != 0){
                                if(autoCapturaNormal(jogo) == 1){
                                    jogo.jogar(y0+1, x0+1, y0, x0);
                                    return true;
                                }else if(autoCapturaNormal(jogo) == 2){
                                    jogo.jogar(y0-1, x0+1, y0, x0);
                                    return true;
                                }else if(autoCapturaNormal(jogo) == 3){
                                    jogo.jogar(y0-1, x0-1, y0, x0);
                                    return true;
                                }else if (autoCapturaNormal(jogo) == 4){
                                    jogo.jogar(y0+1, x0-1, y0, x0);
                                    return true;        
                                }
                            }else{
                                if (!(autoMov(jogo))){
                                        Random escolher = new Random(0);
                                        if((escolher.nextInt()%2 == 0)){
                                            if(jogo.getTabuleiro().movimentar(y+1, x+1)){
                                                if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                                                    jogo.jogar(y+1, x+1, y, x);
                                                    return true;
                                                }
                                            }
                                        } if((jogo.getTabuleiro().movimentar(y+1, x-1))){
                                            if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                                                jogo.jogar(y-1, x+1, y, x);
                                                return true;
                                            }
                                        }
                                }else
                                    return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Jogador vencedor(Jogo jogo) {
        return jogo.getVencedor();
    }
    
    //meotodo que mira a peça inimiga para esquerda 
    public int pecaInimigaEsquerda(Jogo jogo, int i, int j){
        int casasParaEsquerda = 0;
        for(int x = 1; x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 1; y < jogo.getTabuleiro().getGrid().length; y++){
                if(x == y){
                    if(jogo.getTabuleiro().movimentar(i-y,j+x)){
                        if(!(jogo.getTabuleiro().getGrid()[i-y][j+x].getOcupada())){
                            casasParaEsquerda++;
                        }else
                            return casasParaEsquerda;
                    }else
                        return casasParaEsquerda;
                }
            }
        }
        return casasParaEsquerda;
    }
    public int pecaInimigaEsquerdaC(Jogo jogo, int i, int j){
        int casasParaEsquerda = 0;
        for(int x = 1; x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 1; y < jogo.getTabuleiro().getGrid().length; y++){
                if(x == y){
                    if(jogo.getTabuleiro().movimentar(i-y,j-x)){
                        if(!(jogo.getTabuleiro().getGrid()[i-y][j-x].getOcupada())){
                            casasParaEsquerda++;
                        }else
                            return casasParaEsquerda;
                    }else
                        return casasParaEsquerda;
                }
            }
        }
        return casasParaEsquerda;
    }
    
    //metodo que mira a peça inimiga para direita escura
    public int pecaInimigaDireita(Jogo jogo, int i, int j){
        int casasParaDireita = 0;
        for(int x = 1; x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 1; y < jogo.getTabuleiro().getGrid().length; y++){
                if(y == x){
                    if(jogo.getTabuleiro().movimentar(i+y, j+x)){
                        if(!(jogo.getTabuleiro().getGrid()[i+y][j+x].getOcupada())){
                            casasParaDireita++;
                        }else
                            return casasParaDireita;
                    }else
                        return casasParaDireita;
                }   
            }
        }
        return casasParaDireita;
    }
    public int pecaInimigaDireitaC(Jogo jogo, int i, int j){
        int casasParaDireita = 0;
        for(int x = 1; x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 1; y < jogo.getTabuleiro().getGrid().length; y++){
                if(y == x){
                    if(jogo.getTabuleiro().movimentar(i+y, j-x)){
                        if(!(jogo.getTabuleiro().getGrid()[i+y][j-x].getOcupada())){
                            casasParaDireita++;
                        }else
                            return casasParaDireita;
                    }else
                        return casasParaDireita;
                }   
            }
        }
        return casasParaDireita;
    }
    
    //metodos que verificam as peças do proprio jogador antes dele escura
    public int pecasAmigasEsquerda(Jogo jogo, int i, int j){
        int amigaEsquerda = 0;
        for(int x = 1; x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 1; y < jogo.getTabuleiro().getGrid().length; y++){
                if(x == y){
                    if(jogo.getTabuleiro().movimentar(i-y,j-x)){
                        if(jogo.getTabuleiro().getGrid()[i-y][j-x].getOcupada()){
                            if(jogo.getTabuleiro().getGrid()[i-y][j-x].getPeca().getPedra() == CorPeca.ESCURA){
                                amigaEsquerda++;
                            }else
                                return amigaEsquerda;
                        }else
                            return amigaEsquerda;
                    }else
                        return amigaEsquerda;
                }
            }
        }
        return amigaEsquerda;
    }
    public int pecasAmigasEsquerdaC(Jogo jogo, int i, int j){
        int amigaEsquerda = 0;
        for(int x = 1; x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 1; y < jogo.getTabuleiro().getGrid().length; y++){
                if(x == y){
                    if(jogo.getTabuleiro().movimentar(i-y,j+x)){
                        if(jogo.getTabuleiro().getGrid()[i-y][j+x].getOcupada()){
                            if(jogo.getTabuleiro().getGrid()[i-y][j+x].getPeca().getPedra() == CorPeca.CLARA){
                                amigaEsquerda++;
                            }else
                                return amigaEsquerda;
                        }else
                            return amigaEsquerda;
                    }else
                        return amigaEsquerda;
                }
            }
        }
        return amigaEsquerda;
    }
    
    public int pecasAmigasDireita(Jogo jogo, int i, int j){
        int amigaDireita = 0;
        for(int x = 1; x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 1; y < jogo.getTabuleiro().getGrid().length; y++){
                if(y == x){
                    if(jogo.getTabuleiro().movimentar(i+y,j-x)){
                        if(jogo.getTabuleiro().getGrid()[i+y][j-x].getOcupada()){
                            if(jogo.getTabuleiro().getGrid()[i+y][j-x].getPeca().getPedra() == CorPeca.ESCURA){
                                amigaDireita++;
                            }else
                                return amigaDireita;
                        }else
                            return amigaDireita;
                    }else
                        return amigaDireita;
                }
            }
        }
        return amigaDireita;
    }
    public int pecasAmigasDireitaC(Jogo jogo, int i, int j){
        int amigaDireita = 0;
        for(int x = 1; x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 1; y < jogo.getTabuleiro().getGrid().length; y++){
                if(y == x){
                    if(jogo.getTabuleiro().movimentar(i+y,j+x)){
                        if(jogo.getTabuleiro().getGrid()[i+y][j+x].getOcupada()){
                            if(jogo.getTabuleiro().getGrid()[i+y][j+x].getPeca().getPedra() == CorPeca.CLARA){
                                amigaDireita++;
                            }else
                                return amigaDireita;
                        }else
                            return amigaDireita;
                    }else
                        return amigaDireita;
                }
            }
        }
        return amigaDireita;
    }
    
    //metodo de escolha quando a peça tem mesma quantidade de casas disponiveis dos dois lados
    public int mesmaDisponibilidade(Jogo jogo, int y , int x) throws MovimentoInvalidoException{
        if(pecasAmigasEsquerda(jogo, y, x) == pecasAmigasDireita(jogo, y, x)){ // e quando atrás tem a mesma quantidade de peças
                Random escolher = new Random(0);
            if((escolher.nextInt()%2 == 0) && (jogo.getTabuleiro().movimentar(y+1, x+1))){
                if((jogo.poderMovimentar(y+1, x+1, y, x) != 0))
                    return jogo.jogar(y+1, x+1, y, x);
            }else if((jogo.getTabuleiro().movimentar(y+1, x-1))){
                if((jogo.poderMovimentar(y+1, x-1, y, x) != 0))
                    return jogo.jogar(y-1, x+1, y, x);
            }
        } if(pecasAmigasEsquerda(jogo, y, x) > pecasAmigasDireita(jogo, y, x)){ // quando atrás na esqueda tem mais peças
            if((pecasAmigasEsquerda(jogo, y, x) == 1)){ // Se só tiver uma peça auxiliar a ser jogada
                if((jogo.getTabuleiro().movimentar(y-2, x+2)) && (!(jogo.getTabuleiro().getGrid()[y-2][x+2].getOcupada()))){
                    if((jogo.poderMovimentar(y-1, x+1, y, x) != 0))
                        return jogo.irJogar(CorPeca.ESCURA,y-1, x+1, y, x);
                }else{
                    if((jogo.poderMovimentar(y+1, x+1, y, x) != 0))
                        return jogo.jogar(y+1, x+1, y, x);
                }
            }else if((pecasAmigasEsquerda(jogo, y, x) >= 2)){ // se tiver mais de uma, ela joga pra tentar gerar captura obrigadoria
                if((jogo.poderMovimentar(y+1, x+1, y, x) != 0))
                    return jogo.jogar(y+1, x+1, y, x);
            }
        } if(pecasAmigasEsquerda(jogo, y, x) < pecasAmigasDireita(jogo, y, x)){ // QUANDO ATRÁS NA DIREITA TEM MAIS PEÇAS
            // fazer metodos para escolher entre o melhor
            if((pecasAmigasDireita(jogo, y, x) == 1)){ // Se só tiver uma peça auxiliar a ser jogada
                if((jogo.getTabuleiro().movimentar(y+2, x+2)) && (!(jogo.getTabuleiro().getGrid()[y+2][x+2].getOcupada()))){
                    if((jogo.poderMovimentar(y+1, x+1, y, x) != 0))
                        return jogo.jogar(y+1, x+1, y, x);
                }else{
                    if((jogo.poderMovimentar(y-1, x+1, y, x) != 0))
                        return jogo.jogar( y-1, x+1, y, x);
                }
            }else if((pecasAmigasDireita(jogo, y, x) >= 2)){ // se tiver mais de uma, ela joga pra tentar gerar captura obrigadoria
                if((jogo.poderMovimentar(y-1, x+1, y, x) != 0))
                    return jogo.jogar(y-1, x+1, y, x);
            }
        }
        return 0;
    }
    public int mesmaDisponibilidadeC(Jogo jogo, int y , int x) throws MovimentoInvalidoException{
        if(pecasAmigasEsquerdaC(jogo, y, x) == pecasAmigasDireitaC(jogo, y, x)){ // e quando atrás tem a mesma quantidade de peças
                Random escolher = new Random(0);
            if((escolher.nextInt()%2 == 0) && (jogo.getTabuleiro().movimentar(y-1, x-1))){
                if((jogo.poderMovimentar(y-1, x-1, y, x) != 0))
                    return jogo.jogar(y-1, x-1, y, x);
            }else if((jogo.getTabuleiro().movimentar(y+1, x-1))){
                if((jogo.poderMovimentar(y+1, x-1, y, x) != 0))
                    return jogo.jogar(y+1, x-1, y, x);
            }
        } if(pecasAmigasEsquerdaC(jogo, y, x) > pecasAmigasDireitaC(jogo, y, x)){ // quando atrás na esqueda tem mais peças
            if((pecasAmigasEsquerdaC(jogo, y, x) == 1)){ // Se só tiver uma peça auxiliar a ser jogada
                if((jogo.getTabuleiro().movimentar(y+2, x-2)) && (!(jogo.getTabuleiro().getGrid()[y-2][x+2].getOcupada()))){
                    if((jogo.poderMovimentar(y+1, x-1, y, x) != 0))
                        return jogo.irJogar(CorPeca.ESCURA,y-1, x+1, y, x);
                }else{
                    if((jogo.poderMovimentar(y-1, x-1, y, x) != 0))
                        return jogo.jogar(y-1, x-1, y, x);
                }
            }else if((pecasAmigasEsquerdaC(jogo, y, x) >= 2)){ // se tiver mais de uma, ela joga pra tentar gerar captura obrigadoria
                if((jogo.poderMovimentar(y-1, x-1, y, x) != 0))
                    return jogo.jogar(y-1, x-1, y, x);
            }
        } if(pecasAmigasEsquerdaC(jogo, y, x) < pecasAmigasDireitaC(jogo, y, x)){ // QUANDO ATRÁS NA DIREITA TEM MAIS PEÇAS
            // fazer metodos para escolher entre o melhor
            if((pecasAmigasDireitaC(jogo, y, x) == 1)){ // Se só tiver uma peça auxiliar a ser jogada
                if((jogo.getTabuleiro().movimentar(y-2, x-2)) && (!(jogo.getTabuleiro().getGrid()[y+2][x+2].getOcupada()))){
                    if((jogo.poderMovimentar(y-1, x-1, y, x) != 0))
                        return jogo.jogar(y-1, x-1, y, x);
                }else{
                    if((jogo.poderMovimentar(y+1, x-1, y, x) != 0))
                        return jogo.jogar( y+1, x-1, y, x);
                }
            }else if((pecasAmigasDireitaC(jogo, y, x) >= 2)){ // se tiver mais de uma, ela joga pra tentar gerar captura obrigadoria
                if((jogo.poderMovimentar(y+1, x-1, y, x) != 0))
                    return jogo.jogar(y+1, x-1, y, x);
            }
        }
        return 0;
    }
    
    //movimentação caso o jogador tenha uma peça escura
    public boolean autoMovEscura(Jogo jogo, int y, int x) throws MovimentoInvalidoException{
        if(pecaInimigaDireita(jogo, y, x) == pecaInimigaEsquerda(jogo, y, x)){ // CASO AS CASAS DISPONIVEIS DOS DOIS LADOS SÃO AS MESMAS
            if(pecaInimigaDireita(jogo, y, x) == 1){
                // quando só há uma casa disponivel
                this.mesmaDisponibilidade(jogo, y , x);
                return true;
            } if((pecaInimigaDireita(jogo, y, x) == 0)){ // caso tenha chance de fazer a captura tanto para esquerda quanto para a direita
                if((jogo.getTabuleiro().movimentar(y+2, x+2)) && (!jogo.getTabuleiro().getGrid()[y+2][x+2].getOcupada())){
                    if(jogo.getTabuleiro().getGrid()[y+1][x+1].getPeca().getPedra() == CorPeca.CLARA){
                        if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){ // VERIFICAR A COR DA PEÇA POSTERIOR
                            jogo.jogar(y+1, x+1, y, x);
                            return true;
                        }
                    }
                } if((jogo.getTabuleiro().movimentar(y-2, x+2))&&(!jogo.getTabuleiro().getGrid()[y-2][x+2].getOcupada())){
                    if(jogo.getTabuleiro().getGrid()[y-1][x+1].getPeca().getPedra() == CorPeca.CLARA){
                        if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                            jogo.jogar(y-1, x+1, y, x);
                            return true;
                        }
                    }
                }
            } if(pecaInimigaDireita(jogo, y, x) >= 2){
                this.mesmaDisponibilidade(jogo, y, x);
                return true;
            }
        } if(pecaInimigaDireita(jogo, y, x) > pecaInimigaEsquerda(jogo, y, x)){ // QUANDO A DIREÇÃO DA ESQUERDA TEM MAIS CASAS DESOCUPADAS
            // fazer metodos para escolher entre o melhor
            if((pecaInimigaEsquerda(jogo, y, x) == 0)){ // caso tenha chance de fazer a captura
                if(jogo.getTabuleiro().movimentar(y-2, x+2)){
                    if((jogo.getTabuleiro().getGrid()[y-1][x+1].getPeca().getPedra() == CorPeca.CLARA)){
                        if(!jogo.getTabuleiro().getGrid()[y-2][x+2].getOcupada()){ 
                            if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                                jogo.jogar(y-1, x+1, y, x);
                                return true;
                            }
                        }else{        
                            if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                                jogo.jogar(y+1, x+1, y, x);
                                return true;
                            }
                        }
                    }
                }else{
                    if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                        jogo.jogar(y+1, x+1, y, x);
                        return true;
                    }
                }
            }if(pecaInimigaEsquerda(jogo, y, x) == 1){
                if(pecasAmigasDireita(jogo, y, x) == 0){
                    if(pecasAmigasEsquerda(jogo, y , x) == 0){
                        //não faz nada para tentar ver se tem uma peça melhor
                    } if(pecasAmigasEsquerda(jogo, y, x) >= 1){
                        if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                            jogo.jogar(y-1, x+1, y, x);
                            return true;
                        }
                    }
                } if(pecasAmigasDireita(jogo, y, x) == 1){
                    if((jogo.getTabuleiro().movimentar(y-2, x+2)) && (!(jogo.getTabuleiro().getGrid()[y-2][x+2].getOcupada()))){
                            if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                                jogo.jogar(y+1, x+1, y, x);
                                return true;
                            }
                    }else{
                        if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                            jogo.jogar(y-1, x+1, y, x);
                            return true;
                        }
                    }
                } if(pecasAmigasDireita(jogo, y ,x) >= 2){   
                    if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                        jogo.jogar(y+1, x+1, y, x);
                        return true;
                    }
                }
            }if(pecaInimigaEsquerda(jogo, y, x) >= 2){
                if((jogo.getTabuleiro().movimentar(y-2, x)) && (jogo.getTabuleiro().getGrid()[y-2][x].getOcupada())){ //evitar capitura em futuro movimento para esquerda
                    if(jogo.getTabuleiro().getGrid()[y-2][x].getPeca().getPedra() == CorPeca.CLARA){   
                        if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                            jogo.jogar(y+1, x+1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                            jogo.jogar(y-1, x+1, y, x);
                            return true;
                        }
                    }
                } if((jogo.getTabuleiro().movimentar(y+2, x)) && (jogo.getTabuleiro().getGrid()[y+2][x].getOcupada())){ //evitar capitura em futuro movimento para direita
                    if(jogo.getTabuleiro().getGrid()[y+2][x].getPeca().getPedra() == CorPeca.CLARA){
                        if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                            jogo.jogar(y-1, x+1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                            jogo.jogar(y+1, x+1, y, x);
                            return true;
                        }
                    }
                }else{
                    if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                        jogo.jogar(y+1, x+1, y, x);
                        return true;
                    }
                }
            }
        } if(pecaInimigaDireita(jogo, y, x) < pecaInimigaEsquerda(jogo, y, x)){ // QUANDO A DIREÇÃO DA DIREITA TEM MAIS CASAS DISPONIVEIS
            // fazer metodos para escolher entre o melhor
            if((pecaInimigaDireita(jogo, y, x) == 0)){
                if((jogo.getTabuleiro().movimentar(y+2, x+2)) && (!jogo.getTabuleiro().getGrid()[y+2][x+2].getOcupada())){ // caso tenha chance de fazer a captura
                    if((jogo.getTabuleiro().getGrid()[y+1][x+1].getPeca().getPedra() == CorPeca.CLARA)) {   
                        if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                            jogo.jogar( y+1, x+1, y, x);
                            return true;
                        }
                    }
                }else{
                    if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                        jogo.jogar(y-1, x+1, y, x);
                        return true;
                    }
                }
            } if(pecaInimigaDireita(jogo, y, x) == 1){
                if(pecasAmigasEsquerda(jogo, y, x) == 0){
                    if(pecasAmigasDireita(jogo, y , x) == 0){
                        //não faz nada para tentar ver se tem uma peça melhor
                    }else if(pecasAmigasDireita(jogo, y, x) >= 1){   
                        if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                            jogo.jogar( y+1, x+1, y, x);
                            return true;
                        }
                    }
                } if(pecasAmigasEsquerda(jogo, y, x) == 1){
                    if((jogo.getTabuleiro().movimentar(y-2, x-2)) && (!(jogo.getTabuleiro().getGrid()[y-2][x-2].getOcupada()))){
                        if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                            jogo.jogar( y-1, x+1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                            jogo.jogar(y+1, x+1, y, x);
                            return true;
                        }
                    }
                }else if(pecasAmigasEsquerda(jogo, y ,x) >= 2){
                    if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                        jogo.jogar( y-1, x+1, y, x);
                        return true;
                    }
                }
            } if(pecaInimigaDireita(jogo, y, x) >= 2){
                if((jogo.getTabuleiro().movimentar(y+2, x)) && (jogo.getTabuleiro().getGrid()[y+2][x].getOcupada())){ //evitar capitura em futuro movimento para esquerda
                    if(jogo.getTabuleiro().getGrid()[y+2][x].getPeca().getPedra() == CorPeca.CLARA){
                        if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                            jogo.jogar(y-1, x+1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                            jogo.jogar( y+1, x+1, y, x);
                            return true;
                        }
                    }
                } if((jogo.getTabuleiro().movimentar(y-2, x)) && (jogo.getTabuleiro().getGrid()[y-2][x].getOcupada())){ //evitar capitura em futuro movimento para direita
                    if(jogo.getTabuleiro().getGrid()[y-2][x].getPeca().getPedra() == CorPeca.CLARA){
                        if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                            jogo.jogar( y+1, x+1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y-1, x+1, y, x) != 0)){
                            jogo.jogar( y-1, x+1, y, x);
                            return true;
                        }
                    }
                }else{ 
                    if((jogo.poderMovimentar(y+1, x+1, y, x) != 0)){
                        jogo.jogar(y+1, x+1, y, x);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    //movimentação para caso o jogador tenha a peça clara
    public boolean autoMovClara(Jogo jogo, int y, int x) throws MovimentoInvalidoException{
        if(pecaInimigaDireitaC(jogo, y, x) == pecaInimigaEsquerdaC(jogo, y, x)){ // CASO AS CASAS DISPONIVEIS DOS DOIS LADOS SÃO AS MESMAS
            if(pecaInimigaDireitaC(jogo, y, x) == 1){
                // quando só há uma casa disponivel
                this.mesmaDisponibilidadeC(jogo, y , x);
                return true;
            } if((pecaInimigaDireitaC(jogo, y, x) == 0)){ // caso tenha chance de fazer a captura tanto para esquerda quanto para a direita
                if((jogo.getTabuleiro().movimentar(y+2, x-2)) && (!jogo.getTabuleiro().getGrid()[y+2][x-2].getOcupada())){
                    if(jogo.getTabuleiro().getGrid()[y+1][x-1].getOcupada()){
                        if(jogo.getTabuleiro().getGrid()[y+1][x-1].getPeca().getPedra() == CorPeca.CLARA){
                            if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){ // VERIFICAR A COR DA PEÇA POSTERIOR
                                jogo.jogar(y+1, x-1, y, x);
                                return true;
                            }
                        }
                        }
                } if((jogo.getTabuleiro().movimentar(y-2, x-2))&&(!jogo.getTabuleiro().getGrid()[y-2][x-2].getOcupada())){
                    if(jogo.getTabuleiro().getGrid()[y-1][x-1].getOcupada()){
                        if(jogo.getTabuleiro().getGrid()[y-1][x-1].getPeca().getPedra() == CorPeca.CLARA){
                            if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                                jogo.jogar(y-1, x-1, y, x);
                                return true;
                            }
                        }
                    }
                }
            } if(pecaInimigaDireitaC(jogo, y, x) >= 2){
                this.mesmaDisponibilidade(jogo, y, x);
                return true;
            }
        } if(pecaInimigaDireitaC(jogo, y, x) > pecaInimigaEsquerdaC(jogo, y, x)){ // QUANDO A DIREÇÃO DA ESQUERDA TEM MAIS CASAS DESOCUPADAS
            // fazer metodos para escolher entre o melhor
            if((pecaInimigaEsquerdaC(jogo, y, x) == 0)){ // caso tenha chance de fazer a captura
                if(jogo.getTabuleiro().movimentar(y-2, x-2)){
                    if(jogo.getTabuleiro().getGrid()[y-1][x-1].getOcupada()){
                        if((jogo.getTabuleiro().getGrid()[y-1][x-1].getPeca().getPedra() == CorPeca.CLARA)){
                            if(!jogo.getTabuleiro().getGrid()[y-2][x-2].getOcupada()){ 
                                if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                                    jogo.jogar(y-1, x-1, y, x);
                                    return true;
                                }
                            }else{        
                                if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                                    jogo.jogar(y+1, x-1, y, x);
                                    return true;
                                }
                            }
                        }
                    }
                }else{
                    if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                        jogo.jogar(y+1, x-1, y, x);
                        return true;
                    }
                }
            }if(pecaInimigaEsquerdaC(jogo, y, x) == 1){
                if(pecasAmigasDireitaC(jogo, y, x) == 0){
                    if(pecasAmigasEsquerdaC(jogo, y , x) == 0){
                        Random escolher = new Random(0);
                            if((escolher.nextInt()%2 == 0)){
                                if(jogo.getTabuleiro().movimentar(y-1, x-1)){
                                    if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                                        jogo.jogar(y-1, x-1, y, x);
                                        return true;
                                    }
                                }
                            } if((jogo.getTabuleiro().movimentar(y+1, x-1))){
                                if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                                    jogo.jogar(y-1, x+1, y, x);
                                    return true;
                                }
                            }
                    } if(pecasAmigasEsquerda(jogo, y, x) >= 1){
                        if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                            jogo.jogar(y-1, x-1, y, x);
                            return true;
                        }
                    }
                } if(pecasAmigasDireitaC(jogo, y, x) == 1){
                    if((jogo.getTabuleiro().movimentar(y+2, x-2)) && (!(jogo.getTabuleiro().getGrid()[y+2][x-2].getOcupada()))){
                            if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                                jogo.jogar(y+1, x-1, y, x);
                                return true;
                            }
                    }else{
                        if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                            jogo.jogar(y-1, x-1, y, x);
                            return true;
                        }
                    }
                } if(pecasAmigasDireitaC(jogo, y ,x) >= 2){   
                    if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                        jogo.jogar(y-1, x-1, y, x);
                        return true;
                    }
                }
            }if(pecaInimigaEsquerdaC(jogo, y, x) >= 2){
                if((jogo.getTabuleiro().movimentar(y+2, x)) && (jogo.getTabuleiro().getGrid()[y+2][x].getOcupada())){ //evitar capitura em futuro movimento para esquerda
                    if(jogo.getTabuleiro().getGrid()[y+2][x].getPeca().getPedra() == CorPeca.CLARA){   
                        if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                            jogo.jogar(y-1, x-1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                            jogo.jogar(y+1, x-1, y, x);
                            return true;
                        }
                    }
                } if((jogo.getTabuleiro().movimentar(y-2, x)) && (jogo.getTabuleiro().getGrid()[y-2][x].getOcupada())){ //evitar capitura em futuro movimento para direita
                    if(jogo.getTabuleiro().getGrid()[y-2][x].getPeca().getPedra() == CorPeca.CLARA){
                        if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                            jogo.jogar(y+1, x-1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                            jogo.jogar(y-1, x-1, y, x);
                            return true;
                        }
                    }
                }else{
                    if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                        jogo.jogar(y-1, x-1, y, x);
                        return true;
                    }
                }
            }
        } if(pecaInimigaDireitaC(jogo, y, x) < pecaInimigaEsquerdaC(jogo, y, x)){ // QUANDO A DIREÇÃO DA DIREITA TEM MAIS CASAS DISPONIVEIS
            // fazer metodos para escolher entre o melhor
            if((pecaInimigaDireitaC(jogo, y, x) == 0)){
                if((jogo.getTabuleiro().movimentar(y+2, x-2))){ // caso tenha chance de fazer a captura
                    if((!jogo.getTabuleiro().getGrid()[y+2][x-2].getOcupada())){    
                        if(jogo.getTabuleiro().getGrid()[y+1][x-1].getOcupada()){ 
                            if((jogo.getTabuleiro().getGrid()[y+1][x-1].getPeca().getPedra() == CorPeca.CLARA)) {   
                                if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                                    jogo.jogar(y+1, x-1, y, x);
                                    return true;
                                }
                            }
                        }
                    }
                }else{
                    if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                        jogo.jogar(y-1, x-1, y, x);
                        return true;
                    }
                }
            } if(pecaInimigaDireitaC(jogo, y, x) == 1){
                if(pecasAmigasEsquerdaC(jogo, y, x) == 0){
                    if(pecasAmigasDireitaC(jogo, y , x) == 0){
                        mesmaDisponibilidadeC(jogo, y ,x);
                        return true;
                    }else if(pecasAmigasDireitaC(jogo, y, x) >= 1){   
                        if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                            jogo.jogar( y-1, x-1, y, x);
                            return true;
                        }
                    }
                } if(pecasAmigasEsquerdaC(jogo, y, x) == 1){
                    if((jogo.getTabuleiro().movimentar(y+2, x-2)) && (!(jogo.getTabuleiro().getGrid()[y+2][x-2].getOcupada()))){
                        if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                            jogo.jogar( y+1, x-1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                            jogo.jogar(y-1, x-1, y, x);
                            return true;
                        }
                    }
                }else if(pecasAmigasEsquerdaC(jogo, y ,x) >= 2){
                    if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                        jogo.jogar( y+1, x-1, y, x);
                        return true;
                    }
                }
            } if(pecaInimigaDireitaC(jogo, y, x) >= 2){
                if((jogo.getTabuleiro().movimentar(y-2, x)) && (jogo.getTabuleiro().getGrid()[y-2][x].getOcupada())){ //evitar capitura em futuro movimento para esquerda
                    if(jogo.getTabuleiro().getGrid()[y-2][x].getPeca().getPedra() == CorPeca.CLARA){
                        if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                            jogo.jogar(y+1, x-1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                            jogo.jogar( y-1, x-1, y, x);
                            return true;
                        }
                    }
                } if((jogo.getTabuleiro().movimentar(y+2, x)) && (jogo.getTabuleiro().getGrid()[y+2][x].getOcupada())){ //evitar capitura em futuro movimento para direita
                    if(jogo.getTabuleiro().getGrid()[y+2][x].getPeca().getPedra() == CorPeca.CLARA){
                        if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                            jogo.jogar( y-1, x-1, y, x);
                            return true;
                        }
                    }else{
                        if((jogo.poderMovimentar(y+1, x-1, y, x) != 0)){
                            jogo.jogar( y+1, x-1, y, x);
                            return true;
                        }
                    }
                }else{ 
                    if((jogo.poderMovimentar(y-1, x-1, y, x) != 0)){
                        jogo.jogar(y-1, x-1, y, x);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    //Verifica o movimento normal com captura
    public boolean autoMov(Jogo jogo) throws MovimentoInvalidoException{
        for(int x = 0;x < jogo.getTabuleiro().getGrid().length; x++){
            for(int y = 0; y< jogo.getTabuleiro().getGrid().length; y++){
                if(jogo.getTabuleiro().getGrid()[y][x].getOcupada()){
                    if(jogo.getTabuleiro().getGrid()[y][x].getPeca().getJogador() instanceof JogadorAutonomo){
                        if(jogo.getTabuleiro().getGrid()[y][x].getPeca().getPedra().equals(CorPeca.ESCURA)){
                            //casos em que a peça pode se movimentar de acordo com a peças proximas a ela
                            if(this.autoMovEscura(jogo, y, x)){
                                return true;
                            }
                        }else if (jogo.getTabuleiro().getGrid()[y][x].getPeca().getPedra().equals(CorPeca.CLARA)){
                            if(this.autoMovClara(jogo, y, x)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    //verificar captura caso clara
    public int autoCapturaClara(Jogo jogo){
        for(int j = 0; j < jogo.getTabuleiro().getGrid().length; j++){
            for(int i = 0; i < jogo.getTabuleiro().getGrid().length; i++){
                if(jogo.getTabuleiro().getGrid()[i][j].getOcupada()){
                    if(jogo.getTabuleiro().getGrid()[i][j].getPeca().getPedra() == CorPeca.CLARA){
                        if(jogo.getTabuleiro().movimentar(i-1,j-1)){ // verifica DIAGONAL INFERIOR DIREITA
                            if(jogo.getTabuleiro().getGrid()[i-1][j-1].getOcupada()){
                                if(jogo.getTabuleiro().getGrid()[i-1][j-1].getPeca().getPedra() == CorPeca.ESCURA){
                                    if(jogo.getTabuleiro().movimentar(i-2,j-2)){
                                        if(!(jogo.getTabuleiro().getGrid()[i-2][j-2].getOcupada())){
                                            y0 = i; x0 = j;
                                            return 3;
                                        }
                                    }
                                }
                            }
                        } if(jogo.getTabuleiro().movimentar(i+1,j-1)){
                            if(jogo.getTabuleiro().getGrid()[i+1][j-1].getOcupada()){ // DIAGONAL INFERIOR ESQUERDA
                                if(jogo.getTabuleiro().getGrid()[i+1][j-1].getPeca().getPedra() == CorPeca.ESCURA){
                                    if(jogo.getTabuleiro().movimentar(i+2,j-2)){
                                        if(!(jogo.getTabuleiro().getGrid()[i+2][j-2].getOcupada())){
                                            y0 = i; x0 = j;
                                            return 4;
                                        }
                                    }
                                }
                            }
                        } if(jogo.getTabuleiro().movimentar(i+1,j+1)){
                            if(jogo.getTabuleiro().getGrid()[i+1][j+1].getOcupada()){ // DIAGONAL SUPERIOR ESQUERDA
                                if(jogo.getTabuleiro().getGrid()[i+1][j+1].getPeca().getPedra() == CorPeca.ESCURA){
                                    if(jogo.getTabuleiro().movimentar(i+2,j+2)){
                                        if(!(jogo.getTabuleiro().getGrid()[i+2][j+2].getOcupada())){
                                            y0 = i; x0 = j;
                                            return 1;
                                        }
                                    }
                                }
                            }
                        } if(jogo.getTabuleiro().movimentar(i-1,j+1)){
                            if(jogo.getTabuleiro().getGrid()[i-1][j+1].getOcupada()){ // DIAGONAL SUPERIOR DIREITA
                                if(jogo.getTabuleiro().getGrid()[i-1][j+1].getPeca().getPedra() == CorPeca.ESCURA){
                                    if(jogo.getTabuleiro().movimentar(i-2,j+2)){
                                        if(!(jogo.getTabuleiro().getGrid()[i-2][j+2].getOcupada())){
                                            y0 = i; x0 = j;
                                            return 2;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
    //verificar captura caso escura
    public int autoCapturaEscura(Jogo jogo){
        for(int j = 0; j < jogo.getTabuleiro().getGrid().length; j++){
            for(int i = 0; i < jogo.getTabuleiro().getGrid().length; i++){
                if(jogo.getTabuleiro().getGrid()[i][j].getOcupada()){
                    if(jogo.getTabuleiro().getGrid()[i][j].getPeca().getPedra() == CorPeca.ESCURA){
                        if(jogo.getTabuleiro().movimentar(i+1,j+1)){ // verifica DIAGONAL INFERIOR DIREITA
                            if(jogo.getTabuleiro().getGrid()[i+1][j+1].getOcupada()){
                                if(jogo.getTabuleiro().getGrid()[i+1][j+1].getPeca().getPedra() == CorPeca.CLARA){
                                    if(jogo.getTabuleiro().movimentar(i+2,j+2)){
                                        if(!(jogo.getTabuleiro().getGrid()[i+2][j+2].getOcupada())){
                                            y0 = i; x0 = j;
                                            return 1;
                                        }
                                    }
                                }
                            }
                        } if(jogo.getTabuleiro().movimentar(i-1,j+1)){
                            if(jogo.getTabuleiro().getGrid()[i-1][j+1].getOcupada()){ // DIAGONAL INFERIOR ESQUERDA
                                if(jogo.getTabuleiro().getGrid()[i-1][j+1].getPeca().getPedra() == CorPeca.CLARA){
                                    if(jogo.getTabuleiro().movimentar(i-2,j+2)){
                                        if(!(jogo.getTabuleiro().getGrid()[i-2][j+2].getOcupada())){
                                            y0 = i; x0 = j;
                                            return 2;
                                        }
                                    }
                                }
                            }
                        } if(jogo.getTabuleiro().movimentar(i-1,j-1)){
                            if(jogo.getTabuleiro().getGrid()[i-1][j-1].getOcupada()){ // DIAGONAL SUPERIOR ESQUERDA
                                if(jogo.getTabuleiro().getGrid()[i-1][j-1].getPeca().getPedra() == CorPeca.CLARA){
                                    if(jogo.getTabuleiro().movimentar(i-2,j-2)){
                                        if(!(jogo.getTabuleiro().getGrid()[i-2][j-2].getOcupada())){
                                            y0 = i; x0 = j;
                                            return 3;
                                        }
                                    }
                                }
                            }
                        } if(jogo.getTabuleiro().movimentar(i+1,j-1)){
                            if(jogo.getTabuleiro().getGrid()[i+1][j-1].getOcupada()){ // DIAGONAL SUPERIOR DIREITA
                                if(jogo.getTabuleiro().getGrid()[i+1][j-1].getPeca().getPedra() == CorPeca.CLARA){
                                    if(jogo.getTabuleiro().movimentar(i+2,j-2)){
                                        if(!(jogo.getTabuleiro().getGrid()[i+2][j-2].getOcupada())){
                                            y0 = i; x0 = j;
                                            return 4;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    //metodo que auxilia no verificaçao da captura
    public int autoCapturaNormal(Jogo jogo){
        for(int j = 0; j < jogo.getTabuleiro().getGrid().length; j++){
            for(int i = 0; i < jogo.getTabuleiro().getGrid().length; i++){
                if(jogo.getTabuleiro().getGrid()[i][j].getOcupada()){
                    if(jogo.getTabuleiro().getGrid()[i][j].getPeca().getJogador() instanceof JogadorAutonomo){
                        if(jogo.getTabuleiro().getGrid()[i][j].getPeca().getPedra().equals(CorPeca.CLARA)){
                                return autoCapturaClara(jogo);   
                        }else if(jogo.getTabuleiro().getGrid()[i][j].getPeca().getPedra().equals(CorPeca.ESCURA)){
                            return autoCapturaEscura(jogo);
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    
}
