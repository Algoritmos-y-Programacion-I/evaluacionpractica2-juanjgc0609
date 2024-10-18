package model;

import java.util.ArrayList;

public class Controller {

    private ArrayList<Project> projects;

    public Controller() {
        projects = new ArrayList<>();
    }

    public boolean isUniqueIdInPillar(int pillarType, String id) {
        for (Project project : projects) {
            if (project.getPillarType() == pillarType && project.getId().equals(id)) {
                return false; // Si ya existe un proyecto con el mismo ID en el mismo pilar, devuelve falso
            }
        }
        return true; // ID único
    }

    public void registerProject(int pillarType, String id, String name, String description, int statusOption) {
        ProjectStatus status = (statusOption == 1) ? ProjectStatus.ACTIVE : ProjectStatus.INACTIVE;
        projects.add(new Project(pillarType, id, name, description, status));
    }

    public String queryProjectsByPillar(int pillarType) {
        StringBuilder result = new StringBuilder("Proyectos en el Pilar " + pillarType + ":\n");

        boolean hasProjects = false;

        for (Project project : projects) {
            if (project.getPillarType() == pillarType) {
                result.append(project.toString()).append("\n");
                hasProjects = true;
            }
        }

        // Si no se encontraron proyectos, mostrar mensaje específico
        if (!hasProjects) {
            return "Aún no se ha registrado ningún proyecto en este Pilar. Registre al menos uno.";
        }

        return result.toString();
    }
}
