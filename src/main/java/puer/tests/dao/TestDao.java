package puer.tests.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.charset.StandardCharsets;
import java.util.List;

import puer.tests.entity.*;

import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.*;

public class TestDao implements Dao<Test> {

    private String path = "files\\tests\\";
    // private static final Path

    private static enum Fields {
        name,
        questions,
        results,
    }

    @Override
    public Test get(int id) {
        JSONObject jsonObject = Dao.readJsonFromFile(path, id);
        if (jsonObject != null) {
            // System.out.println(jsonObject.toJSONString());
            Test test = new Test((String) jsonObject.get(Fields.name.name()));
            test.setQuestions(((JSONArray)jsonObject.get(Fields.questions.name())));
            return test;
        }
        return null;
    }

    @Override
    public List<Test> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Test t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Test t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Test t) {
        // TODO Auto-generated method stub
        
    }
}
