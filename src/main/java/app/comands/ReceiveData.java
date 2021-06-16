package app.comands;

import app.config.AppConfig;
import app.dao.database.WorkWithDataBase;
import app.dao.model.Statistic;
import app.util.ConsoleHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class ReceiveData implements Command{
    private static final Logger log = LogManager.getLogger(ReceiveData.class);
    @Override
    public void doSomeCommand() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        WorkWithDataBase workWithDataBase = ctx.getBean("workWithDataBase",WorkWithDataBase.class);
        ConsoleHelper.showMessage("Введите URL (либо часть URL) для поиска:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            log.error("Ошибка чтения с консоли: " + e);
        }
        if (line.length()>0) {
            log.info("Поиск по строке " + line);
            List<Statistic> statisticList = workWithDataBase.getData(line);
            for (Statistic el : statisticList) {
                ConsoleHelper.showMessage(el.toString());
            }
            if (statisticList.size()==0){
                ConsoleHelper.showMessage("Записи в базе данных отсутствуют!");
            }
            ConsoleHelper.showMessage("Конец.................................................");
        }else{
            ConsoleHelper.showMessage("Для поиска необходимо ввести хотябы один символ!");
        }
    }
}
