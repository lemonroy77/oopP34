/*（1）结合课堂所学，独立完成班级MIS的设计和编码。*/
import java.util.Scanner;
    class Student{
    int age;String name,ID,birthplace,telephonenum; char xingbie;boolean makeuptest; 
    public static void showHint(){//读取数据时给出的输入格式提示。
         System.out.printf("请输入一组学生信息,以ctrl+Z结束输入:");
         System.out.printf("\n   年龄  姓名     学号  性别       籍贯     有无补考记录  联系方式 ");
         System.out.printf("\n例如18  张三    001     M    江西南昌    true      18070220059\n");
    }
    void read(Scanner sc){//读取数据，并赋值
        age=sc.nextInt();
        name=sc.next();
        ID=sc.next();
        xingbie=sc.next().charAt(0);//读取字符串再读取字符串的第一个首字母
        birthplace=sc.next();
        makeuptest=sc.nextBoolean();
        telephonenum=sc.next();                                                                       
    }
    public String toString(){
		String xb=(xingbie=='F'||xingbie=='f')?"女":
			( (xingbie=='M'||xingbie=='m')?"男":"未知" );
        String makeup="";
        if(makeuptest==true)makeup="有补考记录";else makeup="无补考记录";
        return age+" "+name+" "+ID+" "+xb+" "+makeup+" "+telephonenum+" ";
    }
}
class Banji{
    StudentNode bj=new StudentNode();//相当于班级的表头结点
    int renShu;
    class StudentNode{//创建类
	    Student data;//数据为Student型
	    StudentNode next;
	    StudentNode(){ data=new Student(); }//注意：每新造一个链表结点，结点内的data都要造出一个学生对象，以便后面用read对其赋值。否则，将产生空指针引用错
	    int append(){//有返回值，以便为班级类中的人数赋值
	        Scanner sc=new Scanner(System.in);
	        Student.showHint();//提示
	        StudentNode tail,s;
	        tail=this;  int i=0;
	        while(sc.hasNextInt()==true){
	        	s=new StudentNode(); //造结点
	        	s.data.read(sc);        //赋值
	        	tail.next=s;         //插在表尾
	        	tail=s;              //修改尾部
	        	i++;
	        }
	        return i;
		}
		void show(){
	          for(StudentNode p=this.next;p!=null;p=p.next)
	          	  System.out.println(p.data); //注意：p.data才是Student型，才有对应的toString()
		}
    }
    //下面是班级对外提供的服务
    void append(){ renShu=bj.append(); }
    void show(){
    	bj.show();
    	System.out.print("班级共有 "+renShu+" 人.");
    }
}
class App{
    public static void main(String[] arge){
        Banji bj=new Banji();
        bj.append();
        System.out.printf("班级信息如下：\n");
        bj.show();
    }
}
