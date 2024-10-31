package form;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class DeleteFormService {
  public void deleteQuestion(int id){
    List<String> questions = new LinkedList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader("form.txt"))){
      String line;

      while((line = reader.readLine()) != null){
        questions.add(line);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    questions.removeIf(question -> question.startsWith(id + " - "));

    try(BufferedWriter writer = new BufferedWriter(new FileWriter("form.txt"))){

      for(String question : questions){
        writer.write(question);

        if(!questions.getLast().equals(question)){
          writer.newLine();
        }
      }
      
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
