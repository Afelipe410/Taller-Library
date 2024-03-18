import java.util.ArrayList;
import java.util.List;

class Library {
    private List<Book> availableBooks;
    private List<Book> borrowedBooks;

    public Library() {
        this.availableBooks = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();
    }

    public boolean unicaId(int id) {
        for (Book existingBook : availableBooks) {
            if (existingBook.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public void addBook(Book book) {
        if (unicaId(book.getId())) {
            availableBooks.add(book);
        }

    }

    public void removeBook(int id) {
        for (Book book : availableBooks) {
            if (book.getId() == id) {
                availableBooks.remove(book);
                borrowedBooks.add(book);
                break;
            }
        }
    }

    public boolean lendBook(int id) {
        for (int i = 0; i < availableBooks.size(); i++) {
            if (availableBooks.get(i).getId() == id) {

                Book book = availableBooks.remove(i);
                borrowedBooks.add(book);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(int id) {
        for (int i = 0; i < borrowedBooks.size(); i++) {
            if (borrowedBooks.get(i).getId() == id) {
                Book book = borrowedBooks.remove(i);
                availableBooks.add(book);
                return true;
            }
        }
        return false;
    }

    public List<Book> getAllBooks() {
        return availableBooks;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
