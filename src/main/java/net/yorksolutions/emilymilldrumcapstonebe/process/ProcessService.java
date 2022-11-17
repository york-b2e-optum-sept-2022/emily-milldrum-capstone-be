package net.yorksolutions.emilymilldrumcapstonebe.process;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageDTO;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageRepository;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessService {

    ProcessRepository processRepository;
    StageRepository stageRepository;

    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public Processes create(ProcessDTO requestDTO) {
        try {
            //TODO FIGURE OUT HOW TO SAVE STAGES TO PROCESSES
            Processes processes = new Processes(requestDTO.title, requestDTO.discontinued);
            System.out.println(requestDTO.stage.toString());

            processRepository.save(processes);
            Stage newStage = new Stage();
            List<StageDTO> stageList = requestDTO.stage;
            for (StageDTO stage : stageList){
                newStage.setQuestion(stage.question);
                newStage.setStageOrder(stage.stageOrder);
                newStage.setType(stage.type);
                System.out.println(newStage.toString());
                stageRepository.save(newStage);

                processRepository.save(processes);
            }
            return processRepository.save(processes);

//            return this.processRepository.save(
//                    new Process(requestDTO.title, requestDTO.discontinued, requestDTO.stage));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Processes> getAllProcesses() {
        return processRepository.findAll();
    }

    public void delete(Integer productId) {
        this.processRepository.deleteById(productId);
    }

    public Processes update(ProcessUpdateDTO requestDTO) {
        Optional<Processes> processOpt = this.processRepository.findById(requestDTO.id);
        if(processOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
//
//        for(final Stage stage : requestDTO.stage){
//            this.stageRepository.save(stage);
//        }
        Processes processes = processOpt.get();
        processes.setTitle(requestDTO.title);
        processes.setDiscontinued(requestDTO.discontinued);
//        process.setStage(requestDTO.stage);
//
//        //TODO FIX this here for BE testing
//        process.setStage(requestDTO.stage);

        return this.processRepository.save(processes);
    }

    public Process createAll(ProcessDTO requestDTO) {
        return null;
    }
}
