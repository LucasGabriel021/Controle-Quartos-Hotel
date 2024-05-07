package br.com.sistema_hotel.hotel;

import java.util.Random;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Camareira extends Thread {
    private final Hotel hotel;;

    public Camareira(Hotel hotel) {
        this.hotel = hotel;
    }

   @Override
    public void run() {
        while (true) {
            try {
                Quarto quarto = hotel.getQuartoParaLimpeza();
                if (quarto != null) {
                    System.out.println("Camareira está limpando o quarto " + quarto.getNumero());
                    // Lógica para limpar o quarto
                    Thread.sleep(new Random().nextInt(5000)); // Simula o tempo de limpeza
                    quarto.setDisponivel(true);
                    quarto.setChaveNaRecepcao(true);
                    System.out.println("Camareira terminou de limpar o quarto " + quarto.getNumero());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

