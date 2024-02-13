// Serveur TCP:

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // Créer un socket serveur TCP lié au port 9876
            serverSocket = new ServerSocket(9876);
            System.out.println("Serveur TCP en attente...");

            // Attendre qu'un client se connecte
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté");

            // Obtenir le flux d'entrée du client
            InputStream inputStream = clientSocket.getInputStream();

            // Lire les données du client
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            // Convertir les données en chaîne de caractères
            String message = new String(buffer, 0, bytesRead);
            System.out.println("Message reçu du client : " + message);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

// Client TCP:

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        Socket socket = null;

        try {
            // Créer un socket client TCP pour se connecter au serveur sur le port 9876
            socket = new Socket("localhost", 9876);

            // Obtenir le flux de sortie du client
            OutputStream outputStream = socket.getOutputStream();

            // Envoyer un message au serveur
            String message = "Hello, serveur TCP!";
            byte[] data = message.getBytes();
            outputStream.write(data);
            System.out.println("Message envoyé au serveur : " + message);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
