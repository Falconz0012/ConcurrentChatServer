package oorg.codeforall.iorns.commands;

import java.net.Socket;

public class Command {
    public Command(CommandInterface command) {
        this.command = command;
    }

    CommandInterface command;
   public void  sendMessage(String message, Socket sender){
       command.commandAction(message,sender);

    }
}
