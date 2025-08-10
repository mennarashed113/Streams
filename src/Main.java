import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);

        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);


        List<String> namesLower = Arrays.asList("ali", "mona", "ahmed", "sara", "amr", "laila", "kareem", "nada", "nour", "samy");

        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );


        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );

        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        //------------------------------------------------------------------------------------------
        //🔹 Basic Stream Operations
        //1-Filter even numbers from a list of integers.
        List<Integer> evenNumbers = numbers.stream().filter(n -> n%2 ==0).collect(Collectors.toList());
        System.out.println(evenNumbers);


        //2-Find names starting with a specific letter from a list of strings.
        List<String> namesWithA = names.stream().filter(n -> n!=null && n.startsWith("A")).collect(Collectors.toList());
        System.out.println(namesWithA);


        //3-Convert all strings to uppercase using stream.
        List<String> UpperCaseNames = namesLower.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(UpperCaseNames);


        //4-Sort a list of integers in descending order using streams.
        List<Integer> DescendingOrder = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(DescendingOrder);

        //5-Remove duplicate elements from a list using distinct().
        List<Integer> Distinct = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println(Distinct);



        //----------------------------------------------------------------------------------------
        //🔹 Intermediate Stream Tasks
        //1-Count the number of strings longer than 5 characters.
        long CountStrings = names.stream().filter(s ->s!= null && s.length() > 5).count();
        System.out.println(CountStrings);

        //2-Find the first element in a stream that matches a given condition.
        Optional<Integer> findFirst = numbers.stream().filter(n -> n == 10).findFirst();
        System.out.println(findFirst);

        //3-Check if any number is divisible by 5 in a list.
        Boolean divsBy5 = numbers.stream().anyMatch(n -> n % 5 == 0);
        System.out.println(divsBy5);

        //4-Collect elements into a Set instead of a List.
        Set<Integer> set = numbers.stream().collect(Collectors.toSet());
        System.out.println(set);

        //5-Skip the first 3 elements and return the rest.
        List<String> Skip = names.stream().skip(3).collect(Collectors.toList());
        System.out.println(Skip);

        //---------------------------------------------------------------------------------
        //🔹 Numeric Streams & Reductions
        //1-Calculate the sum of a list of integers using reduce.
        long sum = numbers.stream().reduce(0,Integer::sum);
        System.out.println(sum);

        //2-Find the maximum and minimum value in a list.
        int max = numbers.stream().reduce(0,Integer::max);
        System.out.println(max);
        int min = numbers.stream().reduce(0,Integer::min);
        System.out.println(min);


        //3-Calculate the average of a list of doubles.
        double average = numbers.stream().collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(average);

        //4-Multiply all integers in a list together using reduce.
        int multiply = numbers.stream().reduce(1, (a, b) -> a *b);
        System.out.println(multiply);


        //5-Count how many numbers are positive in a list.
        long countEven = numbers.stream().filter(n -> n > 0).count();
        System.out.println(countEven);



        //---------------------------------------------------------------------------------------------------
        //🔹 Collectors & Grouping
        //1-Group a list of students by their department.
        Map<String,List<Student>> StudentDep = students.stream().collect(Collectors.groupingBy(Student::getDepartment));
        for(Map.Entry<String,List<Student>> entry : StudentDep.entrySet()){
            System.out.println(entry.getValue());
        }


        //2-Partition a list of numbers into even and odd using partitioningBy.
        Map<String, List<Integer>> evenAndOdd = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey() ? "Even" : "Odd",
                        Map.Entry::getValue
                ));

        System.out.println(evenAndOdd);


        //3-Create a comma-separated string from a list of strings.
        String joiner = names.stream().collect(Collectors.joining(", "));
        System.out.println(joiner);


        //4-Group employees by age and count how many per age.
        Map<Integer,Long> employeeGroup= employees.stream().collect(Collectors.groupingBy(Employee::getAge,Collectors.counting()));
        System.out.println(employeeGroup);

        //5-Find the average salary per department in a list of employees.
        Map<String,Double> avgSalary = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalary);



        //-------------------------------------------------------------------------------------------------------------------
        // 🔹 Optional, Map, FlatMap
        //1-Flatten a list of lists into a single list.

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4, 5),
                Arrays.asList(6)
        );

        List<Integer> flatList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(flatList);


        //2-Extract all unique characters from a list of words.
        List<String> words = Arrays.asList("apple", "banana");

        Set<Character> uniqueChars = words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .collect(Collectors.toSet());

        System.out.println(uniqueChars);


        //3-Filter a list of Optionals and collect non-empty values.
        List<Optional<String>> optionals = Arrays.asList(
                Optional.of("Java"),
                Optional.empty(),
                Optional.of("Streams")
        );

        List<String> values = optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        System.out.println(values);


        //4-Map a list of strings to their lengths.
        List<Integer> listOfStrings = namesLower.stream().map(String::length).collect(Collectors.toList());

        System.out.println(listOfStrings);

        //5-Return a list of uppercased words that start with “A”.
        List<String> upperCaseNames = namesLower.stream().filter( n -> n.toUpperCase().startsWith("A")).
                map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(upperCaseNames);



        //----------------------------------------------------------------------------------------------------
        //🔹 Advanced Operations
        //1-Sort a list of employees by salary then by name.
        List<Employee> employees1 = employees.stream().sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName))
                .collect(Collectors.toList());
        System.out.println(employees1);


        //2-Find the second highest number in a list.
        Optional<Integer> secondHighest = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
        System.out.println(secondHighest);

        //3-Find duplicate elements in a list of integers.
        Set<Integer> s = new HashSet<>();
        Set<Integer> duplicates = numbers.stream()
                .filter(n -> !s.add(n))
                .collect(Collectors.toSet());

        System.out.println(duplicates);


        //4-Remove null or empty strings from a list using stream.
        List<String> cleaned = names.stream()
                .filter(Objects::nonNull)
                .filter(s1 -> !s1.isEmpty())
                .collect(Collectors.toList());

        System.out.println(cleaned);


        //5-Partition students into pass/fail groups based on grade.
        Map<String, List<Student>> passFail = students.stream()
                .collect(Collectors.partitioningBy(s2 -> s2.getGrade() >= 50)).entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey() ? "pass" : "fail",
                        Map.Entry::getValue
                ));


        System.out.println(passFail);

    }
}