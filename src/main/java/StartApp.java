import app.comands.ComandProxy;
import app.comands.DoComands;
import app.comands.IDoComands;
import app.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartApp {

    private static final Logger log = LogManager.getLogger(StartApp.class);

    public static void main(String[] args){

        StartApp app = new StartApp();
        app.run();

    }

    private void run(){

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        IDoComands comands = ctx.getBean("doComands", DoComands.class);
        ComandProxy comandProxy = ctx.getBean("comandProxy", ComandProxy.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        comands.showAvaliableComands();

        while (!line.equals("exit")) {
            try {
                line = reader.readLine();
            }catch (IOException ex){
                log.error("Ошибка чтения с консоли: " + ex);
            }
            if (!line.equals("exit")) {
                comandProxy.doComand(line, comands);
            }
        }

        ctx.close();

    }
}

