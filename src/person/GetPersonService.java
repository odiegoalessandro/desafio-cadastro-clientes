package person;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
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

  public void getPerson(String query){
    File currentPath = new File("./");
    Pattern pattern = Pattern.compile("(\\d)-([A-Z]+)\\.txt");
    List<String> names = new LinkedList<>();
    List<File> files = Arrays.stream(Objects.requireNonNull(currentPath.listFiles((dir, name) -> {
      Matcher matcher = pattern.matcher(name);

      if(matcher.find()){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(name))) {
          names.add(bufferedReader.readLine());
          return true;
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }

      return false;
    }))).toList();

    for (int i = 0; names.size() > i; i++){
      var name = names.get(i);

      if(name.toLowerCase().trim().contains(query)){
        System.out.println(i + " - " + name);
      } else{
        System.out.println("Nenhum nome correspondente a isso");
      }
    }
  }
}
