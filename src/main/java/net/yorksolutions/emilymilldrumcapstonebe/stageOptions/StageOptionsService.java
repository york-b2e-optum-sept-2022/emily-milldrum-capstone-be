package net.yorksolutions.emilymilldrumcapstonebe.stageOptions;

import net.yorksolutions.emilymilldrumcapstonebe.process.Processes;
import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class StageOptionsService {

    StageOptionsRepository stageOptionsRepository;

    public StageOptionsService(StageOptionsRepository stageOptionsRepository) {
        this.stageOptionsRepository = stageOptionsRepository;
    }

    public void delete(Integer optId) {
        try {
            this.stageOptionsRepository.deleteById(optId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<StageOptions> getAllOptions() {
        return this.stageOptionsRepository.findAll();
    }

    public StageOptions createOption(StageOptions option) {
        return this.stageOptionsRepository.save(option);
    }

    public StageOptions update(StageOptionsUpdateDTO requestDTO) {

        Optional<StageOptions> stageOpt = this.stageOptionsRepository.findById(requestDTO.id);
        if(stageOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        StageOptions option = stageOpt.get();
        option.setOption(requestDTO.option);
        return this.stageOptionsRepository.save(option);
    }
}
