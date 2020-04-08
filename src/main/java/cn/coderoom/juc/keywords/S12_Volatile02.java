package cn.coderoom.juc.keywords;

/**
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 * @package：cn.coderoom.juc.keywords
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S12_Volatile02 {

    private static class Data {
        int a, b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    volatile static Data data;

    public static void main(String[] args) {
        Thread writer = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                data = new Data(i, i);
            }
        });

        Thread reader = new Thread(()->{
            while (data == null) {}
            int x = data.a;
            int y = data.b;
            if(x != y) {
                System.out.printf("a = %s, b=%s%n", x, y);
            }
        });

        reader.start();
        writer.start();

        try {
            reader.join();
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }

}
