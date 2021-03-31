package app.config;

import app.comands.DoComands;
import app.component.Downloader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("app")
public class AppConfig {

    @Bean
    public DoComands doComands(){
        DoComands doComands = new DoComands();
        doComands.setDownloader(downloader());
        return doComands;
    }

    @Bean Downloader downloader(){
        return new Downloader();
    }

}
