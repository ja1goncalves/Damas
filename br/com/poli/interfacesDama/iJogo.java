
package br.com.poli.interfacesDama;

import br.com.poli.damas.*;

public interface iJogo {
    
    void start(Jogador jogador1, Jogador jogador2);
    void geradorTabuleiro(Casa[][] casa, Peca clara, Peca escura);
    void desenharTabuleiro(Jogo jogo);
    void cliqueParaJogar(Jogo jogo);
    void fimDeJogo(Jogo jogo);
}
