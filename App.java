/*（2）模拟审批流程，具体如下：有对象Data，内有“办事员审核结果”、“科长意见”“处长意见”等3个String型属性。
用A、B、C对象分别模拟办事员、科长、处长，将此对象依次交给A、B、C，填入审核意见。审核意见要求从键盘输入。*/
import java.util.Scanner;

class Data {
    String banyuan = null;  // 办事员审核结果
    String kezhang = null;  // 科长意见
    String chuzhang = null; // 处长意见
}

class A {
    void check(Data data) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("办事员审核结果：");
        data.banyuan = scanner.nextLine();
    }
}

class B {
    void check(Data data) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("科长意见：");
        data.kezhang = scanner.nextLine();
    }
}

class C {
    void check(Data data) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("处长意见：");
        data.chuzhang = scanner.nextLine();
    }
}

class App {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();

        Data data = new Data();
        a.check(data);
        b.check(data);
        c.check(data);

        System.out.println("办事员审核结果：" + data.banyuan);
        System.out.println("科长意见：" + data.kezhang);
        System.out.println("处长意见：" + data.chuzhang);
    }
}

