/*设计存储int型数据的K叉树类KTree，实现如下操作：建表、递归的前/后序遍历、层次遍历
注：可采用如下不同方式实现队列，以强化理解：
1、将顺序型循环队列作为KTree的内部类实现；
2、将顺序型循环队列作为层次遍历方法的内部类实现；
3、将顺序型循环队列设计成存储Object型元素的通用队列，作为普通类实现；
4、将队列实现成链式队列。 */
import java.util.Scanner;
class ReadChar{
    String data; //建树用的字符串
    int pos = 0; //对应字符串中的字符位置
    ReadChar(String x){data = x;}
    char getChar(){
        char x = data.charAt(pos);
        pos ++;
        return x;
    }
}
class KTree{
    static final int k=3;//静态变量只能使用静态成员
    char data;
    KTree[] c=new KTree[k];
    KTree(char c){data=c;}
    KTree(){;}
    static KTree create(ReadChar r){//static修饰的成员属性
        //建树=造根，根有两种情况：空、非空
        char x = r.getChar();
        if(x == '#') return null; //根据数据判断是否为空
        //非空根=造t对象、给t的所有属性赋值
        KTree t = new KTree(x);
   for(int i=0;i<k;i++)
        t.c[i]=create(r);
        return t;
    }
    void pre(){
        System.out.print(this.data + " ");
        for(int i=0;i<k;i++)
        if(c[i]!=null) c[i].pre();
    }
   void post(){
        for(int i=0;i<k;i++)
        if(c[i]!=null) c[i].post();
        System.out.print(this.data + " ");
    }
    void level(){
        class Queue{
            int max=20;
            KTree []a=new KTree[max];
            int f,r;
            boolean isEmpty(){
                return f==r;
            }
            boolean isFull(){
                return (r+1)%max==f;
            }
            void enQueue(KTree x){
                if(isFull()==true){
                max=max+10;
                KTree[]temp=a;
                a=new KTree[max];
                for(int i=0;i<temp.length;i++)
                a[i]=temp[i];
            }
            r=(r+1)%max;
            a[r]=x;
        }
        KTree outQueue(){f=(f+1)%max;return a[f];}
        }
        KTree t=this;
        Queue q=new Queue();
        q.enQueue(t);
        while(q.isEmpty()==false){
            t=q.outQueue();
            System.out.print(t.data+" ");
            for(int i=0;i<t.c.length;i++)
            if(t.c[i]!=null)q.enQueue(t.c[i]);
        }
    }
}
class App{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("\n输入建树序列,#表示null:");
        String s = sc.nextLine();
        ReadChar r = new ReadChar(s);
        KTree t=KTree.create(r);
        System.out.print("\n pre:"); t.pre();
        System.out.print("\n post:"); t.post();
        System.out.print("\n level:");t.level();
    }
}
//AB###C###D###
//AB##D###C#F###G###E###