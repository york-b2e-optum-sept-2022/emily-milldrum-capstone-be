package net.yorksolutions.emilymilldrumcapstonebe.stage;

import net.yorksolutions.emilymilldrumcapstonebe.process.Process;
import net.yorksolutions.emilymilldrumcapstonebe.process.ProcessDTO;
import net.yorksolutions.emilymilldrumcapstonebe.process.ProcessService;
import net.yorksolutions.emilymilldrumcapstonebe.process.ProcessUpdateDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stage")
@CrossOrigin
public class StageController {
    StageService stageService;
    public StageController(StageService stageService){
        this.stageService = stageService;
    }



    @PostMapping
    public Stage create(@RequestBody StageDTO requestDTO){
        return this.stageService.create(requestDTO);
    }

    @GetMapping
    Iterable<Stage> get() {
        return this.stageService.getAllStages();
    }
    //
    @DeleteMapping("/{stageId}")
    public void delete(@PathVariable Integer stageId){
        this.stageService.delete(stageId);
    }

    @PutMapping
    public Stage update(@RequestBody StageUpdateDTO requestDTO){
        return this.stageService.update(requestDTO);
    }

}