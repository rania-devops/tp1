// Serveur UDP:


import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Créer une socket UDP pour le serveur
            socket = new DatagramSocket(9876);

            byte[] buffer = new byte[1024];

            // Créer un paquet pour recevoir les données
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Serveur UDP en attente...");

            // Attendre la réception d'un paquet
            socket.receive(packet);

            // Convertir les données reçues en chaîne de caractères
            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Message reçu du client : " + message);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

// Client UDP:

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Créer une socket UDP pour le client
            socket = new DatagramSocket();

            // Adresse IP du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");

            // Message à envoyer au serveur
            String message = "Hello, serveur UDP!";
            byte[] data = message.getBytes();

            // Créer un paquet avec les données et les informations du serveur
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 9876);

            // Envoyer le paquet au serveur
            socket.send(packet);

            System.out.println("Message envoyé au serveur : " + message);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
