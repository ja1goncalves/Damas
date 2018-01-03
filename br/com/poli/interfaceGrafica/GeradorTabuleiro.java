/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poli.interfaceGrafica;

import br.com.poli.interfacesDama.iJogo;
import br.com.poli.damas.*;
import br.com.poli.enuns.*;
import br.com.poli.exceptions.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author joaog
 */
public class GeradorTabuleiro implements iJogo {
    public static int linha = 8;
    public static int coluna = 8;
    private int contador;
    
        //Objetos concretos da frame 1
    public static final JFrame frameTabuleiro = new JFrame();
    public static JButton botaoCasas[][] = new JButton[linha][coluna];
    public static JLabel movimento = new JLabel();
    
        //Objetos para o desenvolver do jogo
    private static Jogador jogador1 = new Jogador(null);
    private static Jogador jogador2 = new Jogador(null);
    private static final Casa[][] casa = new Casa[linha][coluna];
    private static Peca clara = new Peca(jogador1, CorPeca.CLARA);
    private static Peca escura = new Peca(jogador2, CorPeca.ESCURA);
    private static Dama dama = new Dama(null, null);
    private static Tabuleiro tabuleiro = new Tabuleiro(clara, escura, dama);
    private static Jogo jogo = new Jogo(null, null, tabuleiro);
    
        //Objetos da janela de jogo
    private static JButton desistir = new JButton("Desistir");
    private static JLabel jogadasParaFim = new JLabel("Jogadas sem captura: "+jogo.getJogadasParaFim());
    private static JLabel tempo = new JLabel("Tempo: 00:00:00");
    private static JLabel pecasClaras = new JLabel("Peças claras: "+jogo.getContClara());
    private static JLabel pecasEscuras = new JLabel("Peças escuras: "+jogo.getContEscura());
    private final Icon pecaClara = new ImageIcon(getClass().getResource("pecaclara.png"));
    private final Icon pecaEscura = new ImageIcon(getClass().getResource("pecaescura.png"));
    private final Icon damaClara = new ImageIcon(getClass().getResource("damaClara.png"));
    private final Icon damaEscura = new ImageIcon(getClass().getResource("damaEscura.png"));

