package net.yorksolutions.emilymilldrumcapstonebe.stageOptions;

import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageDTO;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageService;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageUpdateDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stageOptions")
@CrossOrigin
public class StageOptionsController {

    StageOptionsService stageOptionsService;
    public StageOptionsController(StageOptionsService stageOptionsService){
        this.stageOptionsService = stageOptionsService;
    }

    @GetMapping
    Iterable<StageOptions> get() {
        return this.stageOptionsService.getAllOptions();
    }

    @DeleteMapping("/{stageId}")
    public void delete(@PathVariable Integer stageId){
        this.stageOptionsService.delete(stageId);
    }

    @PutMapping
    public StageOptions update(@RequestBody StageOptionsUpdateDTO requestDTO){
        return this.stageOptionsService.update(requestDTO);
    }

}
