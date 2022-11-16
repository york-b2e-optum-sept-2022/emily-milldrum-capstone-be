package net.yorksolutions.emilymilldrumcapstonebe.stage;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StageService {

    StageRepository stageRepository;

    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public Stage create(StageDTO requestDTO) {
        try {
            return this.stageRepository.save(
                    new Stage(requestDTO.question, requestDTO.ordering,
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
            stage.setOrdering(requestDTO.ordering);
            stage.setType(requestDTO.type);

        return this.stageRepository.save(stage);
    }

}
