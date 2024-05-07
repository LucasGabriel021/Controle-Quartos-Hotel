package br.com.sistema_hotel.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Queue;
import java.util.LinkedList;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Hotel {
    private final List<Quarto> quartos;
    private final BlockingQueue<Quarto> filaEspera;
    private final BlockingQueue<Hospede> filaCheckIn;
    private final Queue<Hospede> filaEsperaCheckIn = new LinkedList<>();
    private final BlockingQueue<Quarto> filaLimpeza;
    private final Lock lock = new ReentrantLock();
    private final AtomicInteger quartosOcupados = new AtomicInteger(0);
    
    public Hotel(int numQuartos) {
        this.quartos = new ArrayList<>();
        this.filaEspera = new ArrayBlockingQueue<>(50);
        this.filaCheckIn = new ArrayBlockingQueue<>(50);
        this.filaLimpeza = new ArrayBlockingQueue<>(50);

        for (int i = 0; i < numQuartos; i++) {
            quartos.add(new Quarto(i + 1));
        }
    }

private Quarto getQuartoDisponivel() {
        lock.lock();
        try {
            for (Quarto quarto : quartos) {
                if (quarto.isDisponivel()) {
                    return quarto;
                }
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public void solicitarCheckIn(Hospede hospede) throws InterruptedException {
        if (quartosOcupados.get() < quartos.size()) {
            filaCheckIn.offer(hospede);
            atenderPedidosCheckIn();
        } else {
            filaEspera.offer(getQuartoDisponivel()); // Adiciona o quarto à fila de espera
            System.out.println(hospede.getNome() + " está aguardando na fila de espera.");
        }
    }
    
public void fazerCheckOut(Hospede hospede) {
        Quarto quarto = encontrarQuartoDoHospede(hospede);
        if (quarto != null) {
            quarto.removerHospede(hospede);
            quarto.devolverChave(); // Devolve a chave à recepção
            quarto.iniciarLimpeza(); // Inicia a limpeza do quarto
            System.out.println("Hóspedes do quarto " + quarto.getNumero() + " saíram para passear. Quarto sendo limpo.");
            quartosOcupados.decrementAndGet();
            System.out.println(hospede.getNome() + " fez check-out do quarto " + quarto.getNumero() + " e devolveu a chave à recepção.");
        }
    }

public synchronized boolean adicionarFilaEspera(Hospede hospede) {
        return filaEspera.offer(hospede);
    }

    public synchronized Hospede proximoFilaEspera() {
        return filaEspera.poll();
    }

    public synchronized List<Camareira> getCamareiras() {
        return camareiras;
    }

    public Quarto[] getQuartos() {
        return quartos.toArray(new Quarto[0]);
    }

    public List<Recepcionista> getRecepcionistas() {
        return recepcionistas;
    }
}





    
