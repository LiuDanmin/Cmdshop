import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*开始读文件*/
        int k=0;
        File file = new File("C:\\Users\\lenovo\\Desktop\\2020-12-27\\src\\user.xlsx");
        ReadExcel readExcel =new ReadExcel();//创建对象
        User users[]=readExcel.readExcel(file);

        System.out.println("请输入用户名：");
        Scanner sc=new Scanner(System.in);
        String username=sc.next();

        System.out.println("请输入密码：");
        String password=sc.next();
        for(User user:users){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                k=1;
            }
        }
        if(k==1){
            System.out.println("登陆成功");
        }else{
            System.out.println("登录失败");
        }
    }
}
