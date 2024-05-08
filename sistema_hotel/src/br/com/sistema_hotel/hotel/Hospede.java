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
         boolean checkedIn = false;
         boolean isEsperandoFila = false; // Booleano que verifica se a um hospede na fila
         Random random = new Random();
         while (!checkedIn) {
             if (hotel.checkIn(this)) {
                 checkedIn = true;
                 System.out.println(nome + " fez check-in.");
             } else {
                 if(!isEsperandoFila) { // Só exibir mensagem e adicionar na fila se não estiver já esperando
                     System.out.println(nome + " está esperando por um quarto.");
                     if (!hotel.adicionarFilaEspera(this)) {
                         System.out.println(nome + " deixou uma reclamação e foi embora.");
                         return;
                     }
                     isEsperandoFila = true; // Indica que o hospede esta na fila!
                 }

                 try {
                     Thread.sleep(random.nextInt(5000)); // Tempo aleatório para passear
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }
         try {
             Thread.sleep(random.nextInt(10000)); // Tempo aleatório para permanecer no quarto
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         hotel.checkOut(this);
         System.out.println(nome + " fez check-out.");

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
