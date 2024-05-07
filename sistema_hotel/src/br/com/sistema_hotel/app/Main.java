package br.com.sistema_hotel.app;

public class Main {
public static void main(String[] args) {
        // Criando o hotel
        Hotel hotel = new Hotel(12); // Por exemplo, 10 quartos

        // Criando e iniciando as threads das entidades
        Recepcionista[] recepcionistas = new Recepcionista[5];
        for (int i = 0; i < recepcionistas.length; i++) {
            recepcionistas[i] = new Recepcionista(hotel);
            recepcionistas[i].start();
        }

        Camareira[] camareiras = new Camareira[10];
        for (int i = 0; i < camareiras.length; i++) {
            camareiras[i] = new Camareira(hotel);
            camareiras[i].start();
        }

        // Criando e iniciando as threads dos hóspedes
        for (int i = 1; i <= 50; i++) {
            Hospede hospede = new Hospede("Hospede " + i, hotel);
            hospede.start();
        }
    }
}
