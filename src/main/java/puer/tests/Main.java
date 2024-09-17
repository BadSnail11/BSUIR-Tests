package puer.tests;

import javafx.application.Application;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import puer.tests.dao.AnswerDao;
import puer.tests.dao.QuestionDao;
import puer.tests.entity.Answer;
import puer.tests.entity.Question;
// import puer.tests.dao.TestDao;
// import puer.tests.entity.Test;
import puer.tests.stages.DefaultStage;

import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.*;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // primaryStage.setTitle("Tests");
        // primaryStage.setMaxWidth(800);
        // primaryStage.setMinWidth(500);
        // primaryStage.setWidth(650);
        // primaryStage.show();
        primaryStage = new DefaultStage();
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        // AnswerDao answerDao = new AnswerDao();
        // Answer answer = new Answer(4, "test");
        // answerDao.save(answer);
        // Answer answer = answerDao.get(4);
        // answerDao.delete(answer);
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.get(0);
        System.out.println(question.getAnswers());
        Application.launch(args);
    }
}