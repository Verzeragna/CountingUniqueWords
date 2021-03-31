import app.component.Downloader;
import app.dao.database.IWorkWithDataBase;
import app.dao.database.WorkWithDataBase;
import app.dao.model.Statistic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

public class TestApp {

    private Downloader downloader;
    private IWorkWithDataBase workWithDataBase;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp(){
        workWithDataBase = new WorkWithDataBase();
        downloader = new Downloader();
        downloader.setWorkWithDataBase(workWithDataBase);
    }

    @Test
    public void testDownloadAndSave(){
        try {
            downloader.downloadPage("https://www.simbirsoft.com/");
        }catch (Exception e){
            System.out.println(e);
        }
        List<Statistic> statisticList = workWithDataBase.getData("simbirsoft");
        Assert.assertNotEquals(0,statisticList.size());
    }

    @Test
    public void testSaveStatistics(){
        Statistic statistic = new Statistic("www.mail.ru","привет",6);
        workWithDataBase.save(statistic);
        List<Statistic> statisticList = workWithDataBase.getData("mail");
        Assert.assertEquals(1,statisticList.size());
    }

    @Test
    public void testGetAllDataFromBase(){
        Statistic statistic = new Statistic("www.mail.ru","привет",6);
        workWithDataBase.save(statistic);
        List<Statistic> statisticList = workWithDataBase.getAllData();
        Assert.assertNotEquals(0,statisticList.size());
    }

    @Test
    public void testDeleteFromBase(){
        Statistic statistic = new Statistic("www.mail.ru","привет",6);
        workWithDataBase.save(statistic);
        workWithDataBase.delete("mail");
        List<Statistic> statisticList = workWithDataBase.getData("mail");
        Assert.assertEquals(0,statisticList.size());
    }

    @Test
    public void testClear(){
        workWithDataBase.clear();
        List<Statistic> statisticList = workWithDataBase.getAllData();
        Assert.assertEquals(0,statisticList.size());
    }

    @Test
    public void testGetData(){
        Statistic statistic = new Statistic("www.mail.ru","привет",6);
        workWithDataBase.save(statistic);
        List<Statistic> statisticList = workWithDataBase.getData("mail");
        Assert.assertNotEquals(0,statisticList.size());
    }
}

