package com.korea.dulgiUI.comment;

import com.korea.dulgiUI.User.SiteUser;
import com.korea.dulgiUI.answer.Answer;
import com.korea.dulgiUI.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment create(Question question,
                          String content,
                          SiteUser author) {

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthor(author);
        comment.setQuestion(question);
        comment.setCreateDate(LocalDateTime.now());
        this.commentRepository.save(comment);
        return comment;
    }

    public Comment create(Answer answer,
                          String content,
                          SiteUser author) {

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthor(author);
        comment.setAnswer(answer);
        comment.setCreateDate(LocalDateTime.now());
        this.commentRepository.save(comment);
        return comment;
    }

    public List<Comment> findByUserId(Long userId) {
        return commentRepository.findByAuthor_Id(userId);
    }
}