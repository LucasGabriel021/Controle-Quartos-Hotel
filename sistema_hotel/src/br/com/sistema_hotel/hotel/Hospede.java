package br.com.sistema_hotel.hotel;

import java.util.Random;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Hospede extends Thread {
    private final Hotel hotel;
    private final String nome;
    private int tentativasCheckIn = 0;
    private static final int LIMITE_TENTATIVAS_CHECKIN = 2;

    public Hospede(String nome, Hotel hotel) {
        this.nome = nome;
        this.hotel = hotel;
    }

     @Override
    public void run() {
        try {
            while (tentativasCheckIn < LIMITE_TENTATIVAS_CHECKIN) {
                hotel.solicitarCheckIn(this);
                Quarto quarto = hotel.encontrarQuartoDoHospede(this);
                while (quarto != null && !quarto.isLimpo()) {
                    Thread.sleep(1000);
                    quarto = hotel.encontrarQuartoDoHospede(this);
                }
                if (quarto != null) {
                    System.out.println(nome + " retornou ao quarto " + quarto.getNumero());
                    break; // Sai do loop se o check-in for bem-sucedido
                } else {
                    tentativasCheckIn++;
                    System.out.println(nome + " não conseguiu fazer o check-in. Tentativa " + tentativasCheckIn);
                }
            }
            if (tentativasCheckIn == LIMITE_TENTATIVAS_CHECKIN) {
                System.out.println(nome + " deixou uma reclamação e foi embora.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getMembrosFamilia() {
        return membrosFamilia;
    }

    public String getNome() {
        return nome;
    }
}
