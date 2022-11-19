package net.yorksolutions.emilymilldrumcapstonebe.response;

import net.yorksolutions.emilymilldrumcapstonebe.answer.Answer;
import net.yorksolutions.emilymilldrumcapstonebe.process.Processes;
import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;

import javax.persistence.*;
import java.util.List;

@Entity
//these are the responses
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    Processes processes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name="response_answer")
    List<Answer> answer;

    public Response(){

    }

    public Response(Processes processes, List<Answer> answer) {
        setProcesses(processes);
        setAnswer(answer);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Processes getProcesses() {
        return processes;
    }

    public void setProcesses(Processes processes) {
        this.processes = processes;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }
}
