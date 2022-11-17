package net.yorksolutions.emilymilldrumcapstonebe.process;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends CrudRepository<Processes, Integer> {
}
