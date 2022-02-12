import java.io.*;

public class SmallChangeSystem {
    public static void main(String[] args) throws IOException {
        FastScanner in=new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        boolean loop=true;
        String choice;
        File detail = new File("detail.txt");
        detail.createNewFile();
        do {
            out.println("----------零钱通菜单----------");
            out.println("          1.零钱通明细");
            out.println("          2.收益入账");
            out.println("          3.消费");
            out.println("          4.退出");

            out.print("请选择(1-4):");
            out.flush();
            choice=in.next();
            switch (choice) {
                case "1" -> {
                    out.println("1.零钱通明细");
                    detail();
                }
                case "2" -> out.println("2.收益入账");
                case "3" -> out.println("3.消费");
                case "4" -> {
                    out.println("4.退出");
                    loop = false;
                }
                default -> out.println("菜单选择有误");
            }
        }while (loop);
        out.println("----------已退出零钱通----------");
        out.close();
    }
    public static void detail() throws FileNotFoundException {
        File detail = new File("detail.txt");
        FileReader fr = new FileReader(detail);
        BufferedReader br=new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String str = null;
        while (true) {
            try {
                if ((str = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(str);
        }
    }
}

