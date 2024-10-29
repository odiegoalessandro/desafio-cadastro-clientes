package person;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPersonService {
  public void getManyPersons(){
    File currentPath = new File("./");
    Pattern pattern = Pattern.compile("(\\d)-([A-Z]+)\\.txt");
    List<File> files = Arrays.stream(Objects.requireNonNull(currentPath.listFiles((dir, name) -> {
      Matcher matcher = pattern.matcher(name);
      return matcher.find();
    }))).toList();

    for (int i = 0; files.size() > i; i++) {
      try (
          FileReader fileReader = new FileReader(files.get(i));
          BufferedReader bufferedReader = new BufferedReader(fileReader)
      ) {
        System.out.println(i + " - " + bufferedReader.readLine());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
