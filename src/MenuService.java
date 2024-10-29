import form.CreateFormService;
import person.CreatePersonService;
import person.GetPersonService;
import person.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MenuService {
  private final String FORM_PATH = "form.txt";

  public void start() {
    Scanner scanner = new Scanner(System.in);
    int option = 0;

    do {
      System.out.println("\nMenu Principal:");
      System.out.println("1 - Cadastrar usuário");
      System.out.println("2 - Listar todos usuários cadastrados");
      System.out.println("3 - Cadastrar nova pergunta no formulário");
      System.out.println("4 - Deletar pergunta do formulário");
      System.out.println("5 - Pesquisar usuário por nome, idade ou email");
      System.out.println("99 - Sair");

      if (!scanner.hasNextInt()) {
        System.out.println("Resposta inválida. Tente novamente.");
        scanner.next();
        continue;
      }

      option = scanner.nextInt();
      scanner.nextLine();

      switch (option) {
        case 1:
          createPerson();
          break;
        case 2:
          getManyPersons();
          break;
        case 3:
          createQuestions();
          break;
        case 4:
          break;
        case 5:
          break;
        case 99:
          System.out.println("Saindo...");
          break;
        default:
          System.out.println("Opção inválida. Tente novamente.");
          break;
      }

    } while (option != 99);
  }

  public void createPerson() {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FORM_PATH))) {
      CreatePersonService createPersonService = new CreatePersonService();
      Scanner scanner = new Scanner(System.in);
      String currentQuestion;
      StringBuilder responses = new StringBuilder();

      while ((currentQuestion = bufferedReader.readLine()) != null) {
        System.out.println(currentQuestion);
        responses.append(scanner.nextLine()).append(",");
      }

      Scanner personData = new Scanner(responses.toString());
      personData.useDelimiter(",");

      Person person = createPersonService.createPerson(
          personData.next(),
          personData.next(),
          personData.hasNextInt() ? personData.nextInt() : 0,
          personData.hasNextDouble() ? personData.nextDouble() : 0.0
      );

      personData.close();
      createPersonService.savePerson(person);

    } catch (FileNotFoundException e) {
      System.err.println("Arquivo não encontrado: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Erro de entrada/saída: " + e.getMessage());
    }
  }

  public void getManyPersons(){
    GetPersonService getPersonService = new GetPersonService();

    getPersonService.getManyPersons();
  }

  public void createQuestions(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite a questão que você deseja adicionar");
    CreateFormService createFormService = new CreateFormService();

    createFormService.addQuestion(scanner.nextLine());
  }
}
