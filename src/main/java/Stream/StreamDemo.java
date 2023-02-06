package Stream;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @PROJECT_NAME: Stream_Project
 * @PACKAGE_NAME: Stream
 * @FILE_NAME: StreamDemo
 * @Author: Jayfei-Wu
 * @create: 2023-01-31 9:31
 * @DESCRIPTION: TODO
 */
public class StreamDemo {

    public static void main(String[] args) {

        List<Author> authors = getAuthors();
        System.out.println(authors);
        authors
                .stream()   //把集合转换成流
                .distinct()     //先去除重复的作家
                .filter(author -> author.getAge() < 18)   //筛选年龄小于18的
                .forEach(author -> System.out.println(author.getName()));   //遍历打印名字


        List<Author> authorList = getAuthors();
        Stream<Author> stream = authorList.stream();

        Integer[] arr = {1,2,3,4,5};
        Stream<Integer> stream1 = Arrays.stream(arr);
        Stream<Integer> stream2 = Stream.of(arr);

        Map<String,Integer> map = new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("日向",16);

        Stream<Map.Entry<String,Integer>> stream3 = map.entrySet().stream();

        authors.stream()
                .filter( author -> author.getName().length()>1 )
                .forEach(author -> System.out.println(author.getName()));

        authors.stream()
                .map(author -> author.getName())
                .forEach(name -> System.out.println(name));

        authors.stream()
                .map(author -> author.getAge())
                .map(age -> age+10)
                .forEach(age -> System.out.println(age));

        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));

        authors.stream()
                .distinct()
                .sorted()
                .forEach(author -> System.out.println(author.getAge()));

        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()- o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));

        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()- o1.getAge())
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));

        authors.stream()
                .distinct()
                .sorted()
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));

        authors.stream()
                .flatMap( author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));

        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCaregory().split(",")))
                .distinct()
                .forEach(category -> System.out.println(category));

        authors.stream()
                .forEach(author -> System.out.println(author.getName()));

        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);

        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((o1, o2) -> o1 - o2);
        System.out.println(max);

        List<String> stringList = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(stringList);

        Set<Book> bookSet = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toSet());
        System.out.println(bookSet);

        Map<String, List<Book>> listMap = authors.stream()
                .distinct()
                .collect(Collectors.toMap(new Function<Author, String>() {

                    @Override
                    public String apply(Author author) {
                        return author.getName();
                    }
                }, new Function<Author, List<Book>>() {

                    @Override
                    public List<Book> apply(Author author) {
                        return author.getBooks();
                    }
                }));

        Map<String, List<Book>> listMap1 = authors.stream()
                .distinct()
                .collect(Collectors.toMap( author -> author.getName(),author-> author.getBooks()));

        System.out.println(listMap);
        System.out.println(listMap1);

        boolean flag1 = authors.stream()
                .anyMatch(author -> author.getAge()>29);
        System.out.println(flag1);

        boolean flag2 = authors.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(flag2);

        boolean flag3 = authors.stream()
                .noneMatch(author -> author.getAge() > 100);
        System.out.println(flag3);

        Optional<Author> any = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();

        any.ifPresent(author -> System.out.println(author.getName()));

        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));

        Integer sum = authors.stream()
                .map(author -> author.getAge())
                .reduce(0, (result, element) -> result + element);
        System.out.println(sum);

        Integer min1 = authors.stream()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
        System.out.println(min1);
    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风", null);
        Author author3 = new Author(3L, "易", 14, "世界", null);
        Author author4 = new Author(3L, "易", 14, "世界", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧", "哲学,爱情", 88, "用一把刀"));
        books1.add(new Book(2L, "一个人", "个人成长,爱情", 99, "讲述如何"));

        books2.add(new Book(3L, "那风吹不到", "哲学", 85, "带你"));
        books2.add(new Book(3L, "那风吹不到", "哲学", 85, "带你"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家"));

        books3.add(new Book(5L, "你的剑", "爱情", 56, "无法想象"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }
}
