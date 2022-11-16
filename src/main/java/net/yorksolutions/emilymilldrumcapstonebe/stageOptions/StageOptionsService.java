package net.yorksolutions.emilymilldrumcapstonebe.stageOptions;

import net.yorksolutions.emilymilldrumcapstonebe.process.ProcessRepository;
import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageDTO;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageRepository;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public class StageOptionsService {

    StageOptionsRepository stageOptionsRepository;

    public StageOptionsService(StageOptionsRepository stageOptionsRepository) {
        this.stageOptionsRepository = stageOptionsRepository;
    }

    public void delete(Integer optId) {
        this.stageOptionsRepository.deleteById(optId);
    }

    public Iterable<StageOptions> getAllOptions() {
        return this.stageOptionsRepository.findAll();
    }

    public StageOptions create(StageOptionsDTO requestDTO) {
        try {
            return this.stageOptionsRepository.save(
                    new Stage(
                            //requestDTO.processId,
                            requestDTO.
                            //TODO FIX ORDER
                            requestDTO.stageOrder,
                            requestDTO.type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
        return this.stageOptionsRepository.save(requestDTO);
    }

//TODO

//    public Stage update(StageOptionDTO requestDTO) {
//    }
}
