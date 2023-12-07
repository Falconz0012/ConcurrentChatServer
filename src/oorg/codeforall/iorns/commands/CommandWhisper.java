package oorg.codeforall.iorns.commands;

import oorg.codeforall.iorns.ChatServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CommandWhisper implements CommandInterface {
    @Override
    public void commandAction(String message, Socket sender) {
        String messageReceiver = message.split(" ")[1];
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(ChatServer.clientList.get(messageReceiver).getOutputStream()));
            bufferedWriter.write(message+"\n");
            bufferedWriter.flush();
            System.out.println("Sent Whisper to "+ messageReceiver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
