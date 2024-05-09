package com.korea.basic1.answer;

import com.korea.basic1.User.SiteUser;
import com.korea.basic1.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Page<Answer> findAllByQuestion(Question question, Pageable pageable);

    Optional<List<Answer>> findAllByAuthor(SiteUser user);

    Page<Answer> findAllByQuestion(Question question, Specification<Answer> spec, Pageable pageable);
}