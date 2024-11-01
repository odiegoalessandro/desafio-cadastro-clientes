package person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
  private final String name;
  private final String email;
  private final int age;
  private final double height;

  public Person(String name, String email, int age, double height) {
    try {
      validateName(name);
      validateAge(age);
      validadeHeight(height);
      validateEmail(email);
    } catch (InvalidPersonException e) {
      throw new RuntimeException(e);
    }

    this.name = name;
    this.email = email;
    this.age = age;
    this.height = height;
  }

  private void validateName(String name) throws InvalidPersonException {
    if(name == null || name.trim().length() < 10){
      throw new InvalidPersonException("Nome deve conter 10 caracteres ou mais");
    }
  }

  private void validateEmail(String email) throws InvalidPersonException {
    Pattern pattern = Pattern.compile("(\\\\w+)@(\\\\D+)+(\\\\.(\\\\D+)+)");
    Matcher matcher = pattern.matcher(email);

    if(!matcher.matches()){
      throw new InvalidPersonException("Email invalido");
    }
  }

  private void validateAge(int age) throws InvalidPersonException {
    if(age < 18){
      throw new InvalidPersonException("É proibido usuarios menores de idade");
    }
  }

  private void validadeHeight(double height) throws InvalidPersonException {
    if(height <= 0){
      throw new InvalidPersonException("Não é possivel cadastrar uma altura negativa");
    }
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
