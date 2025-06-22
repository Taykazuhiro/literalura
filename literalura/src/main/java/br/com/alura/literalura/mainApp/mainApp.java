package br.com.alura.literalura.mainApp;

import br.com.alura.literalura.DTO.RApiData;
import br.com.alura.literalura.DTO.RAuthorData;
import br.com.alura.literalura.DTO.RBookData;
import br.com.alura.literalura.model.AuthorData;
import br.com.alura.literalura.model.BookData;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BooksRepository;
import br.com.alura.literalura.service.DataConverter;
import br.com.alura.literalura.service.UsingAPI;
import org.springframework.dao.DataIntegrityViolationException;

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
                    searchAuthorsByYear();
                    break;
                case 5:
                    searchBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção do menu.");
            }
        }
    }

    private void searchAuthorsByYear() {
        int year = -1;
        while ( year < 0){
            try{
            System.out.println("Digite ano para busca do autor:");
            year = Integer.parseInt(READER.nextLine());

            if (year < 0 ){
                System.out.println("Ano inválido. Digite valor com 4 digitos maior ou igual a 0.");
            }
        } catch (NumberFormatException e){
                System.out.println("Digite apenas números inteiros!");}
        }
        List<AuthorData> authorListByYear = authorRepository.findAuthorAliveInYear(year);
        if (authorListByYear.isEmpty()){
            System.out.println("Nenhum autor encontrado para o ano buscado.");
        } else {
            System.out.println(authorListByYear);
        }
    }

    private void searchBooksByLanguage() {
        int languageOption = 0;
        System.out.println(MENUS.showLanguageMenu());
        try {
            languageOption = Integer.parseInt(READER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Idioma não suportado pela busca");
        }
        switch (languageOption) {
            case 1:
                searchByLanguage("%pt%");
                break;
            case 2:
                searchByLanguage("%en%");
                break;
            case 3:
                searchByLanguage("%de%");
                break;
            default:
                System.out.println("Opção não suportada");
        }

    }

    private void searchByLanguage(String language) {
        List<BookData> languageSearched = booksRepository.searchBookByLanguage(language);
        if (!languageSearched.isEmpty()) {
            System.out.println("Livros encontrados:" + languageSearched);
        } else {
            System.out.println("Nenhum livro encontrado para o idioma buscado.");
        }
    }


        private void searchBookAPI () {
            RBookData userbook = getBookData();
            if (userbook != null) {
                //pega o primeiro autor da lista de autores da record livros
                try {
                    RAuthorData rAuthorData = userbook.authors().get(0);
                    BookData bookData;
                    AuthorData existedAuthor = authorRepository.findByName(rAuthorData.name());
                    if (existedAuthor != null) {
                        bookData = new BookData(userbook, existedAuthor);

                    } else {
                        AuthorData newAuthor = new AuthorData(rAuthorData);
                        bookData = new BookData(userbook, newAuthor);
                        authorRepository.save(newAuthor);
                    }
                    try {
                        booksRepository.save(bookData);
                        System.out.println("Livro salvo no banco de dados!" + bookData);
                    } catch (DataIntegrityViolationException e) {
                        System.out.println("Livro já está cadastrado no banco:");
                    }
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Gutendex retornou uma busca vazia!");
                }
            } else {
                System.out.println("Nenhum livro encontrado no Gutendex. Realize nova busca!");
            }

        }

        public RBookData getBookData () {
            System.out.println(MENUS.showSmallMenu());
            String userBook = READER.nextLine();
            String json = API.APIConnection(URL + userBook.replace(" ", "%20"));
            //pega a lista de results dentro do json
            RApiData searchBook = CONVERTER.getData(json, RApiData.class);
            //manipula results para se tornar o record de livros
            Optional<RBookData> bookConverted = searchBook.results().stream()
                    .filter(book -> book.title().toUpperCase().contains(userBook.toUpperCase()))
                    .findFirst();

            if (bookConverted.isPresent()) {
                return bookConverted.get();
            } else {
                return null;
            }
        }


        private void showSavedBooks () {
            List<BookData> bookDataList = booksRepository.findAll();
            if (bookDataList.isEmpty()) {
                System.out.println("Nenhum livro registrado.");
            } else {
                System.out.println(bookDataList);

            }
        }

        private void showSavedAuthors () {
            List<AuthorData> authorDataList = authorRepository.findAll();
            if (authorDataList.isEmpty()) {
                System.out.println("Nenhum autor registrado.");
            } else {
                System.out.println(authorDataList);
            }
        }
    }

