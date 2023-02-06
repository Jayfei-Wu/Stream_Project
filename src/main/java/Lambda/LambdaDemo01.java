package Lambda;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/**
 * @PROJECT_NAME: Stream_Project
 * @PACKAGE_NAME: Lambda
 * @FILE_NAME: LambdaDemo01
 * @Author: Jayfei-Wu
 * @create: 2023-01-30 18:40
 * @DESCRIPTION: TODO
 */
public class LambdaDemo01 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新线程中run方法被执行了");
            }
        }).start();

        new Thread(() -> {
                System.out.println("lambda表达式优化后的新线程中run方法被执行了");
        }).start();


        int i = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(i);

        int j = calculateNum((int left, int right) -> {
            return left + right;
        });
        System.out.println(j);

        int k = calculateNum(((left, right) -> left+right));

        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value%2==0;
            }
        });

        printNum((int value) -> {
            return value%2==0;
        });

        Integer integer = typeConver(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }

        });
        System.out.println(integer);

        Integer integer1 = typeConver((String s) -> {
            return Integer.valueOf(s);
        });
        System.out.println(integer1);

        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });

        foreachArr((int value) -> {
            System.out.println(value);
        });

    }

    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a,b);
    }

    public static void printNum(IntPredicate predicate) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }

    public static <R> R typeConver(Function<String,R> function) {
        String str = "1235";
        R result = function.apply(str);
        return result;
    }

    public static void foreachArr(IntConsumer consumer) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            consumer.accept(i);
        }
    }

}
