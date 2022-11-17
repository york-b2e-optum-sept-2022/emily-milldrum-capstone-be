package net.yorksolutions.emilymilldrumcapstonebe.stage;

import net.yorksolutions.emilymilldrumcapstonebe.process.ProcessRepository;
import net.yorksolutions.emilymilldrumcapstonebe.process.Processes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class StageService {

    StageRepository stageRepository;
    ProcessRepository processRepository;

    public StageService(StageRepository stageRepository//, ProcessRepository processRepository
    ) {
        this.stageRepository = stageRepository;
       // this.processRepository = processRepository;
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
        try {
            return this.stageRepository.save(
                    new Stage(
                            requestDTO.question,
                            requestDTO.stageOrder,
                            requestDTO.type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public void delete(Integer stageId) {
        this.stageRepository.deleteById(stageId);
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

//    public Iterable<Stage> findStagesByProcessId(Integer processId) {
//        return this.stageRepository.findStagesByProcessId(processId);
//    }
}
