package app.comands;

import app.component.Downloader;
import app.dao.database.IWorkWithDataBase;
import app.dao.model.Statistic;
import app.util.ConsoleHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class DoComands implements IDoComands {

    private Downloader downloader;

    @Autowired
    IWorkWithDataBase workWithDataBase;

    private static final Logger log = LogManager.getLogger(DoComands.class);

    @Override
    public void search(){
        ConsoleHelper.showMessage("Введите URL страницы, пример (https://www.simbirsoft.com):");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
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
    }

    @Override
    public void showDataFromBase() {
        log.info("Запрос данных из базы!");
        List<Statistic> dataList = workWithDataBase.getAllData();
        for (Statistic el : dataList) {
            ConsoleHelper.showMessage(el.toString());
        }
        if (dataList.size()==0){
            ConsoleHelper.showMessage("Записи в базе данных отсутствуют!");
        }
        ConsoleHelper.showMessage("Конец.................................................");
    }

    @Override
    public void clearDataBase() {
        log.info("Очистка базы!");
        workWithDataBase.clear();
    }

    @Override
    public void delete() {
        ConsoleHelper.showMessage("Введите URL (либо часть URL) для удаления:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            log.error("Ошибка чтения с консоли: " + e);
        }
        if (line.length()>0) {
            log.info("Удаление из базы по строке " + line);
            workWithDataBase.delete(line);
        }else{
            ConsoleHelper.showMessage("Для удаления необходимо ввести хотябы один символ!");
        }
    }

    @Override
    public void getData() {
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

    @Override
    public void help() {
        showAvaliableComands();
    }

    @Override
    public void exit() {
        workWithDataBase.disconnect();
    }

    @Override
    public void showAvaliableComands() {
        ConsoleHelper.showMessage("Список команд:");
        Map<String, String> comands = getComands();
        for (Map.Entry<String,String> el : comands.entrySet()){
            ConsoleHelper.showMessage(el.getKey() + el.getValue());
        }
    }

    private Map<String,String> getComands() {
        Map<String,String> comandList = new LinkedHashMap<>();
        comandList.put("search"," - осуществить поиск повторяющихся слов;");
        comandList.put("show data"," - получить все записи базы данных;");
        comandList.put("get data"," - получить записи по URL;");
        comandList.put("clear"," - очистить базу данных;");
        comandList.put("delete"," - удалить найденные слова из базы данных с определенным URL;");
        comandList.put("help"," - показать доступные команды;");
        comandList.put("exit"," - выход из приложения.");
        return comandList;
    }

    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }
}
