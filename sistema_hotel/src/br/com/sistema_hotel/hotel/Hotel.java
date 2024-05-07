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

	public synchronized boolean checkIn(Hospede hospede) {
	    int membrosRestantes = hospede.getMembrosFamilia();
	    while(membrosRestantes > 0) {
	        for (Quarto quarto : quartos) {
	            if (quarto.isVago()) {
	//                Se o quarto estiver vago, aloca um número máximo de 4 membros
	//                (ou menos, se houver menos membros restantes) para esse quarto.
	                int membrosAlocados = Math.min(membrosRestantes, 4);
	                System.out.println("Membros alocados: " + membrosAlocados);
	                quarto.adicionarHospede(hospede, membrosAlocados);
	
	                // System.out.println(quarto.getNumero());
	                quarto.adicionarHospede(hospede, membrosAlocados);
	                membrosRestantes -= membrosAlocados;
	                if(membrosRestantes <= 0) {
	                    hospedesAtivos.incrementAndGet(); // Incrementar o contador de hóspedes ativos
	                    return true;
	                }
	            }
	        }
	        if (membrosRestantes > 0) {
	            return false; // Há membros restantes, mas não há quartos suficientes disponíveis
	        }
	    }
	    return true;
	}
	
	 public synchronized void checkOut(Hospede hospede) {
	        for (Quarto quarto : quartos) {
	            if (quarto.getHospedes().contains(hospede)) {
	                quarto.removerHospede(hospede);
	                quarto.setChaveNaRecepcao(true); // Marcar a chave como na recepção para limpeza
	                notifyAll(); // Notificar camareiras para limpeza do quarto
	                break;
	            }
	        }
	        if(hospedesAtivos.decrementAndGet() == 0) {
	            System.out.println("Não há mais hospedes no hotel, portanto o sistema será encerrado");
	            System.exit(0); // Método encerra a aplicação se não houver hóspedes ativos!
	        }
	    }
	    
	    public synchronized boolean temQuartoDisponivel() {
	        for (Quarto quarto : quartos) {
	            if (quarto.isVago()) {
	                return true;
	            }
	        }
	        return false;
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





    
