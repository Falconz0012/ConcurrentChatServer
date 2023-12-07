package oorg.codeforall.iorns.commands;

import oorg.codeforall.iorns.ChatServer;

import java.io.IOException;
import java.net.Socket;

public class CommandQuit implements CommandInterface {
    @Override
    public void commandAction(String message, Socket sender) {
        try {
            sender.close();
            for(String client : ChatServer.clientList.keySet()){
                if(ChatServer.clientList.get(client).equals(sender)){
                    ChatServer.clientList.remove(client);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
