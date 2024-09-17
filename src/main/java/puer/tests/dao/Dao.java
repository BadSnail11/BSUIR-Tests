package puer.tests.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Files;

public interface Dao<T> {
    
    T get(int id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

    public static JSONObject readJsonFromFile(String path) {
        String fileName = path;
        try (FileReader file = new FileReader(fileName)) {
            return (JSONObject) new JSONParser().parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject readJsonFromFile(String path, int id) {
        String fileName = path + "\\" + Integer.toString(id) + ".json";
        try (FileReader file = new FileReader(fileName)) {
            return (JSONObject) new JSONParser().parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeJsonToFile(JSONObject jsonObject, String path, int id) {
        String fileName = path + "\\" + Integer.toString(id) + ".json";
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteJsonFromFile(JSONObject jsonObject, String path, int id) {
        String fileName = path + "\\" + Integer.toString(id) + ".json";
        try {
            File file = new File(fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> jsonArrayToList(JSONArray arr) {
        List<Integer> list = new ArrayList<>();
        for (Object obj : arr) {
            list.add(((Long) obj).intValue());
        }
        return list;
    }

    public static boolean pathContains(int id, Path path) {
        try {
            var files = Files.list(path).collect(Collectors.toList());
            int currentId;
            for (var currentPath : files) {
                currentId = Integer.parseInt(currentPath.toFile().getName().split("\\.")[0]);
                if (currentId == id) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getNewId(Path path) {
        try {
            var files = Files.list(path).collect(Collectors.toList());
            int id = -1;
            int currentId;
            for (var currentPath : files) {
                currentId = Integer.parseInt(currentPath.toFile().getName().split("\\.")[0]);
                if (currentId > id) {
                    id = currentId;
                }
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
