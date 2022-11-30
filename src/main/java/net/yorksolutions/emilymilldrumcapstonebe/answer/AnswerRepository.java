package net.yorksolutions.emilymilldrumcapstonebe.answer;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    Iterable<Answer> findAllByStage(Stage stage);
}
