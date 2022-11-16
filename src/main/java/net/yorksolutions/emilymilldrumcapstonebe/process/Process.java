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
    private Boolean discontinued;

    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="process_stage",
//            joinColumns=
//            @JoinColumn(name="process_id", referencedColumnName="id"),
//            inverseJoinColumns=
//            @JoinColumn(name="stage_id", referencedColumnName="id"))
//    @JoinTable(name="process_stage")
    private Set<Stage> stage;


    public Process(){
        setId(0);
        setTitle("Title");
        //setStage(null);
    }
//    public Process(Set<Stage> stage){
//        setStage(stage);
//    }
    public Process(String title, Boolean discontinued
                  // Set<Stage> stage
    ){
        setTitle(title);
        setDiscontinued(discontinued);
       // setStage(stage);
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

    public Boolean getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Boolean discontinued) {
        this.discontinued = discontinued;
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
