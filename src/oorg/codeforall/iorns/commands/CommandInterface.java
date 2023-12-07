package oorg.codeforall.iorns.commands;

import java.net.Socket;

public interface CommandInterface {
    public void commandAction(String message, Socket sender);
}
