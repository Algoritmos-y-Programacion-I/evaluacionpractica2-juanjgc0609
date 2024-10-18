package model;

public class Pillar {

    private String name;
    private Project[] projects;
    private int projectCount;

    public Pillar(String name) {
        this.name = name;
        projects = new Project[50]; // Máximo 50 proyectos
        projectCount = 0;
    }

    public String getName() {
        return name;
    }

    /**
     * Descripcion: Añade un nuevo Project al pilar, evitando IDs duplicados
     * @param newProject El Project que se va a añadir
     * @return boolean true si se logra añadir el Project, false si el ID ya existe
     */
    public boolean registerProject(Project newProject) {
        if (projectCount >= 50) {
            return false; // No se puede añadir más proyectos
        }

        // Verificar si el ID ya existe
        for (int i = 0; i < projectCount; i++) {
            if (projects[i].getId().equals(newProject.getId())) {
                return false; // ID duplicado
            }
        }

        // Añadir el proyecto
        projects[projectCount] = newProject;
        projectCount++;
        return true;
    }

    /**
     * Descripcion: Genera una lista de los proyectos registrados en el pilar
     * @return String con la información de los proyectos
     */
    public String getProjectList() {
        StringBuilder list = new StringBuilder("Proyectos en el Pilar " + name + ":\n");

        if (projectCount == 0) {
            list.append("No hay proyectos registrados.\n");
        } else {
            for (int i = 0; i < projectCount; i++) {
                list.append(projects[i].toString()).append("\n");
            }
        }
        return list.toString();
    }
}
