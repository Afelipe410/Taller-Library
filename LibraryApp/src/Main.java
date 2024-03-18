import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final String FILE_PATH = "data/Books.csv";
    private Library library = new Library();
    private CSVCoder<Book> booksCoder = null;

    public Book CreateBook(String title, String author, int publicationYear, int numberOfPages, int id) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
        book.setNumberOfPages(numberOfPages);
        book.setId(id);
        return book;

    }

    public Main() {

        booksCoder = new CSVCoder<>(';') {
            @Override
            public String[] encode(Book book) {
                return new String[] {
                        String.valueOf(book.getId()),
                        book.getTitle(),
                        book.getAuthor(),
                };
            };

            @Override
            public Book decode(String[] values) {
                var id = Integer.parseInt(values[0]);
                var title = values[1];
                var author = values[2];

                return new Book(id, title, author);

            };
        };
    }

    public void loadInfo() {
        List<Book> books = new ArrayList<>();
        try {
            booksCoder.readFromFile(FILE_PATH, books);

            library = new Library();

            for (Book book : books) {
                library.addBook(book);
            }
            Console.writeLine();
        } catch (IOException e) {
            Console.writeLine("Error al intentar leer el archivo");
        }
    }

    public void saveInfo() {
        try {
            booksCoder.writeToFile(FILE_PATH, library.getAllBooks());
        } catch (IOException e) {

            Console.writeLine("Error al intentar guardar el archivo");
        }
    }

    public void showMenu() {
        String option = "";

        do {
            Console.writeLine();

            Console.writeLine(" BIENVENIDO AL MENÚ PRINCIPAL ");

            Console.writeLine("1. Agregar un libro a la biblioteca ");

            Console.writeLine("2. Eliminar un libro de la biblioteca ");

            Console.writeLine("3. Prestar un libro ");

            Console.writeLine("4. Devolver un libro un libro ");

            Console.writeLine("5. Mostrar todos los libros disponibles en la biblioteca ");

            Console.writeLine("6. Cargar información ");

            Console.writeLine("7. Guardar información ");

            Console.writeLine("0. Salir");
            Console.writeLine();
            Console.writeLine("Ingrese una opción y presione [ENTER]");
            option = Console.readLine();

            switch (option) {
                case "1": {
                    Console.writeLine("Ingrese el titulo del libro: ");
                    String title = Console.readLine();
                    Console.writeLine("Ingrese el autor del libro: ");
                    String author = Console.readLine();
                    Console.writeLine("Ingrese el año de publicación del libro: ");
                    int publicationYear = (Integer.parseInt(Console.readLine()));
                    Console.writeLine("Ingrese la cantidad de paginas del libro: ");
                    int numberOfPages = (Integer.parseInt(Console.readLine()));
                    Console.writeLine("Ingrese el id del libro: ");
                    int id = (Integer.parseInt(Console.readLine()));
                    Book book = CreateBook(title, author, publicationYear, numberOfPages, id);
                    library.addBook(book);
                    library.unicaId(id);

                    if (library.unicaId(id)) {
                        Console.writeLine("Libro ingresado correctamente");
                    } else {
                        Console.writeLine("Error: Existe un libro con esta ID");
                    }
                    break;
                }

                case "2": {
                    Console.writeLine("Ingrese el ID del libro a eliminar: ");
                    int bookId = Integer.parseInt(Console.readLine());
                    library.removeBook(bookId);
                    break;
                }
                case "3": {
                    Console.writeLine("Ingrese el ID del libro que desea sacar a préstamo: ");
                    int lendBookId = Integer.parseInt(Console.readLine());
                    boolean bookLent = library.lendBook(lendBookId);
                    if (bookLent) {
                        Console.writeLine("Libro prestado correctamente.");
                    } else {
                        Console.writeLine("Error: Este libro ya está en préstamo.");
                    }
                    break;

                }
                case "4": {
                    Console.writeLine("Ingrese el ID del libro que desea devolver: ");
                    int returnBookId = Integer.parseInt(Console.readLine());
                    if (library.returnBook(returnBookId)) {
                        Console.writeLine("Libro devuelto correctamente.");
                    } else {
                        Console.writeLine("No se encontró ningún libro prestado con ese ID.");
                    }

                    break;
                }
                case "5":
                    Console.writeLine("Libros disponibles en la biblioteca:");
                    for (Book book : library.getAllBooks()) {
                        Console.writeLine(book.getTitle() + " de " + book.getAuthor());
                        Console.writeLine(book.getId());
                    }
                    break;

                case "6": {
                    loadInfo();
                }
                    break;

                case "7": {
                    saveInfo();
                }
                    break;

                default:
                    if (!option.matches("[0-7]")) {
                        Console.writeLine(" xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
                        Console.writeLine(" Opción invalida, intentelo de nuevo");
                        Console.writeLine(" xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
                        Console.writeLine();
                    }

            }

        } while (!option.equals("0"));
        Console.writeLine(" Gracias por usar nuestro programa, hasta pronto.");

    }

    public static void main(String[] args) {
        new Main().showMenu();

    }
}
