package app;

import app.comands.Command;
import app.comands.CommandInfo;
import app.comands.CommandProxy;
import app.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartApp {

    private static final Logger log = LogManager.getLogger(StartApp.class);

    public void run(){

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        CommandProxy commandProxy = ctx.getBean("commandProxy", CommandProxy.class);
        Command commandInfo = ctx.getBean("commandInfo", CommandInfo.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        commandInfo.doSomeCommand();

        while (!line.equals("exit")) {
            try {
                line = reader.readLine();
                commandProxy.doCommand(line);
            }catch (IOException ex){
                log.error("Ошибка чтения с консоли: " + ex);
            }
        }
        ctx.close();

    }
}
