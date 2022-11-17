package net.yorksolutions.emilymilldrumcapstonebe.process;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageRepository;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProcessService {

    ProcessesRepository processesRepository;
    StageRepository stageRepository;
    StageService stageService;

    public ProcessService(ProcessesRepository processesRepository, StageRepository stageRepository) {
        this.processesRepository = processesRepository;
        this.stageRepository = stageRepository;
    }

    public Processes create(ProcessDTO requestDTO) {
        try {
            Processes newProc = new Processes(requestDTO.title, requestDTO.discontinued, requestDTO.stage);
//            Proccesses newProc = new Processes();
//            System.out.println(requestDTO.stage.toString());
//
//            processRepository.save(processes);
            this.processesRepository.save(newProc);
            Stage newStage = new Stage();
//            List<StageDTO> stageList = requestDTO.stage;
            for (Stage stage : requestDTO.stage){
                newStage.setQuestion(stage.getQuestion());
                newStage.setStageOrder(stage.getStageOrder());
                newStage.setType(stage.getType());
                         System.out.println(newStage.toString());
                this.stageRepository.save(newStage);


                newProc.getStage().add(this.stageRepository.save(this.createStage(stage)));
            }
            return this.processesRepository.save(newProc);

//
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Stage createStage(Stage stage) {
        Stage newStage = new Stage();
        newStage.setQuestion(stage.getQuestion());
        newStage.setStageOrder(stage.getStageOrder());
        newStage.setType(stage.getType());

        return newStage;
    }

    public Iterable<Processes> getAllProcesses() {
        return processesRepository.findAll();
    }

    public void delete(Integer productId) {
        this.processesRepository.deleteById(productId);
    }

    public Processes update(ProcessUpdateDTO requestDTO) {
        Optional<Processes> processOpt = this.processesRepository.findById(requestDTO.id);
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

        return this.processesRepository.save(processes);
    }

    public Process createAll(ProcessDTO requestDTO) {
        return null;
    }
}
