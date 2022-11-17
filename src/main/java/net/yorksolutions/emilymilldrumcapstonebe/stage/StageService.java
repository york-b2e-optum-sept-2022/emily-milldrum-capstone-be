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

    public StageService(StageRepository stageRepository, ProcessRepository processRepository) {
        this.stageRepository = stageRepository;
        this.processRepository = processRepository;
    }

    public Stage create(StageDTO requestDTO) {

        try {
            Optional<Processes> optProc = this.processRepository.findById(requestDTO.processId);
            if(!optProc.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            Stage result = this.stageRepository.save(
                    new Stage(
                            optProc.get(),
                            requestDTO.question,
                            requestDTO.stageOrder,
                            requestDTO.type,
                            requestDTO.stageOptions));
//            Processes thisProc = optProc.get();
//            List<Stage> stageList = thisProc.getStage();
//            stageList.add(result);
//
//            this.processRepository.save(thisProc);

            return result;

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
