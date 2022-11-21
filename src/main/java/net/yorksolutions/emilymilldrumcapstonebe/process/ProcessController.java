package net.yorksolutions.emilymilldrumcapstonebe.process;
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
        return this.processService.create(requestDTO);
    }

    @GetMapping
    Iterable<Processes> get() {
        return this.processService.getAllProcesses();
    }

    @DeleteMapping("/{processId}")
    public void delete(@PathVariable Integer processId){
        this.processService.delete(processId);
    }

    @PutMapping
    public Processes update(@RequestBody ProcessUpdateDTO requestDTO){
        return this.processService.update(requestDTO);
    }


}
