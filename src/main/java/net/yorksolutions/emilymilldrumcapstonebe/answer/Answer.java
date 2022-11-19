package net.yorksolutions.emilymilldrumcapstonebe.answer;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;

import javax.persistence.*;

@Entity
public class Answer {
    //variables
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToOne
    private Stage stage;
    private String answer;

    public Answer() {

    }
    public Answer(String answer) {
       // setStage(stage);
        setAnswer(answer);
    }
    public Answer(Stage stage, String answer) {
        setStage(stage);
        setAnswer(answer);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
