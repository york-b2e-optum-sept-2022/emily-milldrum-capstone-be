package net.yorksolutions.emilymilldrumcapstonebe.process;
import com.sun.istack.NotNull;
import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
//these are the surveys
public class Processes {

    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private Boolean discontinued;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@JoinTable(name="processes_stage")
    @JoinColumn(name="processes_id")
    private List<Stage> stage;


    public Processes(){
        setId(0);
        setTitle("Title");
        setStage(null);
    }
    public Processes(String title, Boolean discontinued){
        setTitle(title);
        setDiscontinued(discontinued);
    }

    public Processes(String title, Boolean discontinued, List<Stage> stage) {
        setTitle(title);
        setDiscontinued(discontinued);
        setStage(stage);
    }

    public void removeStage(Stage incStage){
        stage.remove(incStage);
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

    public List<Stage> getStage() {
        return stage;
    }

    public void setStage(List<Stage> stage) {
        this.stage = stage;
    }

}
