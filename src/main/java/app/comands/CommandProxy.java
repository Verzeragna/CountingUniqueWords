package app.comands;

import app.config.AppConfig;
import app.util.ConsoleHelper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CommandProxy {

    public void doCommand(String command){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        switch (command){
            case "search":
                Command search = ctx.getBean("search",Search.class);
                search.doSomeCommand();
                break;
            case "show data":
                Command showDataFromBase = ctx.getBean("showDataFromBase",ShowDataFromBase.class);
                showDataFromBase.doSomeCommand();
                break;
            case "get data":
                Command receiveData = ctx.getBean("receiveData",ReceiveData.class);
                receiveData.doSomeCommand();
                break;
            case "clear":
                Command clearData = ctx.getBean("clearData",ClearData.class);
                clearData.doSomeCommand();
                break;
            case "delete":
                Command delete = ctx.getBean("delete",Delete.class);
                delete .doSomeCommand();
                break;
            case "help":
                Command help = ctx.getBean("help",Help.class);
                help.doSomeCommand();
                break;
            case "exit":
                Command exit = ctx.getBean("exit",Exit.class);
                exit.doSomeCommand();
                break;
            default:
                ConsoleHelper.showMessage("Команда не распознана!");
        }
    }
}
