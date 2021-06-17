package app.comands;

import app.util.ConsoleHelper;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandInfo implements Command {
    @Override
    public void doSomeCommand() {
        ConsoleHelper.showMessage("Список команд:");
        Map<String, String> comands = getComands();
        for (Map.Entry<String, String> el : comands.entrySet()) {
            ConsoleHelper.showMessage(el.getKey() + el.getValue());
        }
    }

    private Map<String, String> getComands() {
        Map<String, String> comandList = new LinkedHashMap<>();
        comandList.put("search", " - осуществить поиск повторяющихся слов;");
        comandList.put("show data", " - получить все записи базы данных;");
        comandList.put("get data", " - получить записи по URL;");
        comandList.put("clear", " - очистить базу данных;");
        comandList.put("delete", " - удалить найденные слова из базы данных с определенным URL;");
        comandList.put("help", " - показать доступные команды;");
        comandList.put("exit", " - выход из приложения.");
        return comandList;
    }
}
