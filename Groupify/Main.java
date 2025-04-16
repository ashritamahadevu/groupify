import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Invalid grade format in line: " + line);
                    }
                } else {
                    System.err.println("Error: Invalid data format in line: " + line);
                }
            }


        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: dataBravo.txt");
            e.printStackTrace();
        } finally {
            if (fin != null) {
                fin.close();
            }
        }
        groupMaker(new ArrayList<>(studentMap.values()));
    }

    public static void groupMaker(List<Student> studentList) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("\nHow many groups do you want to create?");
        int numberOfGroups = inputScanner.nextInt();

        System.out.println("\nWhat factor do you want to prioritize? (Enter 1 for Project Grade, 2 for Assessment Grade):");
        int priorityFactor = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume the newline

        System.out.println("\nDo you want the groups balanced? (Enter 1 for Yes, 0 for No):");
        int balancedInput = inputScanner.nextInt();
        inputScanner.nextLine(); //consume the extra line

        boolean balanced = (balancedInput == 1);

        if (numberOfGroups <= 0) {
            System.out.println("Please enter a positive number of groups.");
            inputScanner.close();
            return;
        }

        if (priorityFactor == 1) {
            Collections.sort(studentList, (a, b) -> Integer.compare(b.getProjectGrade(), a.getProjectGrade()));
        } else if (priorityFactor == 2) {
            Collections.sort(studentList, (a, b) -> Integer.compare(b.getAssessmentGrade(), a.getAssessmentGrade()));
        } else {
            System.out.println("Invalid priority factor.  Defaulting to original order.");
        }

        List<List<Student>> groups = new ArrayList<>();
        for (int i = 0; i < numberOfGroups; i++) {
            groups.add(new ArrayList<>());
        }

        if (balanced) {
            int groupIndex = 0;
            for (Student student : studentList) {
                groups.get(groupIndex).add(student);
                groupIndex = (groupIndex + 1) % numberOfGroups;
            }
        } else {
             int groupIndex = 0;
             for(Student student: studentList){
                groups.get(groupIndex).add(student);
                groupIndex = (groupIndex + 1) % numberOfGroups;
             }
        }

        System.out.println("\nGroups:");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println("Group " + (i + 1) + ":");
            for (Student student : groups.get(i)) {
                System.out.println("  " + student.getStudentName() + " - Project Grade: " + student.getProjectGrade() + ", Assessment Grade: " + student.getAssessmentGrade());
            }
        }

        inputScanner.close();
    }
}
