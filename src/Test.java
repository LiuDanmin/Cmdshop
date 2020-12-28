import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo=true;
        while (bo) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();//阻塞方法
            System.out.println("请输入密码：");
            String password = sc.next();

            //File file = new File("C:\\Users\\Administrator\\IdeaProjects\\ConsoleShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadExcel readExcel = new ReadExcel();//创建对象
            User users[] = readExcel.readExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    System.out.println("登录成功");
                    bo=false;

                    ReadProductExcel readProductExcel=new ReadProductExcel();
                    //Product products[]=readProductExcel.readExcel(inProduct);
                    Product products[] = readProductExcel.getAllProduct(inProduct);
                    for(Product product:products){
                        System.out.println(product.getpId());
                        System.out.println("\t" + product.getpName());
                        System.out.println("\t" + product.getPrice());
                        System.out.println("\t" + product.getpDesc());
                    }
                    System.out.println("请输入商品ID把商品加入购物车");
                    String pId=sc.next();

                    //创建一个购物车数组：存的是商品
                    Product carts[]=new Product[3];

                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}