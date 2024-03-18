public class Book {

    private String title = "";
    private String author = "";
    private int bookLend = 0;
    private int publicationYear = 0;
    private int numberOfPages = 0;
    private int id = 0;

    public Book (int id2, String title2, String author2) {
        id = id2;
        title = title2;
        author = author2;
    }

    public Book() {
        
    }

    public void setTitle(String newTitle) {

        title = newTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String newAuthor) {

        author = newAuthor;
    }

    public String getAuthor() {
        return author;
    }

    public void setPublicationYear(int newPublicationYear) {
        publicationYear = newPublicationYear;
    }

    public int getpublicationYear() {
        return publicationYear;
    }

    public void setNumberOfPages(int newNumberOfPages) {

        numberOfPages = newNumberOfPages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setId(int newId) {

        id = newId;
    }

    public int getId() {
        return id;
    }

    public void setBookLend(int newBookLend) {

        bookLend = newBookLend;
    }

    public int getBookLend() {
        return bookLend;
    }

}
