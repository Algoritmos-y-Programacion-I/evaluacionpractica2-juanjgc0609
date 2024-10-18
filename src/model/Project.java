package model;

public class Project {

    private int pillarType;
    private String id;
    private String name;
    private String description;
    private ProjectStatus status;

    public Project(int pillarType, String id, String name, String description, ProjectStatus status) {
        this.pillarType = pillarType;
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getPillarType() {
        return pillarType;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + name + ", Descripción: " + description + ", Estado: " + status;
    }
}
