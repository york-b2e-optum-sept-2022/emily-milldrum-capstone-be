package net.yorksolutions.emilymilldrumcapstonebe.stage;
import net.yorksolutions.emilymilldrumcapstonebe.stageOptions.StageOptions;

import javax.persistence.*;
import java.util.List;

@Entity
//these are the questions
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private Processes processes;
    private String question;
    private Integer stageOrder;
    private String type;

    @OneToMany(
           // mappedBy = "processes_id",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="stage_id")
    private List<StageOptions> stageOptions;

    public Stage() {

    }
    public Stage(String question, Integer stageOrder, String type) {
        setQuestion(question);
        setStageOrder(stageOrder);
        setType(type);
    }
    public Stage(String question, Integer stageOrder, String type, List<StageOptions> stageOptions) {
        setQuestion(question);
        setStageOrder(stageOrder);
        setType(type);
        setStageOptions(stageOptions);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getStageOrder() {
        return stageOrder;
    }

    public void setStageOrder(Integer stageOrder) {
        this.stageOrder = stageOrder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StageOptions> getStageOptions() {
        return stageOptions;
    }

    public void setStageOptions(List<StageOptions> stageOptions) {
        this.stageOptions = stageOptions;
    }
}