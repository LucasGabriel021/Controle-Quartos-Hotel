package br.com.sistema_hotel.hotel;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;

public class Quarto {
    private int maximo;
    private List<Hospede> hospedes;
    private boolean chave;

    public Quarto(int maximo) {
        this.maximo = maximo;
        this.hospedes = new ArrayList();
        this.chave = true;
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
