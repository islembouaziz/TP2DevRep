package act21.clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    // Validation : nombre  opérateur  nombre (ex: 55 * 25). Rejette "25 ** 55" et "abc + 3".
    private static final String NB = "-?\\d+(\\.\\d+)?";
    private static final String REGEX = "(" + NB + ")\\s*([+\\-*/])\\s*(" + NB + ")";

    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket("localhost", 1234);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(s.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("Opération (ex: 55 * 25) ou 'quit' : ");
                String ligne = sc.nextLine().trim();
                if (ligne.equalsIgnoreCase("quit")) break;
                java.util.regex.Matcher m = java.util.regex.Pattern.compile(REGEX).matcher(ligne);
                if (!m.matches()) { System.out.println("Syntaxe invalide."); continue; }
                out.println(m.group(1));   // opérande 1
                out.println(m.group(3));   // opérateur
                out.println(m.group(4));   // opérande 2
                System.out.println("Résultat = " + in.readLine());
            }
        }
    }
}