    public Jogador getJogador1(){
        return this.jogador1;
    }
    public void setJogador1(Jogador jogador1){
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2(){
        return this.jogador2;
    }
    public void setJogador2(Jogador jogador2){
            this.jogador2 = jogador2;
    }
  
    public static void main (String args[]){
        //criação de uma nova frame pra dama a cada vez que alguem quer jogar
        GeradorTabuleiro tal = new GeradorTabuleiro();
        
        JLabel jogadorClara = new JLabel("A peça clara é do jogador "+jogo.getJogador1().getNome());
        JLabel jogadorEscura = new JLabel("A peça escura é do jogador "+jogo.getJogador2().getNome());
        //localização do objeto na janela de jogo
        tal.jogadasParaFim.setBounds(687, 80, 200, 50);
        tal.desistir.setBounds(710, 0, 100, 60);
        tal.tempo.setBounds(710, 100, 210, 100);
        jogadorClara.setBounds(650, 160, 200, 50);
        jogadorEscura.setBounds(650, 175, 230, 50);
        tal.pecasClaras.setBounds(710, 220, 200, 50);
        tal.pecasEscuras.setBounds(705, 235, 200, 50);
        tal.movimento.setBounds(690, 65, 200, 50);
        
        //Adicionar os objetos na frame da interface
        tal.frameTabuleiro.add(desistir);
        tal.frameTabuleiro.add(jogadasParaFim);
        tal.frameTabuleiro.add(tempo);
        tal.frameTabuleiro.add(jogadorClara);
        tal.frameTabuleiro.add(jogadorEscura);
        tal.frameTabuleiro.add(pecasClaras);
        tal.frameTabuleiro.add(pecasEscuras);
        tal.frameTabuleiro.add(movimento);
        
        //tamanho do tabuleiro
        tal.frameTabuleiro.setSize(900, 676);
        tal.tabuleiro.gerarTabuleiro(casa);
        tal.jogo.iniciarPartida(jogo.getJogador1(), jogo.getJogador2(), tabuleiro);
        tal.geradorTabuleiro(casa, clara, escura);
        
        AudioClip musica = null;
        try {
             musica = Applet.newAudioClip(new File("musiquinhas.mp3").toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(GeradorTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        musica.play();
        
        //caso algum jogador clique no desistir
        desistir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evnt){
                if(tal.jogo.getJogadorAtual().equals(jogo.getJogador1())){
                    jogo.setVencedor(tal.jogo.getJogador2());
                }else if (tal.jogo.getJogador2() instanceof JogadorAutonomo){
                    jogo.setVencedor(tal.jogo.getJogador2());
                }else if(tal.jogo.getJogadorAtual().equals(jogo.getJogador2())){
                    jogo.setVencedor(tal.jogo.getJogador1());
                }else if(tal.jogo.getJogador1() instanceof JogadorAutonomo){
                    jogo.setVencedor(tal.jogo.getJogador1());
                }
                tal.fimDeJogo(jogo);
                frameTabuleiro.remove(desistir);
                frameTabuleiro.dispose();
            }
        });
        tal.iniciaCronometro();
        tal.cliqueParaJogar(jogo);
        
        tal.frameTabuleiro.setLayout(null);
        tal.frameTabuleiro.setVisible(true);
    } 
    //localiza cada botão na frame
    public void geradorTabuleiro(Casa[][] casa, Peca clara, Peca escura){
        for (int y = 0; y < casa.length; y++) {
            for (int x = 0; x < casa[0].length; x++) {
                if(casa[y][x].getCorCasa() == CorCasa.PRETA){
                    if(casa[y][x].getPeca() == null){
                        botaoCasas[y][x] = new JButton();
                        botaoCasas[y][x].setBackground(Color.BLACK);
                        //botao[y][x].setOpaque(true);
                        botaoCasas[y][x].setBorderPainted(false);
                    }else{
                        if((casa[y][x].getPeca() != null) && (casa[y][x].getPeca().getPedra() == escura.getPedra())){
                            botaoCasas[y][x] = new JButton(pecaEscura);
                            botaoCasas[y][x].setBorderPainted(false);
                        }else if((casa[y][x].getPeca() != null) && (casa[y][x].getPeca().getPedra() == clara.getPedra())){
                            botaoCasas[y][x] = new JButton(pecaClara);
                            botaoCasas[y][x].setFocusPainted(false);
                        }
                            botaoCasas[y][x].setBackground(Color.BLACK);
                            botaoCasas[y][x].setOpaque(true);
                            botaoCasas[y][x].setBorderPainted(false);
                    }
                }else if((casa[y][x].getPeca() == null) && (casa[y][x].getCorCasa() == CorCasa.BRANCA)){
                    botaoCasas[y][x] = new JButton("-");
                    botaoCasas[y][x].setBackground(Color.WHITE);
                    botaoCasas[y][x].setOpaque(true);
                    botaoCasas[y][x].setBorderPainted(false);
                    botaoCasas[y][x].setOpaque(true);
                }
                botaoCasas[y][x].setBounds(80 * y, 80 * x, 80, 80);
                frameTabuleiro.add(botaoCasas[y][x]);
            }
        }
    }
    
    //desenha os boões na frame a patir de sua localização
    public void desenharTabuleiro(Jogo jogo) {
       for (int y = 0; y < botaoCasas.length; y++) {
           for (int x = 0; x < botaoCasas[0].length; x++) {
               if (jogo.getTabuleiro().getGrid()[y][x].getOcupada()) {
                   if (jogo.getTabuleiro().getGrid()[y][x].getPeca() instanceof Dama) {
                       if (jogo.getTabuleiro().getGrid()[y][x].getPeca().getPedra().equals(CorPeca.ESCURA)) {
                           botaoCasas[y][x].setIcon(damaEscura);
                       } else if (jogo.getTabuleiro().getGrid()[y][x].getPeca().getPedra().equals(CorPeca.CLARA)) {
                           botaoCasas[y][x].setIcon(damaClara);
                       }
                   } else {
                       if (jogo.getTabuleiro().getGrid()[y][x].getPeca().getPedra().equals(CorPeca.ESCURA)) {
                           botaoCasas[y][x].setIcon(pecaEscura);
                       } else if (jogo.getTabuleiro().getGrid()[y][x].getPeca().getPedra().equals(CorPeca.CLARA)) {
                           botaoCasas[y][x].setIcon(pecaClara);
                       }
                   }

               } else {
                   if (jogo.getTabuleiro().getGrid()[y][x].getCorCasa().equals(CorCasa.PRETA)) {
                       try {
                           botaoCasas[y][x].setIcon(null);
                       } catch (Exception e) {

                       }
                   }
               }
           }
       }

    }
    
    //metodo de ação da casa
    public void cliqueParaJogar(Jogo jogo) {
        int i, j;
        for (j = 0; j < botaoCasas.length; j++) {
            for (i = 0; i < botaoCasas[0].length; i++) {
                int i0, j0;
                i0 = i;
                j0 = j;
                linha = 8;
                coluna = 8;
                botaoCasas[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (linha == 8) {
                            linha = i0; // salva a coordenada "y" do primeiro clique
                            coluna = j0; // salva a coorderna "x" do primeiro clique
                        } else {
                            try {
                                jogo.jogar(i0, j0, linha, coluna);
                                if((jogo.isFimDeJogo(i0, j0))){
                                    fimDeJogo(jogo);
                                }else{
                                    desenharTabuleiro(jogo);
                                    movimento.setText("Movimento correto");
                                    movimento.setForeground(Color.GREEN);
                                    
                                        if((jogo.getJogador2() instanceof JogadorAutonomo)){
                                            desenharTabuleiro(jogo);
                                            jogo.getAutoPlayer().jogarAuto(jogo);
                                            desenharTabuleiro(jogo);
                                        }else if(jogo.getJogador1() instanceof JogadorAutonomo){
                                            desenharTabuleiro(jogo);
                                            jogo.getAutoPlayer().jogarAuto(jogo);
                                            desenharTabuleiro(jogo);
                                        }
                                }
                                if(((jogo.jogar(i0, j0, linha, coluna) == 1)&&(jogo.poderMovimentar(i0,j0,linha,coluna) == 0)) || (jogo.jogar(i0, j0, linha, coluna) == 2))
                                    throw new MovimentoInvalidoException(i0,j0);
                            } catch (MovimentoInvalidoException ex) {
                                ex.toString();
                                movimento.setText("Movimento incorreto");
                                movimento.setForeground(Color.red);
                            }
                                frameTabuleiro.add(movimento);
                            linha = 8;
                            coluna = 8;
                            //Mudança dos objetos da frame após o clique
                            jogadasParaFim.setText("Jogadas sem captura: "+jogo.getJogadasParaFim());
                            pecasClaras.setText("Peças claras: "+jogo.getContClara());
                            pecasEscuras.setText("Peças escuras: "+jogo.getContEscura());
                        }
                    }
                });

            }
        }

    }
    
    //metodo para fim de jogo
    public void fimDeJogo(Jogo jogo){
        //nova frame de declaração do fim
        JFrame fim = new JFrame();
        fim.setSize(300, 250);
        AudioClip musica = null;
        try {
             musica = Applet.newAudioClip(new File("musiquinhas.mp3").toURL());
        } catch (MalformedURLException ex) {
            Logger.getLogger(GeradorTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        musica.play();
        //componentes da frame
        URL url = this.getClass().getResource("vitoria.gif");
        Icon vitoria = new ImageIcon(url);
        JLabel imagem = new JLabel(vitoria);
        imagem.setBounds(0,0,308,232);
        JLabel finalgame = new JLabel("Final de Jogo");
        
        if(jogo.getVencedor() != null){
            JLabel vencedor = new JLabel(this.jogo.getVencedor().getNome()+" VENCEU O JOGO");
            vencedor.setBounds(15, 70, 400, 80);
            vencedor.setForeground(Color.WHITE);
            vencedor.setFont(new Font("Dialog", Font.ITALIC, 18));
            fim.add(vencedor);
        }else{
            JLabel vencedor = new JLabel("A PARTIDA ACABOU EMPATADA");
            vencedor.setFont(new Font("Dialog", Font.ITALIC, 18));
            vencedor.setForeground(Color.WHITE);
            vencedor.setBounds(5, 70, 400, 80);
            fim.add(vencedor);
        }
        JButton sair = new JButton("X");
        sair.setBackground(Color.red);
        sair.setForeground(Color.white);
        //tamanhos
        finalgame.setBounds(140, 35, 80, 35);
        sair.setBounds(310, 10, 45, 30);
        
        //Sair para outro jogo
        sair.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
        fim.setSize(385, 272);
        fim.add(imagem);
        fim.add(finalgame);
        fim.add(sair);
        frameTabuleiro.dispose();
        fim.setLayout(null);
        fim.setVisible(true);
    }
    
    //metodo que calculo e mostra o cronometro na label de tempo
    public void iniciaCronometro(){
        Timer timer = new Timer();  
            TimerTask tarefa;
            tarefa = new TimerTask() {     
                @Override
                public void run()
                {
                    contador++;
                    int seg  = contador % 60;
                    int min  = contador / 60;
                    int hora = min / 60;
                    min     %= 60;
                    tempo.setText("Tempo: " + hora + ":" + min + ":" + seg);  
                }      
            };
            timer.scheduleAtFixedRate(tarefa, 0, 1000);
        }
    
    public void start(Jogador jogador1, Jogador jogador2){
        String[] string = new String[7];
        if(jogador1 instanceof JogadorAutonomo){
            this.jogo.setJogador1(jogador1);
            this.jogo.getJogador1().setNome(jogador1.getNome());
            setJogador1(jogador1);
        }
            this.jogo.setJogador1(jogador1);
            this.jogo.getJogador1().setNome(jogador1.getNome());
        
        this.setJogador1(jogador1);
        if(jogador2 instanceof JogadorAutonomo){
            this.jogo.setJogador2(jogador2);
            this.setJogador2(jogador2);
        }else{
            this.jogo.setJogador2(jogador2);
            this.jogo.getJogador2().setNome(jogador2.getNome());
        }
        System.out.println("Jogo entre "+jogo.getJogador1().getNome()+" e "+jogo.getJogador2().getNome());
        
            main(string);
    }
}
