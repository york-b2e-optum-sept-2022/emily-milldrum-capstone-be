package net.yorksolutions.emilymilldrumcapstonebe.response;

import net.yorksolutions.emilymilldrumcapstonebe.answer.AnswerRepository;
import net.yorksolutions.emilymilldrumcapstonebe.process.Processes;
import net.yorksolutions.emilymilldrumcapstonebe.process.ProcessesRepository;
import net.yorksolutions.emilymilldrumcapstonebe.stage.StageRepository;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    ResponseRepository responseRepository;
    AnswerRepository answerRepository;

    public ResponseService(ResponseRepository responseRepository, AnswerRepository answerRepository) {
        this.responseRepository = responseRepository;
        this.answerRepository = answerRepository;
    }
    public Response create(ResponseDTO requestDTO) {
        try {
            Response newResponse = new Response(requestDTO.processes, requestDTO.answer);
            return this.responseRepository.save(newResponse);

//
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Response> getById(Integer processId) {
        return this.responseRepository.findAllByProcesses_Id(processId);
    }
}
