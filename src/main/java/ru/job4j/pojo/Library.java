package ru.job4j.pojo;

public class Library {

    public static void printBook(Book[] arrayBook) {
        for (int i = 0; i < arrayBook.length; i++) {
            Book book = arrayBook[i];
            System.out.println("Книга - " + book.getTitle() + " имеет " + book.getCount() + " страниц");
        }
    }

    public static void main(String[] args) {
        Book[] arrayBook = new Book[4];
        Book firstBook = new Book("first book", 100);
        arrayBook[0] = firstBook;
        Book secondBook = new Book("second book", 156);
        arrayBook[1] = secondBook;
        Book thirdBook = new Book("third book", 58);
        arrayBook[2] = thirdBook;
        Book fourthBook = new Book("Clean code", 998);
        arrayBook[3] = fourthBook;
        printBook(arrayBook);
        System.out.println("");
        Book bookPermutation = new Book();
        bookPermutation = arrayBook[0];
        arrayBook[0] = arrayBook[3];
        arrayBook[3] = bookPermutation;
        printBook(arrayBook);
        System.out.println("");
        for (int i = 0; i < arrayBook.length; i++) {
            Book book = arrayBook[i];
            String titleBook = book.getTitle();
            if (titleBook.equals("Clean code")) {
                System.out.println("Книга c именем \"Clean code\" нашлась под индексом - " + i
                        +  ". Книга имеет - " + book.getCount() + " страниц");
            }
        }
    }
}
