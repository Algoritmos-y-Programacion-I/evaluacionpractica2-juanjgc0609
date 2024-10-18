package ui;

import java.util.Scanner;
import model.Controller;

public class Executable {

    private Controller control;
    private Scanner reader;

    public Executable() {
        control = new Controller();
        reader = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Executable exe = new Executable();
        exe.menu();
    }

    public void menu() {
        boolean flag = true;

        do {
            System.out.println("\nBienvenido a Icesi Sostenible!");
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("----------------------");
            System.out.println("1) Registrar un Proyecto en un Pilar");
            System.out.println("2) Consultar Proyectos por Pilar");
            System.out.println("0) Salir");

            int option = getValidInteger(0, 2);  // Permite solo las opciones 0 a 2

            switch (option) {
                case 1:
                    registerProject();
                    break;
                case 2:
                    showProjectsByPillar();
                    break;
                case 0:
                    System.out.println("Gracias por usar nuestros servicios. ¡Adiós!");
                    flag = false;
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
                    break;
            }
        } while (flag);
    }

    public void registerProject() {
        System.out.println("Seleccione el Pilar (1- Biodiversidad, 2- Agua, 3- Tratamiento de Basuras, 4- Energía): ");
        int pillarType = getValidInteger(1, 4);  // Permite solo las opciones 1 a 4
        

        String id;
        boolean isUniqueId = false;

        // Validación del ID hasta que sea único
        do {
            System.out.println("Ingrese el ID del proyecto:");
            id = reader.nextLine();

            // Intentar registrar un proyecto con solo el ID para validar duplicados
            isUniqueId = control.isUniqueIdInPillar(pillarType, id);

            if (!isUniqueId) {
                System.out.println("Error: El ID ya existe en este Pilar. Por favor, ingrese un ID diferente.");
            }
        } while (!isUniqueId);

        // Si el ID es válido, continuar con el resto de la información
        System.out.println("Ingrese el nombre del proyecto:");
        String name = reader.nextLine();

        System.out.println("Ingrese la descripción del proyecto:");
        String description = reader.nextLine();

        System.out.println("Estado del proyecto (1- Activo, 2- Inactivo):");
        int statusOption = getValidInteger(1, 2);  // Permite solo las opciones 1 y 2

        // Registrar el proyecto a través del controlador
        control.registerProject(pillarType, id, name, description, statusOption);

        System.out.println("Proyecto registrado exitosamente.");
    }

    public void showProjectsByPillar() {
        System.out.println("Seleccione el Pilar (1- Biodiversidad, 2- Agua, 3- Tratamiento de Basuras, 4- Energía): ");
        int pillarType = getValidInteger(1, 4);  // Permite solo las opciones 1 a 4
        System.out.println(control.queryProjectsByPillar(pillarType));
    }

    /**
     * Método auxiliar para validar y obtener una entrada entera válida
     * 
     * @param min El valor mínimo permitido
     * @param max El valor máximo permitido
     * @return Un entero que está dentro del rango especificado
     */
    private int getValidInteger(int min, int max) {
        String input;
        int number;
        boolean isValid = false;

        do {
            input = reader.nextLine();

            if (isNumeric(input)) {
                number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    isValid = true;
                    return number;
                } else {
                    System.out.println("Error: El número debe estar entre " + min + " y " + max + ". Inténtelo de nuevo.");
                }
            } else {
                System.out.println("Error: Entrada no válida. Ingrese un número.");
            }
        } while (!isValid);

        return -1; // Nunca debería llegar aquí
    }

    /**
     * Verifica si una cadena es numérica
     * 
     * @param str La cadena a verificar
     * @return Verdadero si es numérica, falso si no lo es
     */
    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
