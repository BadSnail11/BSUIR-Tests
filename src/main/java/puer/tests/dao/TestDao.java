package puer.tests.dao;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import puer.tests.entity.Test;

import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;

public class TestDao implements Dao<Test> {

    private final Path PATH = Paths.get("files\\tests\\");

    private static enum Fields {
        name,
        description,
        questions,
        results,
    }

    private JSONObject testToJSON(Test t) throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Fields.name.name(), t.getName());
            jsonObject.put(Fields.description.name(), t.getDescription());
            jsonObject.put(Fields.questions.name(), t.getQuestions());
            jsonObject.put(Fields.results.name(), t.getResults());
            return jsonObject;
        } catch (Exception e) {
            throw e;
        }
    }

    private Test jsonToTest(int id, JSONObject obj) throws Exception {
        try {
            Test test = new Test(id, (String) obj.get(Fields.name.name()));
            test.setDescription((String) obj.get(Fields.description.name()));
            test.setQuestions(Dao.jsonArrayToList((JSONArray) obj.get(Fields.questions.name())));
            test.setResults(Dao.jsonArrayToList((JSONArray) obj.get(Fields.results.name())));
            return test;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Test get(int id) {
        try {
            JSONObject jsonObject = Dao.readJsonFromFile(PATH.toString(), id);
            Test test = jsonToTest(id, jsonObject);
            return test;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Test> getAll() {
        try {
            List<Test> list = new ArrayList<>();
            var files = Files.list(PATH).collect(Collectors.toList());
            Test test;
            JSONObject jsonObject;
            int id;
            for (var path : files) {
                id = Integer.parseInt(path.toFile().getName().split("\\.")[0]);
                jsonObject = Dao.readJsonFromFile(PATH.toString(), id);
                test = jsonToTest(id, jsonObject);
                list.add(test);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Test t) {
        try {
            t.setId(Dao.getNewId(PATH));
            JSONObject jsonObject = testToJSON(t);
            Dao.writeJsonToFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void update(Test t) {
        try {
            if (!Dao.pathContains(t.getId(), PATH))
                return;
            JSONObject jsonObject = testToJSON(t);
            Dao.writeJsonToFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Test t) {
        try {
            if (!Dao.pathContains(t.getId(), PATH))
                return;
            JSONObject jsonObject = testToJSON(t);
            Dao.deleteJsonFromFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
