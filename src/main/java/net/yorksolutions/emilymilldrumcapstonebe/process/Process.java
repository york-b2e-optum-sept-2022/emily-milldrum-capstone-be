package net.yorksolutions.emilymilldrumcapstonebe.process;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;

import javax.persistence.*;
import java.util.Set;

@Entity
//these are the surveys
public class Process {

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;

    @OneToMany
    private Set<Stage> stage;


    public Process(){
        setId(0);
        setTitle("Title");
        setStage(null);
    }

    public Process(String title, Set<Stage> stage){
        setTitle(title);
        setStage(stage);
    }

    //setters & getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Stage> getStage() {
        return stage;
    }

    public void setStage(Set<Stage> stage) {
        this.stage = stage;
    }

}
