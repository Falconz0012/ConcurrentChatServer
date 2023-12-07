package oorg.codeforall.iorns.commands;

public enum CommandEnum {
    WHISPER("/whisper",new CommandWhisper()),
    QUIT("/quit", new CommandQuit()),
    DEFAULT(" ", new CommandDefault());


    CommandEnum(String commandName, CommandInterface command) {
        this.commandName = commandName;
        this.command = command;
    }

    private String commandName;
    private CommandInterface command;

   static public CommandInterface sendMessageToClient(String command){
        CommandInterface finalComand = new CommandDefault();

        for(CommandEnum commandName : values()){
            if(commandName.commandName.equals( command)){
                finalComand = commandName.command;
            }
        }
        return finalComand;
    }
}
