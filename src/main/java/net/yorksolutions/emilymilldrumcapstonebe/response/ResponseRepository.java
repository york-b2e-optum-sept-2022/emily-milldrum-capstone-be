package net.yorksolutions.emilymilldrumcapstonebe.response;

import net.yorksolutions.emilymilldrumcapstonebe.process.Processes;
import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseRepository extends CrudRepository<Response, Integer> {
    Iterable<Response> findAllByProcesses_Id(Integer id);
}
