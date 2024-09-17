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
}
