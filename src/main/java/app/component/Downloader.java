package app.component;

import app.dao.database.IWorkWithDataBase;
import app.dao.model.Statistic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Component
public class Downloader {

    @Autowired
    IWorkWithDataBase workWithDataBase;

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
        Map<String, Integer> wordMap = countWords(list);
        log.info("На странице найдено " + wordMap.size() + " различных слов.");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            Statistic stat = new Statistic(url, entry.getKey(), entry.getValue());
            workWithDataBase.save(stat);
        }
    }

    private Map<String, Integer> countWords(List<String> list) {

        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            int count = 0;
            for (String el : list) {
                if (element.equals(el)) count++;
            }
            result.put(element, count);
        }
        return result;
    }

    public Downloader() {
    }

    public void setWorkWithDataBase(IWorkWithDataBase workWithDataBase) {
        this.workWithDataBase = workWithDataBase;
    }
}
