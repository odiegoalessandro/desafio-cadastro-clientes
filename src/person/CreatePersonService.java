package person;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatePersonService {
  public Person createPerson(String name, String email, int age, double height){
    return new Person(name, email, age, height);
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
