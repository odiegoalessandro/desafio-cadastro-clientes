package form;

import java.io.*;

public class CreateFormService {
  public void addQuestion(String question){
    try(
      FileWriter fileWriter = new FileWriter("form.txt", true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      FileReader fileReader = new FileReader("form.txt");
      BufferedReader bufferedReader = new BufferedReader(fileReader)
    ){
      String line = bufferedReader.readLine();
      int questionId = Integer.parseInt(line.split(" ")[0]);

      while (line != null) {
        questionId = Integer.parseInt(line.split(" ")[0]);
        line = bufferedReader.readLine();
      }

      String content = questionId + 1 + " - " + question;

      bufferedWriter.newLine();
      bufferedWriter.write(content);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
