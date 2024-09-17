package puer.tests.dao;

import java.util.List;

import org.json.simple.JSONObject;

import puer.tests.entity.Answer;

import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;

public class AnswerDao implements Dao<Answer> {

    private final Path PATH = Paths.get("files\\answers\\");

    private static enum Fields {
        name,
        value,
    }

    private JSONObject answerToJSON(Answer t) throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Fields.name.name(), t.getName());
            jsonObject.put(Fields.value.name(), t.getValue());
            return jsonObject;
        } catch (Exception e) {
            throw e;
        }
    }

    private Answer jsonToAnswer(int id, JSONObject obj) throws Exception {
        try {
            Answer answer = new Answer(id, (String) obj.get(Fields.name.name()));
            answer.setValue(((Long) obj.get(Fields.value.name())).intValue());
            return answer;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Answer get(int id) {
        try {
            JSONObject jsonObject = Dao.readJsonFromFile(PATH.toString(), id);
            Answer answer = jsonToAnswer(id, jsonObject);
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Answer> getAll() {
        try {
            List<Answer> list = new ArrayList<>();
            var files = Files.list(PATH).collect(Collectors.toList());
            Answer answer;
            JSONObject jsonObject;
            int id;
            for (var path : files) {
                id = Integer.parseInt(path.toFile().getName().split("\\.")[0]);
                jsonObject = Dao.readJsonFromFile(PATH.toString(), id);
                answer = jsonToAnswer(id, jsonObject);
                list.add(answer);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Answer t) {
        try {
            JSONObject jsonObject = answerToJSON(t);
            Dao.writeJsonToFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Answer t) {
        try {
            if (!Dao.pathContains(t.getId(), PATH))
                return;
            JSONObject jsonObject = answerToJSON(t);
            Dao.writeJsonToFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Answer t) {
        try {
            if (!Dao.pathContains(t.getId(), PATH))
                return;
            JSONObject jsonObject = answerToJSON(t);
            Dao.deleteJsonFromFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
