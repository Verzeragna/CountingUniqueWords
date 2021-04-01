package app.dao.database;

import app.dao.model.Statistic;

import java.util.List;

public interface IWorkWithDataBase {
    void save(Statistic statistics);
    void delete(String query);
    void clear();
    void disconnect();
    List<Statistic> getAllData();
    List<Statistic> getData(String query);
}
