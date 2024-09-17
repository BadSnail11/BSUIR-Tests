package puer.tests.dao;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import puer.tests.entity.Question;

import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;

public class QuestionDao implements Dao<Question> {

    private final Path PATH = Paths.get("files\\questions\\");

    private static enum Fields {
        description,
        answers,
    }
    
    private JSONObject questionToJSON(Question t) throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Fields.description.name(), t.getDescription());
            jsonObject.put(Fields.answers.name(), t.getAnswers());
            return jsonObject;
        } catch (Exception e) {
            throw e;
        }
    }

    private Question jsonToQuestion(int id, JSONObject obj) throws Exception {
        try {
            Question question = new Question(id, (String) obj.get(Fields.description.name()));
            // answer.setValue(((Long) obj.get(Fields.value.name())).intValue());
            // question.setAnswers(Arrays.asList((Integer[])((JSONArray) obj.get(Fields.answers)).toArray()));
            question.setAnswers(Dao.jsonArrayToList((JSONArray) obj.get(Fields.answers.name())));
            return question;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Question get(int id) {
        JSONObject jsonObject = Dao.readJsonFromFile(PATH.toString(), id);
        try {
            Question question = jsonToQuestion(id, jsonObject);
            return question;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Question> getAll() {
        try {
            List<Question> list = new ArrayList<>();
            var files = Files.list(PATH).collect(Collectors.toList());
            Question question;
            JSONObject jsonObject;
            int id;
            for (var path: files) {
                id = Integer.parseInt(path.toFile().getName().split("\\.")[0]);
                jsonObject = Dao.readJsonFromFile(PATH.toString(), id);
                question = jsonToQuestion(id, jsonObject);
                list.add(question);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Question t) {
        try {
            JSONObject jsonObject = questionToJSON(t);
            Dao.writeJsonToFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Question t) {
        try {
            if (!Dao.pathContains(t.getId(), PATH))
                return;
            JSONObject jsonObject = questionToJSON(t);
            Dao.writeJsonToFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Question t) {
        try {
            if (!Dao.pathContains(t.getId(), PATH))
                return;
            JSONObject jsonObject = questionToJSON(t);
            Dao.deleteJsonFromFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
