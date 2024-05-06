package br.com.atv2;

import java.util.ArrayList;
import java.util.List;

public class Quarto {
	private int maximo;
	private List<Hospede> hospedes;
	private boolean chave;
	
	public Quarto(int maximo) {
		this.maximo = maximo;
		this.hospedes = new ArrayList<>();
		this.chave = true; 
	}
	
	public synchronized void adHospede(Hospede hospede) {
		hospedes.add(hospede);
	}
	
	public synchronized void rmHospede(Hospede hospede) {
		hospedes.remove(hospede);
	}
	
	public synchronized boolean Completo() {
		return hospedes.size()>=maximo;
	}
	
	public synchronized boolean Vazio() {
		return hospedes.isEmpty();
	}
	
	public synchronized boolean chave() {
		return chave;
	}
	
	public synchronized void retirarChave() {
		chave = false;
	}
	
	public synchronized void limpeza() {
		hospedes.clear();
		retornaChave();
		System.out.println("Limpeza do quarto completa");
	}
}
