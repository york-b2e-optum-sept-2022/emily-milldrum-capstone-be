package net.yorksolutions.emilymilldrumcapstonebe.stageOptions;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@RequestMapping("/api/stageOptions")
@CrossOrigin
//these are the question choices
public class StageOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String type;

    public StageOptions(){

    }
}
