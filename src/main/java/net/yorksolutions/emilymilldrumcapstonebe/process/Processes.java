package net.yorksolutions.emilymilldrumcapstonebe.process;
import net.yorksolutions.emilymilldrumcapstonebe.response.Response;
import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;


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
    @JoinColumn(name="processes_id")
    private List<Stage> stage;

    // saved in case wanted to join column responses to process
    //    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //    @JoinColumn(name="processes_id")
    //    List<Response> responses;

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
    public Processes(String title, Boolean discontinued, List<Stage> stage, List<Response> responses) {
        setTitle(title);
        setDiscontinued(discontinued);
        setStage(stage);
    }

    public void removeStage(Stage incStage){
        stage.remove(incStage);
    }
    public void addStage(Stage incStage){
        List<Stage> list = getStage();
        list.add(incStage);
        setStage(list);
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
