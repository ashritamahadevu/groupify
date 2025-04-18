import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Student> studentMap = new HashMap<>();
        //reads in the file
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("dataBravo.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] infoArray = line.split("\\s+");

                if (infoArray.length == 6) {
                    //each component of the student set as a variable
                    String studentName = infoArray[0];
                    String learningStyle = infoArray[1];
                    int projectGrade = Integer.parseInt(infoArray[2]);
                    int assessmentGrade = Integer.parseInt(infoArray[3]);
                    String characteristics = infoArray[4];
                    String gender = infoArray[5];

                    //creates student object that inputs the chacteristics from the file
                    Student student = new Student(studentName, learningStyle, projectGrade, assessmentGrade, characteristics, gender);
                    studentMap.put(studentName, student);
                } else {
                    System.out.println("Invalid data format in line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }

        List<Student> studentList = new ArrayList<>(studentMap.values());

        createStudentGroups(studentList);
    }
    //this function takes the list of students and creates groups based on what the user inputs
    public static void createStudentGroups(List<Student> students) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("How many groups do you want to create?");
        int numberOfGroups = inputScanner.nextInt();

        System.out.println("Choose a priority for sorting: 1 = Project Grade, 2 = Assessment Grade");
        int sortChoice = inputScanner.nextInt();

        //the list is sorted from lowest to highest based on what the user chose to sort based of
        if (sortChoice == 1) {
            students.sort((a, b) -> b.getProjectGrade() - a.getProjectGrade());
        } else if (sortChoice == 2) {
            students.sort((a, b) -> b.getAssessmentGrade() - a.getAssessmentGrade());
        }
        //creates a list of the groups, each inner list represents one group
        List<List<Student>> groups = new ArrayList<>();
        //each list keeps track of how many of the characteristic is in each group
        List<Map<String, Integer>> genderCountPerGroup = new ArrayList<>();
        List<Map<String, Integer>> styleCountPerGroup = new ArrayList<>();
        List<Map<String, Integer>> charCountPerGroup = new ArrayList<>();

        //creates empty lists for each group and empty maps for each characteristic of each group
        for (int i = 0; i < numberOfGroups; i++) {
            groups.add(new ArrayList<>());
            genderCountPerGroup.add(new HashMap<>());
            styleCountPerGroup.add(new HashMap<>());
            charCountPerGroup.add(new HashMap<>());
        }

        //checks each group to see what is best fit for the student
        for (Student student : students) {
            int bestGroupIndex = 0;
            int smallestScore = Integer.MAX_VALUE;

            //checks to see how common students characteristics are too others alr in that group
            for (int i = 0; i < numberOfGroups; i++) {
                //score is # of students with that characteristic are alr in the group
                int genderScore = genderCountPerGroup.get(i).getOrDefault(student.getGender(), 0);
                int styleScore = styleCountPerGroup.get(i).getOrDefault(student.getLearningStyle(), 0);
                int characteristicScore = charCountPerGroup.get(i).getOrDefault(student.getCharacteristics(), 0);
                //adds up differnt scores to create similarity score
                int totalScore = genderScore + styleScore + characteristicScore;

                //students are added to group with smallest score, where they are more unique
                if (totalScore < smallestScore) {
                    smallestScore = totalScore;
                    bestGroupIndex = i;
                }
            }

            //student is added to that group 
            groups.get(bestGroupIndex).add(student);
            genderCountPerGroup.get(bestGroupIndex).put(student.getGender(), genderCountPerGroup.get(bestGroupIndex).getOrDefault(student.getGender(), 0) + 1);
            styleCountPerGroup.get(bestGroupIndex).put(student.getLearningStyle(), styleCountPerGroup.get(bestGroupIndex).getOrDefault(student.getLearningStyle(), 0) + 1);
            charCountPerGroup.get(bestGroupIndex).put(student.getCharacteristics(), charCountPerGroup.get(bestGroupIndex).getOrDefault(student.getCharacteristics(), 0) + 1);
        }

        System.out.println("\nGenerated Groups:");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println("Group " + (i + 1) + ":");
            for (Student student : groups.get(i)) {
                System.out.println("  " + student.getStudentName() +
                        " - Project: " + student.getProjectGrade() +
                        ", Assesment: " + student.getAssessmentGrade() +
                        ",  " + student.getLearningStyle() +
                        ", " + student.getCharacteristics() +
                        ",  " + student.getGender());
            }
        }

        inputScanner.close();
    }
}
