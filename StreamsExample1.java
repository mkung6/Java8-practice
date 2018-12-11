/**
Streams: A sequence of elements supporting sequential and parallel aggregate operations.

javadoc for stream(): "Returns a sequential Stream with this collection as its source."
*/

import java.util.*;

public class StreamsExample1 {

  public static class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.age = age;
    }

    public String getFirstName() {
      return firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public int getAge() {
      return age;
    }
  }

  public static void main(String[] args) {

    List<Person> people = Arrays.asList(
      new Person("Charles", "Dickens", 60),
      new Person("Lewis", "Carroll", 42),
      new Person("Thomas", "Carlyle", 51),
      new Person("Charlotte", "Bronte", 45),
      new Person("Matthew", "Arnold", 39)
    );
    /*
    Say you wanted to find each person that starts with 'C' and then print out those names
    One way is to iterate through the list to find them to create an instance, then iterate through to print.

    Another way is to use a stream to iterate through and perform an action on each
    item in the list. You set up the assembly line, then allow the assembly line
    to do the operations.
    */
    System.out.println("First stream example: ");
    //people.stream() will return Stream with type of <Person>

    //a lambda expression that will print each first name in the list
    people.stream()
      .forEach(p -> System.out.println(p.getFirstName()));
    System.out.println("Second stream example: ");
    //Now this will filter and first return a stream consisting of the elements of
    //this stream that match the given predicate (i.e. startsWith("C")).
    //Then take that stream and perform the forEach()
    //If predicate evaluates to false, then the element doesn't "move up" in the
    //assembly line, and doesn't have forEach performed on it.
    people.stream()
      .filter(p -> p.getLastName().startsWith("C"))
      .forEach(p -> System.out.println(p.getFirstName()));
  }
}
