package Proj1;

import java.io.*;
import java.util.*;

public class Studentclass {

    static Scanner sc = new Scanner(System.in);
    static LinkedList<Studentobj> students = new LinkedList<>();
    static HashSet<Integer> studentIds = new HashSet<>();
    static final String FILE_NAME = "students.txt";
    public static void main(String[] args) {
    	
        loadStudentsFromFile();
        int option;
        do {
            System.out.println();
            System.out.println("========== Student Management System ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("===============================================");
            System.out.print("Please choose an option: ");
            option = readInt();

            try {
                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
                        break;
                    case 6:
                        saveStudentsToFile();
                        System.out.println("Application is closed. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-6.");
                }

            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        } while (option != 6);

        sc.close();
    }


    // 1. ADD STUDENT

    static void addStudent() {

        System.out.println();
        System.out.println("========== Add Student ==========");

        int id;

        while (true) {
            System.out.print("Enter Student ID: ");
            id = readInt();
            // Duplicate ID validation
            if (studentIds.contains(id)) {
                System.out.println("ID already exists. Please enter another ID.");
            } else if (id <= 0) {
                System.out.println("ID must be greater than 0.");
            } else {
                break;
            }
        }

        String name = readString("Enter Name: ");
        int age;
        while (true) {
            System.out.print("Enter Age: ");
            age = readInt();
            if (age <= 0 || age > 120) {
                System.out.println("Please enter a valid age.");
            } else {
                break;
            }
        }

        String city = readString("Enter City: ");
        Studentobj student =
                new Studentobj(id, name, age, city);

        students.add(student);
        studentIds.add(id);

        saveStudentsToFile();
        System.out.println("Student added successfully.");
    }


    // 2. VIEW STUDENTS
    static void viewStudents() {

        System.out.println();
        System.out.println("========== All Students ==========");
        if (students.isEmpty()) {

            System.out.println("There are no records.");
            return;
        }
        // Enhanced for loop
        for (Studentobj student : students) {

            System.out.println(student);
        }

        System.out.println("Total Students: " + students.size());
    }

    // 3. SEARCH STUDENT

    static void searchStudent() {

        System.out.println();
        System.out.println("========== Search Student ==========");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Age");
        System.out.println("3. Search by Name");
        System.out.println("4. Search by City");

        System.out.print("Enter search option: ");

        int option = readInt();
        switch (option) {
            case 1:
                System.out.print("Enter ID: ");
                int id = readInt();
                boolean idFound = false;
                for (Studentobj student : students) {
                    if (student.getId() == id) {

                        System.out.println(student);
                        idFound = true;
                        break;
                    }
                }
                if (!idFound) {
                    System.out.println("No student found with ID: " + id);
                }
                break;
            case 2:
                System.out.print("Enter Age: ");
                int age = readInt();
                boolean ageFound = false;
                for (Studentobj student : students) {
                    if (student.getAge() == age) {
                        System.out.println(student);
                        ageFound = true;
                    }
                }
                if (!ageFound) {
                    System.out.println("No student found with age: " + age);
                }
                break;


            case 3:

                String name = readString("Enter Name: ");
                boolean nameFound = false;
                students.stream()
                        .filter(student ->
                                student.getName()
                                        .equalsIgnoreCase(name))
                        .forEach(student -> {
                            System.out.println(student);
                        });
                nameFound = students.stream()
                        .anyMatch(student ->
                                student.getName()
                                        .equalsIgnoreCase(name));

                if (!nameFound) {
                    System.out.println("No student found with name: " + name);
                }
                break;


            case 4:
                String city = readString("Enter City: ");
                boolean cityFound = false;
                students.stream()
                        .filter(student ->
                                student.getCity()
                                        .equalsIgnoreCase(city))
                        .forEach(student -> {
                            System.out.println(student);
                        });
                cityFound = students.stream()
                        .anyMatch(student ->
                                student.getCity()
                                        .equalsIgnoreCase(city));
                if (!cityFound) {
                    System.out.println("No student found in city: " + city);
                }
                break;

            default:

                System.out.println("Invalid search option.");
        }
    }


    // 4. UPDATE STUDENT

    static void updateStudent() {

        System.out.println();
        System.out.println("========== Update Student ==========");

        System.out.print("Enter Student ID: ");

        int id = readInt();

        // Stream API
        Studentobj student = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);

        if (student == null) {

            System.out.println(
                    "No record found for ID: " + id);

            return;
        }

        System.out.println("Current details:");
        System.out.println(student);
        String updatedName =
                readString("Enter updated name: ");
        String updatedCity =
                readString("Enter updated city: ");
        int updatedAge;

        while (true) {
            System.out.print("Enter updated age: ");
            updatedAge = readInt();
            if (updatedAge <= 0 || updatedAge > 120) {
                System.out.println("Please enter a valid age.");
            } else {
                break;
            }
        }

        student.updstd(
                updatedName,
                updatedCity,
                updatedAge
        );

        // Save changes
        saveStudentsToFile();
        System.out.println("Student updated successfully.");
    }


    // 5. DELETE STUDENT

    static void deleteStudent() {

        System.out.println();
        System.out.println("========== Delete Student ==========");

        System.out.print("Enter Student ID: ");

        int id = readInt();

        // removeIf avoids ConcurrentModificationException
        boolean removed = students.removeIf(
                student -> student.getId() == id
        );

        if (removed) {

            studentIds.remove(id);

            saveStudentsToFile();

            System.out.println(
                    "Student deleted successfully.");

        } else {

            System.out.println(
                    "No record found for ID: " + id);
        }
    }


    // 6. FILE HANDLING - SAVE

    static void saveStudentsToFile() {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(FILE_NAME))
        ) {

            for (Studentobj student : students) {

                writer.write(
                        student.getId()
                                + ","
                                + student.getName()
                                + ","
                                + student.getAge()
                                + ","
                                + student.getCity()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error while saving students: "
                            + e.getMessage());
        }
    }


    // 7. FILE HANDLING - LOAD

    static void loadStudentsFromFile() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            return;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(FILE_NAME))
        ) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length != 4) {
                    continue;
                }

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                int age = Integer.parseInt(data[2]);
                String city = data[3];

                if (!studentIds.contains(id)) {

                    Studentobj student =
                            new Studentobj(
                                    id,
                                    name,
                                    age,
                                    city
                            );

                    students.add(student);
                    studentIds.add(id);
                }
            }

            System.out.println(
                    students.size()
                            + " student(s) loaded from file.");

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error while loading file: "
                            + e.getMessage());
        }
    }


    // 8. INPUT VALIDATION

    static int readInt() {

        while (!sc.hasNextInt()) {

            System.out.println(
                    "Invalid input. Please enter a number.");

            sc.next();
        }

        int value = sc.nextInt();

        sc.nextLine();

        return value;
    }


    static String readString(String message) {

        while (true) {

            System.out.print(message);

            String value = sc.nextLine().trim();

            if (!value.isEmpty()) {

                return value;
            }

            System.out.println(
                    "Input cannot be empty.");
        }
    }
}