package app.comands;

import org.springframework.stereotype.Component;

@Component
public class Help implements Command{
    @Override
    public void doSomeCommand() {
        Command command = new CommandInfo();
        command.doSomeCommand();
    }
}
