
public class Student implements Comparable<Student> {

    double gpa;
    
    public Student(double gpa) {
        this.gpa = gpa;
    }

    /**
     *
     * @param s
     * @return
     */
    @Override
    public int compareTo(Student s) {
        if (s instanceof Student) {
            if (this.gpa == ((Student) s).gpa) {
                return 0;
            } else if (this.gpa > ((Student) s).gpa) {
                return 1;
            } else {
                return -1;
            }
        }
        return -1;
    }
    
    @Override
    public String toString() {
        return "" + this.gpa;
    }
}
