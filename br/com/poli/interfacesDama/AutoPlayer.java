/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poli.interfacesDama;

import br.com.poli.damas.*;
import br.com.poli.exceptions.MovimentoInvalidoException;
/**
 *
 * @author joaog
 */
public interface AutoPlayer {
    public boolean jogarAuto(Jogo jogo) throws MovimentoInvalidoException;
    public Jogador vencedor(Jogo jogo);
}
