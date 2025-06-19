package br.com.alura.literalura.mainApp;

public class Menu {

    public String showDefaultMenu() {
        var menu = """
                _______________________________________________
                Escolha o número da sua opção
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                0 - Sair
                _______________________________________________
                """;
        return menu;
    }

    public String showSmallMenu() {
        var menu = """
                ____________________________
                Digite o nome do livro que deseja buscar:
                """;
        return menu;
    }

    public String showLanguageMenu() {
        var menu = """
                ____________________________
                Digite um número para escolhar um idioma para busca:
                1. Português
                2. Inglês
                3. Alemão
                """;
        return menu;
    }
}

