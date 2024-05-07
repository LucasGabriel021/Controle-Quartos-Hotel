package br.com.sistema_hotel.hotel;

import java.util.Random;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Recepcionista(Hotel hotel) {
        this.hotel = hotel;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Hospede hospede = hotel.atenderPedidosCheckIn();
                if (hospede != null) {
                    System.out.println("Recepcionista está realizando o check-in para " + hospede.getNome());
                    // Lógica para realizar o check-in
                    Thread.sleep(new Random().nextInt(5000)); // Simula o tempo de check-in
                    System.out.println("Check-in realizado para " + hospede.getNome());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
