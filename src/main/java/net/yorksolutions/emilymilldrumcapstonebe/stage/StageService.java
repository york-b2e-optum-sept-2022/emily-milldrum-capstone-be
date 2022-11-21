package net.yorksolutions.emilymilldrumcapstonebe.stage;

import net.yorksolutions.emilymilldrumcapstonebe.process.ProcessService;
import net.yorksolutions.emilymilldrumcapstonebe.process.Processes;
import net.yorksolutions.emilymilldrumcapstonebe.process.ProcessesRepository;
import net.yorksolutions.emilymilldrumcapstonebe.stageOptions.StageOptions;
import net.yorksolutions.emilymilldrumcapstonebe.stageOptions.StageOptionsRepository;
import net.yorksolutions.emilymilldrumcapstonebe.stageOptions.StageOptionsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StageService {

    StageRepository stageRepository;
    ProcessesRepository processesRepository;
    StageOptionsRepository optionsRepository;
    StageOptionsService optionsService;

    public StageService(StageRepository stageRepository, ProcessesRepository processesRepository, StageOptionsRepository optionsRepository
    ) {
        this.stageRepository = stageRepository;
        this.processesRepository = processesRepository;
        this.optionsRepository = optionsRepository;
    }

//    public Stage create(StageDTO requestDTO) {
//        Optional<Processes> optProc = this.processRepository.findById(requestDTO.processId);
//        if(optProc.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//        Processes thisProc = optProc.get();
//        try {
////            Stage stage = new Stage();
////            stage.setQuestion(requestDTO.question);
////            stage.setStageOrder(requestDTO.stageOrder);
////            stage.setType(requestDTO.type);
////            stage.setStageOptions(requestDTO.stageOptions);
////            return this.stageRepository.save(
////                   stage);
////
////            Stage result =
////            this.processRepository.save(thisProc);
//
//            return this.stageRepository.save(
//                    new Stage(
//                            optProc.get(),
//                            requestDTO.question,
//                            requestDTO.stageOrder,
//                            requestDTO.type,
//                            requestDTO.stageOptions));
//
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }
//    }
    public Stage create(StageDTO requestDTO) {
//        Optional<Processes> optProc = this.processRepository.findById(requestDTO.processId);
//        if(optProc.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//        }

        //try {
            return this.stageRepository.save(
                    new Stage(
                            //optProc.get(),
                            requestDTO.question,
                            requestDTO.stageOrder,
                            requestDTO.type,
                            requestDTO.stageOptions));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

     public Stage createStage(Stage stage) {
         try {
        Stage newStage = new Stage();
        newStage.setQuestion(stage.getQuestion());
        newStage.setStageOrder(stage.getStageOrder());
        newStage.setType(stage.getType());

             this.stageRepository.save(newStage);
         //    StageOptions newStageOpt = new StageOptions();
             for (StageOptions option : stage.getStageOptions()){
                 newStage.getStageOptions().add(this.optionsRepository.save(this.optionsService.createOption(option)));
             }

        return newStage;
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }


    public Iterable<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public void delete(Integer id) {

        Optional <Stage> stageOpt = this.stageRepository.findById(id);
        if (stageOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }else {
            Stage stage = stageOpt.get();
            Optional <Processes> procOpt = this.processesRepository.findByStage(stage);
            if (procOpt.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            Stage stageFormat = stageOpt.get();
            Processes processes = procOpt.get();
            processes.removeStage(stageFormat);
            this.stageRepository.delete(stageFormat);
        }
    }


    public Stage update(StageUpdateDTO requestDTO) {
        Optional<Stage> stageOpt = this.stageRepository.findById(requestDTO.id);
        if(stageOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Stage stage = stageOpt.get();
            stage.setQuestion(requestDTO.question);
            stage.setStageOrder(requestDTO.stageOrder);
            stage.setType(requestDTO.type);

        return this.stageRepository.save(stage);
    }

    public Processes addToProcess(StageAddDTO requestDTO) {
        Stage stage = new Stage(
                requestDTO.question,
                requestDTO.stageOrder,
                requestDTO.type,
                requestDTO.stageOptions);
        this.stageRepository.save(stage);
        Optional<Processes> processOpt = this.processesRepository.findById(requestDTO.processId);
        if(processOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Processes process = processOpt.get();

        process.addStage(stage);
        return this.processesRepository.save(process);
    }

//    public Iterable<Stage> findStagesByProcessId(Integer processId) {
//        return this.stageRepository.findStagesByProcessId(processId);
//    }
}
