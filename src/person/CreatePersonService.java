package person;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatePersonService {
  public Person createPerson(String name, String email, int age, double height){
    try{
      emailAlreadyExists(email);
      return new Person(name, email, age, height);
    } catch (InvalidPersonException e) {
      throw new RuntimeException(e);
    }
  }

  public void emailAlreadyExists(String email) throws InvalidPersonException {
    File currentPath = new File("./");
    Pattern pattern = Pattern.compile("(\\d)-([A-Z]+)\\.txt");

    var files = Arrays.stream(Objects.requireNonNull(currentPath.listFiles((dir, name) -> {
      Matcher matcher = pattern.matcher(name);
      return matcher.find();
    })));

    List<String> emails = files.map(file -> {
      try(var reader = new BufferedReader(new FileReader(file))){
        return reader.lines().skip(1).findFirst().orElse(null);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }).toList();

    if(emails.contains(email)){
      throw new InvalidPersonException("Email ja esta cadastrado");
    }
  }

  public void savePerson(Person person){
    File currentPath = new File("./");
    Pattern pattern = Pattern.compile("(\\d)-([A-Z]+)\\.txt");

    var files = Arrays.stream(Objects.requireNonNull(currentPath.listFiles((dir, name) -> {
      Matcher matcher = pattern.matcher(name);
      return matcher.find();
    }))).toList();
    String lastFile = "0";

    if(!files.isEmpty()){
      lastFile = files.getLast().toString().replaceAll("\\.\\\\", "").split("-")[0];
    }

    int lastFileIndex = Integer.parseInt(lastFile)+1;
    System.out.println(lastFileIndex);
    File personDataFile = new File(lastFileIndex + "-" + person.getFilename());

    try(BufferedWriter bufferedPerson = new BufferedWriter(new FileWriter(personDataFile))){
      String content = String.join(
        System.lineSeparator(),
        person.getName(),
        person.getEmail(),
        String.valueOf(person.getAge()),
        String.valueOf(person.getHeight())
      );

      bufferedPerson.write(content);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
