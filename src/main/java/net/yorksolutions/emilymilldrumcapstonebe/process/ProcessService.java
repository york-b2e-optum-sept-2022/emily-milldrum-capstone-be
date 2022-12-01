package net.yorksolutions.emilymilldrumcapstonebe.process;

import net.yorksolutions.emilymilldrumcapstonebe.stage.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProcessService {

    ProcessesRepository processesRepository;
    StageRepository stageRepository;
    StageService stageService;

    public ProcessService(ProcessesRepository processesRepository, StageRepository stageRepository) {
        this.processesRepository = processesRepository;
        this.stageRepository = stageRepository;
    }

    //creat a new process
    public Processes create(ProcessDTO requestDTO) {
        try {
            Processes newProc = new Processes(requestDTO.title, requestDTO.discontinued, requestDTO.stage);
            return this.processesRepository.save(newProc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get a list of all processes
    public Iterable<Processes> getAllProcesses() {
        try {
            return processesRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //delete a process
    public void delete(Integer processId) {
        try {
            Optional<Processes> processOpt = getOpt(processId);
            Processes process = processOpt.get();
            process.getStage().removeAll(process.getStage());
            this.processesRepository.deleteById(processId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //update an existing process
    public Processes update(ProcessUpdateDTO requestDTO) {
        try {
            Optional<Processes> processOpt = this.processesRepository.findById(requestDTO.id);
            if (processOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                Processes processes = processOpt.get();
                processes.setTitle(requestDTO.title);
                processes.setDiscontinued(requestDTO.discontinued);
                for (Stage stage: requestDTO.stage)
                {
                    this.stageRepository.save(stage);
                }
                return this.processesRepository.save(processes);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get an optional process by id
    public Optional<Processes> getOpt(Integer id) {
        try {
            Optional<Processes> processOpt = this.processesRepository.findById(id);
            if (processOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return processOpt;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
