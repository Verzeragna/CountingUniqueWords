package app.comands;

import app.config.AppConfig;
import app.dao.database.WorkWithDataBase;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Exit implements Command{
    @Override
    public void doSomeCommand() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        WorkWithDataBase workWithDataBase = ctx.getBean("workWithDataBase",WorkWithDataBase.class);
        workWithDataBase.disconnect();
    }
}
