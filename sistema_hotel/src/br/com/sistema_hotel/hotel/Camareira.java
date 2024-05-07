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
           Quarto quartoParaLimpar = null;
           synchronized (hotel) {
//               boolean found = false;
//               for (Quarto quarto : hotel.quartos) {
//                   if (quarto.isChaveNaRecepcao() && quarto.isVago() || !quarto.isHospedesNoQuarto()) {
//                       limparQuarto(quarto);
//                       found = true;
//                       break;
//                   }
//               }
//               if(!found) {
//                   try {
//                       hotel.wait();
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                   }
//               }

               while((quartoParaLimpar = encontrarQuartoParaLimpar()) == null) {
                   try {
                       hotel.wait(); // Espera até ser notificado de que um quarto precisa de limpeza
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
           if(quartoParaLimpar != null) {
               limparQuarto(quartoParaLimpar);
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
   
}

