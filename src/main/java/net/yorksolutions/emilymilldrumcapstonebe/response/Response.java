package net.yorksolutions.emilymilldrumcapstonebe.response;

import net.yorksolutions.emilymilldrumcapstonebe.answer.Answer;
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
    Stage stage;

    @OneToMany
    List<Answer> answer;
}
