package com.example.demo.Answer;
import java.util.List;
import java.util.Optional;

import com.example.demo.Answer.Answer;
import com.example.demo.Answer.AnswerRepository;
import com.example.demo.DataNotFoundException;
import com.example.demo.Question.Question;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public List<Answer> getList() {
        return this.answerRepository.findAll();
    }

    public void create(Question question, String content) {
        Answer answer = new Answer(); //answerㄹㅏ는 객체구조(answer conteroller)로 가져와서
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question); //setter로 업데이트
        this.answerRepository.save(answer); //save에는 setter로 만들어준 answer를 넣어줌.
    }
}
