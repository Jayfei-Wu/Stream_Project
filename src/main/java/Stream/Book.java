package Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @PROJECT_NAME: Stream_Project
 * @PACKAGE_NAME: Stream
 * @FILE_NAME: Book
 * @Author: Jayfei-Wu
 * @create: 2023-01-31 9:27
 * @DESCRIPTION: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book {

    //id
    private Long id;
    //书名
    private String name;
    //分类
    private String caregory;
    //评分
    private Integer score;
    //简介
    private String intro;
}
