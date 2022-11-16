package net.yorksolutions.emilymilldrumcapstonebe.process;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageDTO;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageRepository;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProcessService {

    ProcessRepository processRepository;
    StageRepository stageRepository;
    StageService stageService;

    public ProcessService(ProcessRepository processRepository, StageService stageService) {
        this.processRepository = processRepository;
    }

    public Process create(ProcessDTO requestDTO) {
        try {
            //TODO FIGURE OUT HOW TO SAVE STAGES TO PROCESSES
            Process process = new Process(requestDTO.title, requestDTO.discontinued);
            processRepository.save(process);
            for (Stage stage : requestDTO.stage){
                Stage newStage = stageService.create(stage);
                process.getStage().add(newStage);
                processRepository.save(process);
            }
            return processRepository.save(process);

            return this.processRepository.save(
                    new Process(requestDTO.title, requestDTO.discontinued
                          //  requestDTO.stage
                    ));
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
//
//        for(final Stage stage : requestDTO.stage){
//            this.stageRepository.save(stage);
//        }
        Process process = processOpt.get();
        process.setTitle(requestDTO.title);
        process.setDiscontinued(requestDTO.discontinued);
//        process.setStage(requestDTO.stage);
//
//        //TODO FIX this here for BE testing
//        process.setStage(requestDTO.stage);

        return this.processRepository.save(process);
    }

    public Process createAll(ProcessDTO requestDTO) {
        return null;
    }
}
