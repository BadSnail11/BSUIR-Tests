package puer.tests.entity;

import java.util.List;

public class Question {
    private int id;
    private String description;
    private List<Integer> answers;

    public Question(String description) {
        this.description = description;
    }

    public Question(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
