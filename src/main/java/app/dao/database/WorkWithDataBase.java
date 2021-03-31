package app.dao.database;

import app.dao.model.Statistic;
import app.util.ConsoleHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkWithDataBase implements IWorkWithDataBase {

    private static final Logger log = LogManager.getLogger(WorkWithDataBase.class);

    @Override
    public void save(Statistic statistics) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Statistic.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(statistics);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Сохранения данных данных: " + e);
        }finally {
            factory.close();
        }

    }

    @Override
    public void delete(String searchLine)  {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Statistic.class)
                .buildSessionFactory();
        String temp = "%"+searchLine+"%";
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from Statistic where url like :url").setParameter("url",temp).executeUpdate();
            session.getTransaction().commit();
            ConsoleHelper.showMessage("Удаление завершено!");
        } catch (Exception e) {
            log.error("Ошибка удаления данных: " + e);
        }finally {
            factory.close();
        }
    }

    @Override
    public void clear() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Statistic.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from Statistic").executeUpdate();
            session.getTransaction().commit();
            ConsoleHelper.showMessage("Очистка завершена!");
        } catch (Exception e) {
            log.error("Ошибка очистки базы данных: " + e);
        }finally {
            factory.close();
        }
    }

    @Override
    public List<Statistic> getAllData() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Statistic.class)
                .buildSessionFactory();
        List<Statistic> dataList = new ArrayList<>();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            dataList = session.createQuery("from Statistic").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка получения данных из базы: " + e);
        }finally {
            factory.close();
        }
        return dataList;
    }

    @Override
    public List<Statistic> getData(String searchLine) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Statistic.class)
                .buildSessionFactory();
        String temp = "%"+searchLine+"%";
        List<Statistic> statisticList = new ArrayList<>();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            statisticList = session.createQuery("from Statistic where url like :url").setParameter("url",temp).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Ошибка получения данных из базы: " + e);
        }finally {
            factory.close();
        }
        return statisticList;
    }
}
