import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Test {

    static Scanner sc = new Scanner(System.in);
    /*
    创建一个购物车的数组：存的是商品
    */
    static Product carts[] = new Product[3];

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        boolean bo = true;
        while (bo) {
            System.out.println("请输入用户名：");
            String username = sc.next();//阻塞方法
            System.out.println("请输入密码：");
            String password = sc.next();

            //File file = new File("C:\\Users\\Administrator\\IdeaProjects\\ConsoleShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();//创建对象
            User users[] = readExcel.readExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    System.out.println("登录成功");
                    bo = false;
                    /*
                    显示商品
                     */

                    while(true){
                        System.out.println("继续购买商品请按1");
                        System.out.println("查看购物车请按2");
                        System.out.println("结账请按3");
                        System.out.println("退出请按4");
                        int choose = sc.nextInt();

                        if (choose == 1) {
                            shopping(inProduct);
                        } else if (choose == 2) {
                            /*
                            查看购物车
                             */
                            checkCarts();

                        }
                        else if (choose == 3) {
                            Order order=new Order();
                            order.setUser(users[i]);
                            order.setProduct(carts);

                            /*
                            统计每个商品的数量思想：

                             */

                            }
                        else if(choose == 4){
                            //System.exit(0);
                            break;
                        }
                    }
                        break;
                    } else{
                        System.out.println("登录失败");
                    }
                }
            }
        }

        public static void shopping(InputStream inProduct) throws ClassNotFoundException {
            ReadProductExcel readProductExcel = new ReadProductExcel();
            Product products[] = readProductExcel.getAllProduct(inProduct);
            for (Product product : products) {
                System.out.print(product.getpId());
                System.out.print("\t" + product.getpName());
                System.out.print("\t" + product.getPrice());
                System.out.println("\t" + product.getpDesc());
            }
            System.out.println("请输入商品ID把该商品加入购物车");
            String pId = sc.next();
            int count = 0;
            /*
           根据此ID去Excel中去查找是否有该ID的商品信息，如果有则返回该商品即可
            */
            inProduct = null;
            inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
            Product product = readProductExcel.getProductById(pId, inProduct);
            System.out.println("要购买的商品价格：" + product.getPrice());
            if (product != null) {
                carts[count++] = product;
            }
        }

        public static void checkCarts(){
            for (Product p : carts) {
                if (p != null) {
                    System.out.print(p.getpId());
                    System.out.print("\t" + p.getpName());
                    System.out.print("\t" + p.getPrice());
                    System.out.println("\t" + p.getpDesc());
                }
            }
        }
    }
