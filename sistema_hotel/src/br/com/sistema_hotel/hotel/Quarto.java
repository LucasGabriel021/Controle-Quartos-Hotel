package br.com.sistema_hotel.hotel;

import java.util.concurrent.locks.*;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import java.util.ArrayList;
import java.util.List;

public class Quarto {
    private int numero;
    private boolean vago;
    private List<Hospede> hospedes;
    private boolean chaveNaRecepcao;
    private int ocupacaoAtual;

    public Quarto(int numero) {
        this.numero = numero;
        this.vago = true;
        this.hospedes = new ArrayList<>();
        this.chaveNaRecepcao = true;
        this.ocupacaoAtual = 0;
    }

    public synchronized void adHospede(Hospede hospede) {
        this.hospedes.add(hospede);
    }

    public synchronized void rmHospede(Hospede hospede) {
        this.hospedes.remove(hospede);
    }

    public synchronized boolean Completo() {
        return this.hospedes.size() >= this.maximo;
    }

    public synchronized boolean Vazio() {
        return this.hospedes.isEmpty();
    }

    public synchronized boolean chave() {
        return this.chave;
    }

    public synchronized void retirarChave() {
        this.chave = false;
    }

    public synchronized void limpeza() {
        throw new Error("Unresolved compilation problem: \n\tThe method retornaChave() is undefined for the type Quarto\n");
    }
}
