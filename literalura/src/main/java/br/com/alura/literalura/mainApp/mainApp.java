package br.com.alura.literalura.mainApp;

import br.com.alura.literalura.model.ApiData;
import br.com.alura.literalura.model.AuthorData;
import br.com.alura.literalura.model.BookData;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BooksRepository;
import br.com.alura.literalura.service.DataConverter;
import br.com.alura.literalura.service.UsingAPI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class mainApp {

    private final Scanner READER = new Scanner(System.in);
    private final Menu MENUS = new Menu();
    private final String URL = "https://gutendex.com/books/?search=";
    private final DataConverter CONVERTER = new DataConverter();
    private final UsingAPI API = new UsingAPI();

    private List<BookData> savedBooks = new ArrayList<>();
    private AuthorRepository authorRepository;
    private BooksRepository booksRepository;

    public mainApp(AuthorRepository authorRepository, BooksRepository booksRepository) {
        this.authorRepository = authorRepository;
        this.booksRepository = booksRepository;
    }

    public void showMenu() {
        int option = -1;
        while (option != 0) {
            System.out.println(MENUS.showDefaultMenu());
            try {
                option = Integer.parseInt(READER.nextLine());
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
        var userBook = READER.nextLine();
        var json = API.APIConnection(URL + userBook.replace(" ", "%20"));

        ApiData apiData = CONVERTER.getData(json, ApiData.class);

        if (apiData.getResults() != null && !apiData.getResults().isEmpty()) {
            BookData book = apiData.getResults().get(0);
            System.out.println(book);

            Optional<BookData> existingBook = booksRepository.findByTitle(book.getTitle());
            if (existingBook.isPresent()) {
                System.out.println("Livro já foi registrado");
            } else {
                booksRepository.save(book);
                System.out.println("Livro salvo com sucesso");
            }
        } else {
            System.out.println("Nenhum livro encontrado.");
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

//        System.out.println("\nAutores registrados:");
//        savedBooks.stream()
//                .flatMap(book -> book.getAuthor())
//                .distinct()
//                .forEach(author -> System.out.println("- " + author.getName()));
    }
}
