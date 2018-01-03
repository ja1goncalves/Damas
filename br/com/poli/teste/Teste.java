package br.com.poli.teste;

import br.com.poli.damas.*;
import br.com.poli.enuns.*;
import br.com.poli.exceptions.MovimentoInvalidoException;
public class Teste {
	public static void main(String[] args) throws MovimentoInvalidoException { 
		Jogador joao = new Jogador("João");
		Jogador ademario = new Jogador("Ademario");
		
		Peca pedraClara = new Peca(joao, CorPeca.CLARA);
		Peca pedraEscura = new Peca(ademario, CorPeca.ESCURA);
                
                Dama dama = new Dama(null, null);
		
		Tabuleiro tabuleiro = new Tabuleiro(pedraClara, pedraEscura, dama);
		Jogo jogo = new Jogo(joao, ademario, tabuleiro);

		System.out.println("peça "+pedraClara.getPedra()+"[o] do jogador "+joao.getNome());
		System.out.println("peça "+pedraEscura.getPedra()+"[e] do jogador "+ademario.getNome()+"\n");
		
		jogo.iniciarPartida(joao, ademario, tabuleiro);
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
                //No console mostra também as jogadas, acompanhem com cada comentario o mevimento das peças
		
		jogo.autoPlay(5, 4, 6, 5); // jogada clara (1)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(6, 3, 7, 2); // jogada escura (2)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(6, 3, 5, 4); // jogada clara (3)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(6, 3, 5, 2); // jogada escura (4)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(5, 4, 4, 5); // jogada clara (5) [captura obrigatória]
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(4, 3, 3, 2); // jogada escura (6)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(4, 3, 5, 4); // jogada clara (7)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(3, 2, 2, 1); // jogada escura (8)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(5, 4, 4, 5); // jogada clara (9)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(5, 4, 4, 3); // jogada clara* (10)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(1, 4, 0, 5); // jogada escura (11)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	
		jogo.autoPlay(3, 2, 4, 1); // jogada escura (12)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.autoPlay(3, 4, 2, 5); // jogada clara (13)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.autoPlay(4, 3, 3, 2); // jogada escura (14)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.autoPlay(3, 4, 3, 4); // jogada clara (15)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.autoPlay(3, 4, 6, 1); // jogada escura (16)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.autoPlay(4, 5, 3, 6); // jogada clara (17)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.autoPlay(5, 4, 4, 3); // jogada escura (18)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.autoPlay(3, 4, 4, 5); // jogada clara (19)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.autoPlay(2, 3, 1, 2); // jogada escura (20)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(5, 2, 6, 3); // jogada clara (21)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(6, 1, 7, 0); // jogada escura (22)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(3, 4, 5, 2); // jogada clara (23)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(3, 4, 2, 3); // jogada escura (24)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(-1, 0, 0, 1); // jogada invalida da peça escura
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(3, 4, 7, 0); // jogada clara (25)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(6, 1, 5, 0); // jogada escura (26)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(3, 4, 2, 5); // jogada clara (27)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(4, 1, 3, 0); // jogada escura (28)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(6, 3, 7, 4); // jogada clara (29)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(5, 2, 4, 1); // jogada escura (30)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(3, 4, 0, 5); // jogada clara (31)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(2, 1, 1, 0); // jogada escura (32)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(4, 1, 0, 5); // jogada invalida
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(1, 4, 0, 5); // jogada clara (33)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(1, 2, 0, 1); // jogada escura (34)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(2, 3, 1, 4); // jogada clara (35)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(3, 4, 2, 1); // jogada escura (36)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(2, 5, 1, 6); // jogada clara (37)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(1, 2, 3, 4); // jogada escura (38)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(1, 4, 0, 7); // jogada clara (39)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(3, 2, 2, 1); // jogada escura (40)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(3, 4, 2, 5); // jogada clara (41)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(4, 3, 3, 2); // jogada escura (42)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(4, 3, 3, 4); // jogada clara (43)
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
                
		jogo.jogar(4, 3, 7, 6); // jogada escura
		tabuleiro.desenharTabuleiro();System.out.println("\n\n");
	}
}