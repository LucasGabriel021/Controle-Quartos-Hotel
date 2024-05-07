package br.com.sistema_hotel.hotel;

import java.util.Random;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Hospede extends Thread {
    private Hotel hotel;
    private String nome;
    private int membrosFamilia;

    public Hospede(Hotel hotel, String nome, int membrosFamilia) {
        this.hotel = hotel;
        this.nome = nome;
        this.membrosFamilia = membrosFamilia;
    }

    public void run() {
        throw new Error("Unresolved compilation problems: \n\tThe method checkIn(Hospede) is undefined for the type Hotel\n\tThe method adicionarFilaEspera(Hospede) is undefined for the type Hotel\n\tThe method checkOut(Hospede) is undefined for the type Hotel\n");
    }

    public int getMembrosFamilia() {
        throw new Error("Unresolved compilation problem: \n");
    }

    public String getNome() {
        throw new Error("Unresolved compilation problem: \n");
    }
}
