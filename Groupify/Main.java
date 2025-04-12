import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Student> studentMap = new HashMap<>();
        Scanner fin = null;

        try {
            File file = new File("dataBravo.txt");
            fin = new Scanner(file);

            while (fin.hasNextLine()) {
                String line = fin.nextLine();
                String[] factor = line.split("\\s+");

                if (factor.length == 6) {
                    String studentName = factor[0];
                    String learningStyle = factor[1];
                    try {
                        int projectGrade = Integer.parseInt(factor[2]);
                        int assessmentGrade = Integer.parseInt(factor[3]);
                        String characteristics = factor[4];
                        String gender = factor[5];
                        Student student = new Student(studentName, learningStyle, projectGrade, assessmentGrade, characteristics, gender);
                        studentMap.put(studentName, student);
                        System.out.println("Added student: " + student);
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Invalid grade format for student: " + studentName + " in line: " + line);
                    }
                } else {
                    System.err.println("Error: Invalid data format in line: " + line);
                }
            }

            System.out.println("\nStudent Data:");
            for (Student student : studentMap.values()) {
                System.out.println(student);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: dataBravo.txt");
            e.printStackTrace();
        } finally {
            if (fin != null) {
                fin.close();
            }
        }

    }
}