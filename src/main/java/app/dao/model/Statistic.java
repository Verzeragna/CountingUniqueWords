package app.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "word")
    private String word;

    @Column(name = "count_word")
    private int countWord;

    public Statistic() {
    }

    public Statistic(String uri, String word, int countWord) {
        this.url = uri;
        this.word = word;
        this.countWord = countWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCountWord() {
        return countWord;
    }

    public void setCountWord(int countWord) {
        this.countWord = countWord;
    }

    @Override
    public String toString() {
        return "URL= " + url + "; " + word + " = " + countWord + ";";
    }
}
