package net.yorksolutions.emilymilldrumcapstonebe.stageOptions;
import net.yorksolutions.emilymilldrumcapstonebe.process.Processes;
import net.yorksolutions.emilymilldrumcapstonebe.stage.Stage;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageAddDTO;
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

    @DeleteMapping("/{optId}")
    public Stage delete(@PathVariable Integer optId){
        return this.stageOptionsService.delete(optId);
    }

    @PutMapping
    public StageOptions update(@RequestBody StageOptionsUpdateDTO requestDTO){
        return this.stageOptionsService.update(requestDTO);
    }

    @PostMapping("/addToStage/")
    public Stage addToStage(@RequestBody StageOptionsAddDTO requestDTO){
        return this.stageOptionsService.addToStage(requestDTO);
    }

}
