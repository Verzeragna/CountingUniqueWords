package app.comands;

import app.config.AppConfig;
import app.dao.database.WorkWithDataBase;
import app.dao.model.Statistic;
import app.util.ConsoleHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ShowDataFromBase implements Command{
    private static final Logger log = LogManager.getLogger(ShowDataFromBase.class);
    @Override
    public void doSomeCommand() {
        log.info("Запрос данных из базы!");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        WorkWithDataBase workWithDataBase = ctx.getBean("workWithDataBase",WorkWithDataBase.class);
        List<Statistic> dataList = workWithDataBase.getAllData();
        for (Statistic el : dataList) {
            ConsoleHelper.showMessage(el.toString());
        }
        if (dataList.size()==0){
            ConsoleHelper.showMessage("Записи в базе данных отсутствуют!");
        }
        ConsoleHelper.showMessage("Конец.................................................");
    }
}
