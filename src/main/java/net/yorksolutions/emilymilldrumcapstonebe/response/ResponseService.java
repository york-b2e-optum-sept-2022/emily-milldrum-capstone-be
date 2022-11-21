package net.yorksolutions.emilymilldrumcapstonebe.response;

import net.yorksolutions.emilymilldrumcapstonebe.answer.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    ResponseRepository responseRepository;
    AnswerRepository answerRepository;

    public ResponseService(ResponseRepository responseRepository, AnswerRepository answerRepository) {
        this.responseRepository = responseRepository;
        this.answerRepository = answerRepository;
    }

    //create new response
    public Response create(ResponseDTO requestDTO) {
        try {
            Response newResponse = new Response(requestDTO.processes, requestDTO.answer);
            return this.responseRepository.save(newResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get responses by process id
    public Iterable<Response> getById(Integer processId) {
        try {
            return this.responseRepository.findAllByProcesses_Id(processId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
