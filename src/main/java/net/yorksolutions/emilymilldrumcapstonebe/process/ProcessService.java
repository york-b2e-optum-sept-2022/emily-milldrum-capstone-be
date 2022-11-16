package net.yorksolutions.emilymilldrumcapstonebe.process;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProcessService {

    ProcessRepository processRepository;
    StageRepository stageRepository;

    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public Process create(ProcessDTO requestDTO) {
        try {

            Stage newStage = new Stage();
            this.stageRepository.save(newStage);

            return this.processRepository.save(
                    new Process(requestDTO.title, requestDTO.discontinued,
                            requestDTO.stage));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Process> getAllProcesses() {
        return processRepository.findAll();
    }

    public void delete(Integer productId) {
        this.processRepository.deleteById(productId);
    }

    public Process update(ProcessUpdateDTO requestDTO) {
        Optional<Process> processOpt = this.processRepository.findById(requestDTO.id);
        if(processOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Process process = processOpt.get();
        process.setTitle(requestDTO.title);
        process.setDiscontinued(requestDTO.discontinued);

        //TODO FIX this here for BE testing
        process.setStage(requestDTO.stage);

        return this.processRepository.save(process);
    }

    public Process createAll(ProcessDTO requestDTO) {
        return null;
    }
}
