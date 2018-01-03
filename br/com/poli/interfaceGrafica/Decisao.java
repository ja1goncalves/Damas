/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poli.interfaceGrafica;

import br.com.poli.damas.Jogador;
import br.com.poli.damas.JogadorAutonomo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author joaog
 */
public class Decisao {
    private static JFrame frame = new JFrame();
    private static JFrame frame2 = new JFrame();
    private static JFrame frame3 = new JFrame();
    private static JFrame instrucao = new JFrame();
    Icon logo = new ImageIcon(getClass().getResource("logo.png"));
    private static JButton doisJogadores = new JButton("Jogador 1 vs Jogador 2");
    private static JButton umJogador = new JButton("Jogador 1 vs maquina");
    private static JButton instruction = new JButton("Instruções");
    private static JButton sair = new JButton("X");
    
    public JFrame getFrame(){
        return this.frame;
    }
    
    public static void main(String[] args){
        Decisao tal = new Decisao();
        JLabel titulo = new JLabel(tal.logo);
        tal.frame.getContentPane().setBackground(Color.black);
        //detalhes das variaveis
        titulo.setBounds(40,-70,400,500);
        doisJogadores.setBounds(135,370,200,50);
        doisJogadores.setBackground(Color.WHITE);
        umJogador.setBounds(135,450,200,50);
        umJogador.setBackground(Color.WHITE);
        sair.setBounds(430, 5, 50, 30);
        sair.setBackground(Color.red);
        sair.setForeground(Color.WHITE);
        instruction.setBounds(3, 3, 100, 25);
        instruction.setBackground(Color.DARK_GRAY);
        instruction.setForeground(Color.white);  
        
        //clique no botão de dois jogadores
        doisJogadores.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evnt){
                
                JLabel dama = new JLabel(tal.logo);
                dama.setBounds(40,-70,400,500);
                
                JTextField jogador1 = new JTextField("Jogador1");
                jogador1.setBounds(135,350,200,50);
                jogador1.setBackground(Color.WHITE);
                JTextField jogador2 = new JTextField("Jogador2");
                jogador2.setBounds(135,410,200,50);
                jogador2.setBackground(Color.BLACK);
                jogador2.setForeground(Color.WHITE);
                
                JButton voltar = new JButton("Voltar");
                voltar.setBounds(10, 5, 70, 25);
                voltar.setBackground(Color.WHITE);
                JButton jogar = new JButton("Jogar");
                jogar.setBounds(200, 470, 70, 30); 
                JButton sair = new JButton("X");
                sair.setBounds(430, 5, 50, 30);
                sair.setBackground(Color.red);
                sair.setForeground(Color.WHITE);
                
                sair.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evnt){
                        System.exit(0);
                    } 
                });    
                
                voltar.addActionListener(new ActionListener(){
                   public void actionPerformed(ActionEvent evnt){
                       Decisao nova = new Decisao();
                       nova.getFrame().setVisible(true);
                       frame3.dispose();
                   } 
                });   
                
                jogar.addActionListener(new ActionListener(){
                   public void actionPerformed(ActionEvent evnt){
                        GeradorTabuleiro novo = new GeradorTabuleiro();
                        novo.start(new Jogador(jogador1.getText()), new Jogador(jogador2.getText()));
                        frame3.dispose();
                   } 
                });
                
                frame3.setSize(500, 600);
                frame3.add(dama);
                frame3.add(sair);
                frame3.add(jogador1);
                frame3.add(jogador2);
                frame3.add(voltar);
                frame3.add(jogar);
                frame3.setLayout(null);
                frame3.setVisible(true);
                frame.dispose();
            }
        });
       
        //clique no botão de um jogadores
        umJogador.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evnt){
                JLabel damas = new JLabel(tal.logo);
                damas.setBounds(40,-70,400,500);
                
                JTextField jogador = new JTextField("jogador");
                jogador.setBounds(150,70,220,50);
                jogador.setFont(new Font("Dialog", Font.PLAIN, 18));
                
                JLabel escolha = new JLabel("Escolha com que peça vai jogar:");
                escolha.setBounds(170, 110, 220, 50);
                
                JButton clara = new JButton("Jogar com clara");
                clara.setBounds(135,370,200,50);
                clara.setBackground(Color.WHITE);
                
                JButton escura = new JButton("Jogar como escura");
                escura.setBackground(Color.BLACK);
                escura.setForeground(Color.WHITE);
                escura.setBounds(135,450,200,50);
                
                JButton voltar = new JButton("Voltar");
                voltar.setBounds(10, 5, 70, 25);
                voltar.setBackground(Color.WHITE);
                
                JButton sair = new JButton("X");
                sair.setBounds(430, 5, 50, 30);
                sair.setBackground(Color.red);
                sair.setForeground(Color.WHITE);
                
                frame2.setSize(500, 600);
                frame2.add(damas);
                frame2.add(sair);
                frame2.add(jogador);
                frame2.add(clara);
                frame2.add(escura);
                frame2.add(escolha);
                frame2.add(voltar);
                
                sair.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evnt){
                        System.exit(0);
                    } 
                });
                
                voltar.addActionListener(new ActionListener(){
                   public void actionPerformed(ActionEvent evnt){
                       frame2.dispose();
                       frame.setVisible(true);
                   } 
                });
                
                clara.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evnt){
                        frame2.dispose();
                        GeradorTabuleiro inicio = new GeradorTabuleiro();
                        Jogador jogador1 = new Jogador(jogador.getText());
                        inicio.start(jogador1, new JogadorAutonomo("Bruno"));
                    }
                });
                
                escura.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evnt){
                        frame2.dispose();
                        GeradorTabuleiro inicio = new GeradorTabuleiro();
                        Jogador jogador1 = new Jogador(jogador.getText());
                        inicio.start(new JogadorAutonomo("Bruno"), jogador1);
                    }
                });
                
                frame2.setLayout(null);
                frame2.setVisible(true);
                frame.dispose();
            }
        });
        
        sair.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent evnt){
               System.exit(0);
           } 
        });
        
        instruction.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent evnt){ 
               instrucao.setSize(500, 600);
               instrucao.getContentPane().setBackground(Color.black);
               
                Icon texto = new ImageIcon(getClass().getResource("texto.png"));
                JLabel ler = new JLabel(texto);
                JLabel titulo = new JLabel("INSTRUÇÃO");
                ler.setBounds(-10, -30, 500, 600);
                titulo.setForeground(Color.WHITE);
                titulo.setBounds(160, 5, 200, 50);
                titulo.setFont(new Font("Dialog", Font.ITALIC, 28));
               
                JButton quit = new JButton("X");
                quit.setBounds(430, 5, 50, 30);
                quit.setBackground(Color.red);
                quit.setForeground(Color.WHITE);
                quit.addActionListener(new ActionListener(){
                   public void actionPerformed(ActionEvent evnt){
                       System.exit(0);
                   } 
                });
                
                JButton back = new JButton("Voltar");
                back.setBounds(10, 5, 70, 25);
                back.setBackground(Color.WHITE);
                back.addActionListener(new ActionListener(){
                   public void actionPerformed(ActionEvent evnt){
                       frame.setVisible(true);
                       instrucao.dispose();
                   } 
                });
               instrucao.add(titulo);
               instrucao.add(quit);
               instrucao.add(back);
               instrucao.add(ler);
               instrucao.setLayout(null);
               instrucao.setVisible(true);
               frame.dispose();
           }
        });
        
        //detalhes da frame
        frame.setSize(500, 600);
        frame.add(titulo);
        frame.add(doisJogadores);
        frame.add(umJogador);
        frame.add(sair);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(instruction);
    }
}
