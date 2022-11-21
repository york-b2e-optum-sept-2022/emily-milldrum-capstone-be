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

    public Stage create(StageDTO requestDTO) {
        try {
            return this.stageRepository.save(
                    new Stage(
                            requestDTO.question,
                            requestDTO.stageOrder,
                            requestDTO.type,
                            requestDTO.stageOptions));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Stage> getAllStages() {
        try {
            return stageRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {
        try {
            Optional<Stage> stageOpt = this.stageRepository.findById(id);
            if (stageOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                Stage stage = stageOpt.get();
                Optional<Processes> procOpt = this.processesRepository.findByStage(stage);
                if (procOpt.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                Stage stageFormat = stageOpt.get();
                Processes processes = procOpt.get();
                processes.removeStage(stageFormat);
                this.stageRepository.delete(stageFormat);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Stage update(StageUpdateDTO requestDTO) {
        try {
            Optional<Stage> stageOpt = this.stageRepository.findById(requestDTO.id);
            if (stageOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            Stage stage = stageOpt.get();
            stage.setQuestion(requestDTO.question);
            stage.setStageOrder(requestDTO.stageOrder);
            stage.setType(requestDTO.type);

            return this.stageRepository.save(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //this adds a new stage to an existing process
    public Processes addToProcess(StageAddDTO requestDTO) {
        try {
            Stage stage = new Stage(
                    requestDTO.question,
                    requestDTO.stageOrder,
                    requestDTO.type,
                    requestDTO.stageOptions);
            this.stageRepository.save(stage);
            Optional<Processes> processOpt = this.processesRepository.findById(requestDTO.processId);
            if (processOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            Processes process = processOpt.get();

            process.addStage(stage);
            return this.processesRepository.save(process);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
