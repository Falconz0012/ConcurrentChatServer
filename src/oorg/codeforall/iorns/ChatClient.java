package oorg.codeforall.iorns;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatClient {


    Socket mySocket;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public void init() {

        {
            try {
                mySocket = new Socket("localhost", 9090);

                BufferedReader serverInput = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
                String serverMessage = serverInput.readLine();
                System.out.println(serverMessage);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(mySocket.getOutputStream()));
                executor.submit(() -> {
                    try {
                        BufferedReader serverInput2 = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
                        String message = "";

                        while ((message = serverInput2.readLine()) != null ) {

                            System.out.println(message);


                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


                while (mySocket.isBound()) {

                    BufferedReader myInput = new BufferedReader(new InputStreamReader(System.in));
                    String message = myInput.readLine();

                    System.out.println(message);


                    bufferedWriter.write(message + "\n");
                    bufferedWriter.flush();
                }


            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        }
    }
}






