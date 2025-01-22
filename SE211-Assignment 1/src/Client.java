import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Port must be specified");
            System.exit(-1);
        }
        int port = Integer.parseInt(args[0]);
        PrintWriter out = null;
        Socket socket = null;
        try {
            socket = new Socket("localhost", port);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Could not connect to server");
            System.exit(-1);
        }
        Scanner in = new Scanner(System.in);
        System.out.print("Enter command: ");
        String userInput = in.nextLine();
        while (!userInput.equals("quit")) {
            out.println(userInput);
            System.out.print("Enter command: ");
            userInput = in.nextLine();
        }
        out.println("quit");
        in.close();
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
