package app.comands;

import app.config.AppConfig;
import app.dao.database.WorkWithDataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ClearData implements Command {
    private static final Logger log = LogManager.getLogger(ClearData.class);

    @Override
    public void doSomeCommand() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        WorkWithDataBase workWithDataBase = ctx.getBean("workWithDataBase", WorkWithDataBase.class);
        log.info("Очистка базы!");
        workWithDataBase.clear();
    }
}
