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

    @PostMapping
    public StageOptions create(@RequestBody StageOptionsDTO requestDTO){

        return this.stageOptionsService.create(requestDTO);
    }

    @GetMapping
    Iterable<StageOptions> get() {

        return this.stageOptionsService.getAllOptions();
    }
    //
    @DeleteMapping("/{stageId}")
    public void delete(@PathVariable Integer stageId){

        this.stageOptionsService.delete(stageId);
    }


    //TODO
//    @PutMapping
//    public Stage update(@RequestBody StageOptionsUpdateDTO requestDTO){
//
//        return this.stageOptionsService.update(requestDTO);
//    }

}
