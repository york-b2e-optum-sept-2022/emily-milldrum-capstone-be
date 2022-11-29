package net.yorksolutions.emilymilldrumcapstonebe.stageOptions;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StageOptionsService {

    StageOptionsRepository stageOptionsRepository;
    StageRepository stageRepository;

    public StageOptionsService(StageOptionsRepository stageOptionsRepository, StageRepository stageRepository) {
        this.stageOptionsRepository = stageOptionsRepository;
        this.stageRepository = stageRepository;
    }

    public Stage delete(Integer optId) {
        Optional<StageOptions> stageOpt = this.stageOptionsRepository.findById(optId);
        if (stageOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            StageOptions options = stageOpt.get();
            Optional<Stage> found = stageRepository.findStageByStageOptions(options);
            if (found.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                Stage stage = found.get();
                this.stageOptionsRepository.deleteById(optId);
                return stage;
            }
        }
    }

    public Iterable<StageOptions> getAllOptions() {
        try {
            return this.stageOptionsRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public StageOptions createOption(StageOptions option) {

        try {
            return this.stageOptionsRepository.save(option);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public StageOptions update(StageOptionsUpdateDTO requestDTO) {

        try {

            Optional<StageOptions> stageOpt = this.stageOptionsRepository.findById(requestDTO.id);
            if (stageOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            StageOptions option = stageOpt.get();
            option.setOption(requestDTO.option);
            return this.stageOptionsRepository.save(option);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //this adds a new stage:choice to an existing stage
    public Stage addToStage(StageOptionsAddDTO requestDTO) {
        try {
            StageOptions stageOption = new StageOptions(
                    requestDTO.option);
            this.stageOptionsRepository.save(stageOption);
            Optional<Stage> stageOpt = this.stageRepository.findById(requestDTO.stageId);
            if (stageOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            Stage stage = stageOpt.get();

            stage.addStageOpt(stageOption);
            return this.stageRepository.save(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
