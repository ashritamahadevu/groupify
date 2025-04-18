

class Student {
    private String studentName;
    private String learningStyle;
    private int projectGrade;
    private int assessmentGrade;
    private String characteristics;
    private String gender;

    public Student(String studentName, String learningStyle, int projectGrade, int assessmentGrade, String characteristics, String gender) {
        this.studentName = studentName;
        this.learningStyle = learningStyle;
        this.projectGrade = projectGrade;
        this.assessmentGrade = assessmentGrade;
        this.characteristics = characteristics;
        this.gender = gender;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getLearningStyle() {
        return learningStyle;
    }

    public int getProjectGrade() {
        return projectGrade;
    }

    public int getAssessmentGrade() {
        return assessmentGrade;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public String getGender() {
        return gender;
    }

    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' + ", learningStyle='" + learningStyle + '\'' + ", projectGrade=" + projectGrade + ", assessmentGrade=" + assessmentGrade + ", characteristics='" + characteristics + '\'' + ", gender='" + gender + '\'' +   '}';
    }
}
