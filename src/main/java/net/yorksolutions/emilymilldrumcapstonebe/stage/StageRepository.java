package net.yorksolutions.emilymilldrumcapstonebe.stage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends CrudRepository<Stage, Integer>{
    //List<Stage> findStagesByProcessId(Integer processId);
}
