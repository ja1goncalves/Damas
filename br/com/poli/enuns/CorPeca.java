/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.poli.enuns;

public enum CorPeca {
	
	CLARA(1), ESCURA(0);
		
		private final int cor;
		
		private CorPeca(int cor) {
			this.cor = cor;
		}
		
		public int getCorPeca() {
			return this.cor;
		}
	
}