/**
Why Lambdas?
- Enables functional programming
- readable and concisce code/removes boilerplate
- easier to use APIs and libraries
- better support for parallel processing/concurrency

Different from object-oriented programming since we can assign a block of code
to a variable.
*/

public class LambdaExample1 {

  public static void main(String[] args) {
    // aBlockOfCode = public void () {
    //   System.out.print("Hello World!");
    // }
    //Doesn't make sense to declare it as public, since the code is accessible
    //by the variable.
    //The Java compiler is now smart enough to implicitly know the return type, too.

    //if your lambda expression is one line, you can shortcut and don't need curly braces
    // greetingFunction = () -> System.out.print("Hellow World!");

    // you can pass in an inline lambda expression
    // greet(() -> System.out.print("Hello World!"));

    //let's say you need arguments:
    //don't need the "return" keyword in single-line lambda expressions
    // doubleNumberFunction = (int a) -> a * 2;
    // addFunction = (int a, int b) -> a + b;

    // safeDivideFunction = (int a, int b) => a / b;
    // because we want to safely divide, we actually need more than one line of code
    // safeDivideFunction = (int a, int b) -> {
        //   if (b == 0) return 0;
        //   return a / b;
        // };

    // another example of lambda expression:
    // stringLengthCountFunction = (String s) -> s.length();

    // What is the type of your lambda expression? There is no function/method type in Java 8
    // We can create an interface for our lambda expression. Then declare your lambda of type as your interface.
    MyLambda myLambdaFunction = () -> System.out.println("Hello World!");
    // execute lambda expression by calling the interface method on it, just as if it were an instance of a class.
    myLambdaFunction.foo();
    MyAdd addFunction = (int a, int b) -> a + b;

    // Whereas, this anonymous inner class is nearly the same as the lambda above.
    // This is an inline implementation of an interface below. The lambda has better syntactical sugar, although isn't technically a 'shortcut'
    // We can also create a new instance of the Greeting interface, which would be different. Such as:
    // Greeting hellowWorldGreeting = new hellowWorldGreeting();
    Greeting innerClassGreeting = new Greeting() {
      public void perform() {
        System.out.println("Hello World!");
      }
    };
    innerClassGreeting.perform();

    // StringLengthLambda myLambda = (String s) -> s.length();
    // Because of the compiler's type inference, you don't have to specify the type in input arguments
    // StringLengthLambda myLamda = (s) -> s.length();
    // Also, because we only have one input, we can shortcut even further!
    StringLengthLambda myLambda = s -> s.length();
    System.out.println(myLambda.getLength("Hello Lambda"));

    // We can also call a lambda expression as an argument to a method. For example, if we define
    // this method and call it using a lambda expression
    printLambda(s -> s.length());
  }


  public static void printLambda(StringLengthLambda l) {
    System.out.println(l.getLength("Hello Lambda"));
  }

  interface MyLambda {
    // create one method in this interface that has the same signature as your lambda that you are trying to declare.
    // so the signature of our myLambdaFunction is a function that takes in no arguments, and returns void.
    // Naming for interface and methods here don't technically matter. There is no naming convention.
    // Perhaps best to name them according to what they do, for future reference.
    void foo();
  }

  interface MyAdd {
    int add(int x, int y);
  }

  interface StringLengthLambda {
    int getLength(String s);
  }
}
