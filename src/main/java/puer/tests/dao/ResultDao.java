package puer.tests.dao;

import puer.tests.entity.Result;

import java.util.List;

import org.json.simple.JSONObject;

import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;

public class ResultDao implements Dao<Result> {
    
    private final Path PATH = Paths.get("files\\results\\");

    private static enum Fields {
        description,
        minValue,
        maxValue,
    }

    private JSONObject resultToJSON(Result t) throws Exception {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Fields.description.name(), t.getDescription());
            jsonObject.put(Fields.minValue.name(), t.getMinValue());
            jsonObject.put(Fields.maxValue.name(), t.getMaxValue());
            return jsonObject;
        } catch (Exception e) {
            throw e;
        }
    }

    private Result jsonToResult(int id, JSONObject obj) throws Exception {
        try {
            Result result = new Result(id, (String) obj.get(Fields.description.name()));
            result.setMinValue(((Long) obj.get(Fields.minValue.name())).intValue());
            result.setMaxValue(((Long) obj.get(Fields.maxValue.name())).intValue());
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result get(int id) {
        try {
            JSONObject jsonObject = Dao.readJsonFromFile(PATH.toString(), id);
            Result result = jsonToResult(id, jsonObject);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Result> getAll() {
        try {
            List<Result> list = new ArrayList<>();
            var files = Files.list(PATH).collect(Collectors.toList());
            Result result;
            JSONObject jsonObject;
            int id;
            for (var path : files) {
                id = Integer.parseInt(path.toFile().getName().split("\\.")[0]);
                jsonObject = Dao.readJsonFromFile(PATH.toString(), id);
                result = jsonToResult(id, jsonObject);
                list.add(result);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Result t) {
        try {
            JSONObject jsonObject = resultToJSON(t);
            Dao.writeJsonToFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Result t) {
        try {
            if (!Dao.pathContains(t.getId(), PATH))
                return;
            JSONObject jsonObject = resultToJSON(t);
            Dao.writeJsonToFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Result t) {
        try {
            if (!Dao.pathContains(t.getId(), PATH))
                return;
            JSONObject jsonObject = resultToJSON(t);
            Dao.deleteJsonFromFile(jsonObject, PATH.toString(), t.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
