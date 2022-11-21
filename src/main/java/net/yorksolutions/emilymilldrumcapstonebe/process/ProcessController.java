package net.yorksolutions.emilymilldrumcapstonebe.process;

import net.yorksolutions.emilymilldrumcapstonebe.stage.StageAddDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/process")
@CrossOrigin
public class ProcessController {

    ProcessService processService;
    public ProcessController(ProcessService processService){
        this.processService = processService;
    }

    @PostMapping
    public Processes create(@RequestBody ProcessDTO requestDTO){

        System.out.println("create process post request");
        return this.processService.create(requestDTO);
    }

    @GetMapping
    Iterable<Processes> get() {
        return this.processService.getAllProcesses();
    }
    //
    @DeleteMapping("/{processId}")
    public void delete(@PathVariable Integer processId){
        this.processService.delete(processId);
    }

    @PutMapping
    public Processes update(@RequestBody ProcessUpdateDTO requestDTO){
        return this.processService.update(requestDTO);
    }

    @PutMapping("/addStage")
    public Processes addStage(@RequestBody StageAddDTO requestDTO){
        System.out.println("adding a stage post request");
        return this.processService.addStage(requestDTO);
    }


}
