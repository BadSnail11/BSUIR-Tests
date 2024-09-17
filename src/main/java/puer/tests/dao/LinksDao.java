package puer.tests.dao;

import java.util.List;
import java.util.ArrayList;

import puer.tests.entity.*;

public class LinksDao {
    
    public static List<Question> questionsByTest(Test test) {
        QuestionDao questionDao = new QuestionDao();
        List<Question> list = new ArrayList<>();
        for (var id : test.getQuestions()) {
            list.add(questionDao.get(id));
        }
        return list;
    }

    public static List<Result> resultsByTest(Test test) {
        ResultDao resultDao = new ResultDao();
        List<Result> list = new ArrayList<>();
        for (var id : test.getResults()) {
            list.add(resultDao.get(id));
        }
        return list;
    }

    public static List<Answer> answersByQuestion(Question question) {
        AnswerDao answerDao = new AnswerDao();
        List<Answer> list = new ArrayList<>();
        for (var id : question.getAnswers()) {
            list.add(answerDao.get(id));
        }
        return list;
    }
}
