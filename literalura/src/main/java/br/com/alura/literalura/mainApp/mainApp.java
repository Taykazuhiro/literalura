package br.com.alura.literalura.mainApp;

import br.com.alura.literalura.model.ApiData;
import br.com.alura.literalura.model.AuthorData;
import br.com.alura.literalura.model.BookData;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.service.DataConverter;
import br.com.alura.literalura.service.UsingAPI;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainApp {

    private Scanner reader = new Scanner(System.in);
    private final Menu MENUS = new Menu();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private DataConverter converter = new DataConverter();
    private UsingAPI api = new UsingAPI();

    private List<BookData> savedBooks = new ArrayList<>();
    private AuthorRepository authorRepository;

    public mainApp(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        int option = -1;
        while (option != 0) {
            System.out.println(MENUS.showDefaultMenu());
            try {
                option = Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas o número da opção do menu.");
                continue;
            }

            switch (option) {
                case 1:
                    searchBookAPI();
                    break;
                case 2:
                    showSavedBooks();
                    break;
                case 3:
                    showSavedAuthors();
                    break;
                case 4:
                    //searchAuthorsByYear();
                    break;
                case 5:
                    //searchBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção do menu.");
            }
        }
    }

    private void searchBookAPI() {
            System.out.println(MENUS.showSmallMenu());
            var userBook = reader.nextLine();
            var json = api.APIConnection(ENDERECO + userBook.replace(" ", "%20"));
            ApiData searchBook = converter.getData(json, ApiData.class);

            if (searchBook != null && searchBook.getResults() != null && !searchBook.getResults().isEmpty()) {
                for (BookData book : searchBook.getResults()) {
                    boolean bookAlreadySaved = savedBooks.stream()
                            .anyMatch(saved -> saved.getTitle().equalsIgnoreCase(book.getTitle()));

                    if (!bookAlreadySaved) {
                        System.out.println("\nLivro salvo: " + book.getTitle());
                        System.out.println("Autores:");
                        if (book.getAuthorDataList() != null && !book.getAuthorDataList().isEmpty()) {
                            for (AuthorData author : book.getAuthorDataList()) {
                                System.out.println("- " + author.getName() +
                                        (author.getBirthYear() != null ? " (Nascido em: " + author.getBirthYear() + ")" : "") +
                                        (author.getDeathYear() != null ? " (Falecido em: " + author.getDeathYear() + ")" : ""));
                                authorRepository.save(author);
                            }
                        } else {
                            System.out.println("- Nenhum autor encontrado.");
                        }
                        //savedBooks.add(book);

                    }
                }
            } else {
                System.out.println("Nenhum livro encontrado com esse título.");
            }
        }

    private void showSavedBooks() {
        if (savedBooks.isEmpty()) {
            System.out.println("Nenhum livro salvo até o momento.");
        } else {
            System.out.println("\nLivros salvos:");
            savedBooks.forEach(book -> System.out.println("- " + book.getTitle()));
        }
    }

    private void showSavedAuthors() {
        if (savedBooks.isEmpty()) {
            System.out.println("Nenhum autor registrado, pois ainda não há livros salvos.");
            return;
        }

        System.out.println("\nAutores registrados:");
        savedBooks.stream()
                .flatMap(book -> book.getAuthorDataList().stream())
                .distinct()
                .forEach(author -> System.out.println("- " + author.getName()));
    }
}
