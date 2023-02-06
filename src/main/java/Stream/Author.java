package Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.List;

/**
 * @PROJECT_NAME: Stream_Project
 * @PACKAGE_NAME: Stream
 * @FILE_NAME: Author
 * @Author: Jayfei-Wu
 * @create: 2023-01-31 9:24
 * @DESCRIPTION: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Author implements Comparable<Author> {

    //id
    private Long id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //简介
    private String intro;
    //作品
    private List<Book> books;


    @Override
    public int compareTo(Author o) {
        return o.getAge()-this.getAge();
    }
}
