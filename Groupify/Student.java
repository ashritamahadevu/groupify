class Student {
    private String name;
    private String learningStyle;
    private int projectGrade;
    private int assessmentGrade;
    private String characteristics;
    private String gender;

    public Student(String name, String learningStyle, int projectGrade, int assessmentGrade, String characteristics, String gender) {
        this.name = name;
        this.learningStyle = learningStyle;
        this.projectGrade = projectGrade;
        this.assessmentGrade = assessmentGrade;
        this.characteristics = characteristics;
        this.gender = gender;
    }

    public String getName() {
        return name;
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

    public String getGender(){
        return gender;
    }
    // Optional: Override toString() for easy printing
    @Override
    public String toString() {
        return "Name: " + name + ", Learning Style: " + learningStyle + ", Project Grade: " + projectGrade +
                ", Assessment Grade: " + assessmentGrade + ", Characteristics: " + characteristics + ", Gender: " + gender;
    }
}