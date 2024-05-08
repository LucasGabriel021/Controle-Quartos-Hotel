package br.com.sistema_hotel.hotel;

import java.util.Random;

public class Hospede extends Thread {

    private Hotel hotel;
    private String nome;
    private int membrosFamilia;
    private int tentativas = 0;
    private boolean estadiaConcluida = false;
    private Quarto quarto;

    public Hospede(Hotel hotel, String nome, int membrosFamilia) {
        this.hotel = hotel;
        this.nome = nome;
        this.membrosFamilia = membrosFamilia;
    }

    @Override
    public void run() {
        try {
            Recepcionista recepcionista = hotel.getRecepciistaAleatoria(); // Obtem uma recepcionista aleatoria

            // Adicionar ao final da fila de espera
            recepcionista.adicionarFilaDeEspera(this);

            while(!estadiaConcluida) {
                synchronized (this) {
                    if(recepcionista.checkIn(this)) {
                        break; // Sai do loop se o check-in foi bem-sucedido
                    }
                    System.out.println("Aguardando check-in para " + getNome());
                    wait(1000);  // Espera um pouco antes de tentar novamente
                }
            }

            // Realizar estadia
            realizarEstadia();

            // Este loop aguarda até o checkout
            synchronized (this) {
                while(!estadiaConcluida) {
                    wait();  // Aguarda notificação da recepcionista de que o check-out foi realizado
                }
            }

            // Check-out após a estadia
            recepcionista.checkOut(this);
            System.out.println(getNome() + " e seu grupo de " + getMembrosFamilia()  + " pessoas, completou o check-out e está deixando o hotel.");

        } catch(InterruptedException e) {
            System.out.println("Hospede interrompido: " + getNome() + " não completou sua estadia devido a uma interrupção.");
            Thread.currentThread().interrupt();
        }
    }

    public int incrementarTentativas() {
        return tentativas++;
    }

    public int getTentativas() {
        return tentativas;
    }

    public int getMembrosFamilia() {
        return membrosFamilia;
    }

    public String getNome() {
        return nome;
    }

    public boolean isEstadiaConcluida() {
        return estadiaConcluida;
    }

    public void setEstadiaConcluida(boolean estadiaConcluida) {
        this.estadiaConcluida = estadiaConcluida;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void sairParaPassear() {
        // Hospedes devem deixar a chave na recepção ao sair para passear
        if (quarto != null && !quarto.isChaveNaRecepcao()) {
            System.out.println(getNome() + " e seu grupo estão saindo para passear.");
            quarto.deixarChaveNaRecepcao(this.nome);
        } else {
            System.out.println("Erro: " + getNome() + " não tem um quarto atribuído.");
        }
    }
}
