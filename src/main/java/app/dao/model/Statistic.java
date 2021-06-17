package app.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
public class Statistic implements Comparable<Statistic>{

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Column(name = "url")
    private String url;

    @Getter
    @Setter
    @Column(name = "word")
    private String word;

    @Getter
    @Setter
    @Column(name = "count_word")
    private long countWord;

    public Statistic() {
    }

    public Statistic(String uri, String word, long countWord) {
        this.url = uri;
        this.word = word;
        this.countWord = countWord;
    }

    @Override
    public String toString() {
        return "URL= " + url + "; " + word + " = " + countWord + ";";
    }

    @Override
    public int compareTo(Statistic anotherObj) {
        return (int) (this.countWord - anotherObj.getCountWord());
    }
}
