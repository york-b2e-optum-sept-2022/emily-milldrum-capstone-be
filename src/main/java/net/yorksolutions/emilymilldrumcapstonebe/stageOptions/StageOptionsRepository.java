package net.yorksolutions.emilymilldrumcapstonebe.stageOptions;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageOptionsRepository extends CrudRepository <StageOptions, Integer>{
}
