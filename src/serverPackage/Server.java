package act21.serverPackage;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket ss = new ServerSocket(1234)) {
            System.out.println("Serveur en attente sur le port 1234...");
            try (Socket s = ss.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                 PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
                System.out.println("Client connecté : " + s.getRemoteSocketAddress());
                String a;
                while ((a = in.readLine()) != null) {      // 1er opérande
                    String op = in.readLine();             // opérateur
                    String b = in.readLine();              // 2e opérande
                    double x = Double.parseDouble(a), y = Double.parseDouble(b);
                    System.out.println("Reçu : " + x + " " + op + " " + y);
                    String res;
                    switch (op) {
                        case "+": res = String.valueOf(x + y); break;
                        case "-": res = String.valueOf(x - y); break;
                        case "*": res = String.valueOf(x * y); break;
                        case "/": res = (y == 0) ? "Erreur : division par zéro" : String.valueOf(x / y); break;
                        default:  res = "Erreur : opérateur inconnu";
                    }
                    out.println(res);                      // résultat
                }
            }
        }
    }
}
