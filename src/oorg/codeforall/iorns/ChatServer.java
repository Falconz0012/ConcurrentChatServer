package oorg.codeforall.iorns;

import oorg.codeforall.iorns.commands.Command;
import oorg.codeforall.iorns.commands.CommandInterface;
import oorg.codeforall.iorns.commands.CommandEnum;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    public ServerSocket serverSocket;

    public static ConcurrentHashMap<String, Socket> clientList = new ConcurrentHashMap<>();

    ExecutorService executor = Executors.newCachedThreadPool();


    int hostPort = 9090;

    public void init() {
        try {
            serverSocket = new ServerSocket(hostPort);


            while (serverSocket.isBound()) {


                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Aceitou " + clientSocket);

                Dispatcher dispatcher = new Dispatcher(clientSocket);
                executor.submit(dispatcher);

            }


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public class Dispatcher implements Runnable {
        Dispatcher(Socket socket) {
            this.clientSocket = socket;
        }

        Command command;

        Socket clientSocket;

        @Override
        public void run() {
            try {

                String chooseName = "Please enter your id: \n";
                BufferedWriter sendQuestion = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                sendQuestion.write(chooseName);
                sendQuestion.flush();

                BufferedReader readName = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String clientName = readName.readLine();
                clientList.put(clientName, clientSocket);
                System.out.println(clientList.size());

                while (clientSocket.isBound()) {
                    String message = "";
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    message = input.readLine();

                    this.command = new Command(CommandEnum.sendMessageToClient(message.split(" ")[0]));
                    System.out.println(message.split(" ")[0]);
                    command.sendMessage(message, clientSocket);


                }


            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        }
    }
}