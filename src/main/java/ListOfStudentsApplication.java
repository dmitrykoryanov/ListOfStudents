import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ListOfStudentsApplication {

    public static void main(String[] args) {

        List<Student> studentList = List.of(
                new Student(1, "Marius Barbulescu"),
                new Student(25, "Christian Stampfli"),
                new Student(315, "Viviana Dwamena")
        );

        new ListOfStudentsApplication().writeToFile(studentList);

        //use this call of method to set age range filter
        //new ListOfStudentsApplication().writeToFile(studentList, 15, 30);
    }

    private void writeToFile(List<Student> students){
        writeToFile(students,0,999);  // as a default age range use range from 0 to 999
    }

    private void writeToFile(List<Student> students, int ageFrom, int ageTo) {

        String fileName = "students.txt";

        try (FileWriter fw = new FileWriter(new File(fileName), StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(fw)) {

            students
                    .stream()
                    .filter(s -> (s.getAge() > ageFrom) && (s.getAge() < ageTo))
                    .forEach(s -> {
                        try {
                            writer.append(s.toString());
                            //writer.append(s.getName() + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Student {

        private int age;

        private String name;

        Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return (this.name + " ".repeat(30)).substring(0,30)+" "+String.format("%3d", this.age)+ "\n";
        }
    }

}
