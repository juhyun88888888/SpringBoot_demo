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
import com.example.demo.User.SiteUser;
import com.example.demo.User.UserService;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserService userService;

    public List<Answer> getList() {
        return this.answerRepository.findAll();
    }

    public Answer create(Question question, String content, SiteUser author) {
        Answer answer = new Answer(); //answerㄹㅏ는 객체구조(answer conteroller)로 가져와서
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question); //setter로 업데이트
        answer.setAuthor(author);
        this.answerRepository.save(answer); //save에는 setter로 만들어준 answer를 넣어줌.
        return answer;
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }

}
