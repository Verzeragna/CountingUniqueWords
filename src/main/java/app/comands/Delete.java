package app.comands;

import app.config.AppConfig;
import app.dao.database.WorkWithDataBase;
import app.util.ConsoleHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Delete implements Command {
    private static final Logger log = LogManager.getLogger(Delete.class);

    @Override
    public void doSomeCommand() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        WorkWithDataBase workWithDataBase = ctx.getBean("workWithDataBase", WorkWithDataBase.class);
        ConsoleHelper.showMessage("Введите URL (либо часть URL) для удаления:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            log.error("Ошибка чтения с консоли: " + e);
        }
        if (line.length() > 0) {
            log.info("Удаление из базы по строке " + line);
            workWithDataBase.delete(line);
        } else {
            ConsoleHelper.showMessage("Для удаления необходимо ввести хотябы один символ!");
        }
    }
}
