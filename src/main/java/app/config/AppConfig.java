package app.config;

import app.comands.*;
import app.component.Downloader;
import app.dao.database.WorkWithDataBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;

@Configuration
@ComponentScan("app")
public class AppConfig {

    @Bean public Downloader downloader(){
        return new Downloader();
    }

    @Bean
    public WorkWithDataBase workWithDataBase(){
        return new WorkWithDataBase();
    }

    @Bean
    public Command clearData(){
        return new ClearData();
    }

    @Bean
    public Command delete(){
        return new Delete();
    }
    @Bean
    public Command exit(){
        return new Exit();
    }
    @Bean
    public Command help(){
        return new Help();
    }
    @Bean
    public Command receiveData(){
        return new ReceiveData();
    }
    @Bean
    public Command search(){
        return new Search();
    }
    @Bean
    public Command showDataFromDataBase(){
        return new ShowDataFromBase();
    }

    @Bean
    public Command commandInfo(){
        return new CommandInfo();
    }

}
