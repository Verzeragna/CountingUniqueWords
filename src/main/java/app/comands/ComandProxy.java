package app.comands;

import app.util.ConsoleHelper;
import org.springframework.stereotype.Component;

@Component
public class ComandProxy {

    public void doComand(String comand, IDoComands comands){
        switch (comand){
            case "search":
                comands.search();
                break;
            case "show data":
                comands.showDataFromBase();
                break;
            case "get data":
                comands.getData();
                break;
            case "clear":
                comands.clearDataBase();
                break;
            case "delete":
                comands.delete();
                break;
            default:
                ConsoleHelper.showMessage("Команда не распознана!");
        }
    }
}
