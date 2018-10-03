package server;

import app.Factory;
import services.FactoryServices;

import java.io.IOException;

public class ReservationApp {

    private final static int PORT = 2500;
    private Factory factory;

    public void main(String[] args) {

        try {
            factory = new FactoryServices();
            new Thread(new Server(PORT, factory)).start();
            System.out.println("Serveur lance sur le port " + PORT);


        } catch (IOException e) {
            System.err.println("Pb lors de la cr�ation du serveur : " +  e);
        }
    }
}
