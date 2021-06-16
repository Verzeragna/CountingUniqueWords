package app.component;

import app.dao.database.WorkWithDataBase;
import app.dao.model.Statistic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.select.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Downloader {

    @Autowired
    WorkWithDataBase workWithDataBase;

    private static final Logger log = LogManager.getLogger(Downloader.class);

    public void downloadPage(String url) throws IOException {

        String pattern = "(?u)[^\\pL ]";
        StringBuilder stringBuilder = new StringBuilder();
        
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        in.close();

        String content = Jsoup.parse(stringBuilder.toString()).text();

        content = content.replaceAll("[0-9]+", "");
        String[] text = content.replaceAll(pattern, " ").toLowerCase().split("\\s+");
        List<String> list = new ArrayList<>(Arrays.asList(text));
        Map<String, Long> wordMap = countWords(list);
        log.info("На странице найдено " + wordMap.size() + " различных слов.");
        for (Map.Entry<String, Long> entry : wordMap.entrySet()) {
            Statistic stat = new Statistic(url, entry.getKey(), entry.getValue());
            workWithDataBase.save(stat);
        }
    }

    private Map<String, Long> countWords(List<String> list) {

        Map<String, Long> result = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            long count = list.stream().filter(l->l.equals(element)).count();
            result.put(element, count);
        }
        return result;
    }

    public Downloader() {
    }

    public void setWorkWithDataBase(WorkWithDataBase workWithDataBase) {
        this.workWithDataBase = workWithDataBase;
    }
}
