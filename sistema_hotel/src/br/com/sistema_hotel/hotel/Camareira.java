package br.com.sistema_hotel.hotel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Camareira extends Thread {
    private final Hotel hotel;;

    public Camareira(Hotel hotel) {
        this.hotel = hotel;
    }

    public void run() {
        while (true) {
            Quarto quartoParaLimpar = null;
            synchronized (hotel) {
                while ((quartoParaLimpar = encontrarQuartoParaLimpar()) == null) {
                    try {
                        hotel.wait(); // Espera até ser notificado de que um quarto precisa de limpeza
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (quartoParaLimpar != null) {
                limparQuarto(quartoParaLimpar);
            }
        }
    }

    private Quarto encontrarQuartoParaLimpar() {
        throw new Error("Unresolved compilation problems: \n\tquartos cannot be resolved or is not a field\n\tThe method isChaveNaRecepcao() is undefined for the type Quarto\n\tThe method isVago() is undefined for the type Quarto\n");
    }

    public void limparQuarto(Quarto var1) {
        throw new Error("Unresolved compilation problems: \n\tThe method getNumero() is undefined for the type Quarto\n\tThe method getNumero() is undefined for the type Quarto\n\tThe method setChaveNaRecepcao(boolean) is undefined for the type Quarto\n\tThe method setChaveNaRecepcao(boolean) is undefined for the type Quarto\n");
    }
}
