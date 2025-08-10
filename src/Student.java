public class Student {
    String name;
    String department;
    double grade;

    // Constructor + Getters
    Student(String name, String department, double grade) {
        this.name = name;
        this.department = department;
        this.grade = grade;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getGrade() { return grade; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', dept='" + department + "', grade=" + grade + "}";
    }
}
