package net.yorksolutions.emilymilldrumcapstonebe.process;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProcessService {

    ProcessRepository processRepository;

    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public Process create(ProcessDTO requestDTO) {
        try {
            return this.processRepository.save(
                    new Process(requestDTO.title, requestDTO.stage));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Process> getAllProcesses() {
        return processRepository.findAll();
    }

    public void delete(Integer productId) {
        this.processRepository.deleteById(productId);
    }

    public Process update(ProcessUpdateDTO requestDTO) {
        Optional<Process> processOpt = this.processRepository.findById(requestDTO.id);
        if(processOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Process process = processOpt.get();
        process.setTitle(requestDTO.title);

        return this.processRepository.save(process);
    }
}
