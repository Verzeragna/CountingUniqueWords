package app.comands;

import app.component.Downloader;
import app.config.AppConfig;
import app.util.ConsoleHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Search implements Command {

    private static final Logger log = LogManager.getLogger(Search.class);

    @Override
    public void doSomeCommand() {
        ConsoleHelper.showMessage("Введите URL страницы, пример (https://www.simbirsoft.com):");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Downloader downloader = ctx.getBean("downloader", Downloader.class);
        try {
            line = reader.readLine();
        } catch (IOException e) {
            log.error("Ошибка чтения с консоли: " + e);
        }
        try {
            log.info("Запущен процесс поиска на странице " + line);
            ConsoleHelper.showMessage("Ждите...");
            downloader.downloadPage(line);
            log.info("Поиск завершен успешно!");
            ConsoleHelper.showMessage("Поиск завершен!");
            ConsoleHelper.showMessage("Воспользуйтесь командой 'show data' для просмотра результата из базы данных.");
        } catch (Exception e) {
            log.error("Поиск завершился с ошибкой!");
            ConsoleHelper.showMessage("Поиск завершился с ошибкой!");
            log.error("Ошибка чтения HTML страницы: " + e);
        }
        ctx.close();
    }
}
