package net.yorksolutions.emilymilldrumcapstonebe.answer;

import org.springframework.stereotype.Service;

@Service
public class AnswerService {


    AnswerRepository answerRepository;

    Answer create(AnswerDTO requestDTO) {
        return this.answerRepository.save(
                new Answer(
                        requestDTO.stage,
                        requestDTO.answer));
    }

}
