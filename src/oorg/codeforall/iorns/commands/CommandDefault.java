package oorg.codeforall.iorns.commands;

import oorg.codeforall.iorns.ChatServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CommandDefault implements CommandInterface {
    @Override
    public void commandAction(String message, Socket sender) {
        String clientName = "";
            try {
                for (String name : ChatServer.clientList.keySet()) {
                    if(ChatServer.clientList.get(name) == sender){
                        clientName = name;
                    }
                }
                for (Socket socket : ChatServer.clientList.values()) {
                    if (sender == socket) {
                        continue;

                    }
                    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    output.write(clientName + ": " + message + "\n");
                    output.flush();
                }

        } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
