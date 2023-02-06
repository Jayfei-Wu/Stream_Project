package Optional;

import Stream.Author;
import Stream.Book;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @PROJECT_NAME: Stream_Project
 * @PACKAGE_NAME: Optional
 * @FILE_NAME: OptionalDemo
 * @Author: Jayfei-Wu
 * @create: 2023-02-03 0:05
 * @DESCRIPTION: TODO
 */
public class OptionalDemo {

    public static void main(String[] args) {

        Author author = getAuthor();
        Optional<Author> authorOptional = Optional.ofNullable(author);

        authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));

        Optional<Author> authorOptional2 = getAuthorOptional();
        authorOptional2.ifPresent(author2 -> System.out.println(author2.getName()));

        Author author1 = authorOptional2.orElseGet(() -> new Author());
        System.out.println(author1);

        try {
            Author author2 = authorOptional2.orElseThrow(() -> new RuntimeException("数据为null"));

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        authorOptional.filter(author3 -> author3.getAge() > 18).ifPresent(author3 -> System.out.println(author3.getName()));

        Optional<List<Book>> books = authorOptional.map(authorNew -> authorNew.getBooks());
        books.ifPresent(books1 -> books1.forEach(book -> System.out.println(book.getName())));

        
    }

    public static Author getAuthor() {
        Author author = new Author(1L,"蒙多",33,"一个",null);
        return author;
    }

    public static Optional<Author> getAuthorOptional() {
        Author author = new Author(1L,"蒙多",33,"一个",null);
        return Optional.ofNullable(author);
    }
}
