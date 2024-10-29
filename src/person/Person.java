package person;

public class Person {
  private final String name;
  private final String email;
  private final int age;
  private final double height;

  public Person(String name, String email, int age, double height) {
    this.name = name;
    this.email = email;
    this.age = age;
    this.height = height;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public int getAge() {
    return age;
  }

  public double getHeight() {
    return height;
  }

  public String getFilename() {
    return name.toUpperCase().replace(" ", "") + ".txt";
  }
}
