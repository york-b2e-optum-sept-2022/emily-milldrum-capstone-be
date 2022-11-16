package net.yorksolutions.emilymilldrumcapstonebe.stage;

import net.yorksolutions.emilymilldrumcapstonebe.stageOptions.StageOptions;

import javax.persistence.*;
import java.util.Set;

@Entity
//these are the questions
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    //private Integer processId;
    private String question;
    private Integer stageOrder;
    private String type;

    @OneToMany
    //@JoinTable(name="stage_stageoptions")
    private Set<StageOptions> stageOptions;
    public Stage(){

    }

    public Stage(
            //Integer processId,
            String question, Integer stageOrder, String type) {
        //setProcessId(processId);
        setQuestion(question);
        setStageOrder(stageOrder);
        setType(type);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getProcessId() {
//        return processId;
//    }
//
//    public void setProcessId(Integer processId) {
//        this.processId = processId;
//    }

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

    public Set<StageOptions> getStageOptions() {
        return stageOptions;
    }

    public void setStageOptions(Set<StageOptions> stageOptions) {
        this.stageOptions = stageOptions;
    }
}