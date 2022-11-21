package net.yorksolutions.emilymilldrumcapstonebe.stageOptions;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

@Entity
@RequestMapping("/api/stageOptions")
@CrossOrigin
//these are the question choices
public class StageOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String option;

    public StageOptions(){

    }
    //add a stage:choice to an existing stage
    public StageOptions(String option){
        setOption(option);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }


}
