package br.com.sistema_hotel.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Recepcionista extends Thread {
    private Hotel hotel;
    private BlockingQueue<Hospede> filaDeEspera;
    private static final int RETRY_DELAY_MS = 5000; // 5 segundos de espera para nova tentativa
    private static final int WALK_DELAY_MS = 10000; // 10 segundos de passeio pela cidade


    public Recepcionista(Hotel hotel) {
        this.hotel = hotel;
        this.filaDeEspera = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Hospede hospede = filaDeEspera.take();
                if (!checkIn(hospede)) {
                    int currentAttempts = hospede.incrementarTentativas();
                    if (currentAttempts >= 2) {
                        System.out.println(hospede.getNome() + " deixou uma reclamação e foi embora após duas tentativas de check-in.");
                        continue; // O hóspede desiste e não é colocado de volta na fila
                    }
                    System.out.println(hospede.getNome() + " vai passear pela cidade e tentar novamente mais tarde.");
                    Thread.sleep(RETRY_DELAY_MS);
                    filaDeEspera.put(hospede); // Coloca o hóspede de volta na fila após o passeio
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
