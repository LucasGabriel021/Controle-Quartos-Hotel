package br.com.sistema_hotel.hotel;

import java.util.Random;

public class Camareira extends Thread {
    private final Hotel hotel;

    public Camareira(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (hotel) {
                try {
                    hotel.wait();  // Esperar até que um quarto esteja disponível para limpeza
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                // Após ser notificado, encontrar um quarto para limpar
                Quarto quartoParaLimpar = encontrarQuartoParaLimpar();
                if (quartoParaLimpar != null) {
                    limparQuarto(quartoParaLimpar);
                }
            }
        }
    }
   
   private Quarto encontrarQuartoParaLimpar() {
       for (Quarto quarto : hotel.quartos) {
           if(quarto.isChaveNaRecepcao() && quarto.isVago()) {
               return quarto;
           }
       }
       return null;
   }
   
   public void limparQuarto(Quarto quarto) {
       synchronized (quarto) {
           System.out.println("Camareira está limpando o quarto " + quarto.getNumero());
           try {
               Thread.sleep(5000); // Tempo para limpar o quarto
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println("Camareira terminou de limpar o quarto " + quarto.getNumero());
           quarto.setChaveNaRecepcao(true);
       }
       quarto.setChaveNaRecepcao(false);

       synchronized (hotel) {
           hotel.notifyAll(); // Notifica que a limpeza do quarto foi concluida
       }

   }
   
   
}

