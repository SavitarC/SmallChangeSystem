import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmallChangeSystem {
    public PrintWriter out;

    public static void main(String[] args) {
        new SmallChangeSystem().run();
    }

    public void run() {
        try {
            FastScanner in = new FastScanner(System.in);
            out = new PrintWriter(System.out);
            boolean loop = true;
            String choice;
            File detail = new File("detail.txt");
            detail.createNewFile();
            BigDecimal total = BigDecimal.valueOf(0);
            do {
                out.println("----------零钱通菜单----------");
                out.println("        1.零钱通明细");
                out.println("        2.收益入账");
                out.println("        3.消费");
                out.println("        4.退出");

                out.print("请选择(1-4):");
                out.flush();
                choice = in.next();
                switch (choice) {
                    case "1" -> {
                        out.println("1.零钱通明细");
                        out.println("----------零钱通明细----------");
                        detail();
                        out.println();
                    }
                    case "2" -> {
                        out.println("2.收益入账");
                        BigDecimal a = BigDecimal.valueOf(in.nextDouble());
                        total = total.add(a);
                        income(a, total);
                        out.println("入账记录成功");
                    }
                    case "3" -> {
                        out.println("3.消费");

                    }
                    case "4" -> {
                        out.println("4.退出");
                        loop = false;
                    }
                    default -> out.println("菜单选择有误");
                }
            } while (loop);
            out.println("----------已退出零钱通----------");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void detail() throws IOException {
        {
            Reader in = new FileReader("detail.txt");
            BufferedReader br = new BufferedReader(in);
            while (br.ready()) {
                try {
                    String line = br.readLine();
                    out.println(line);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void income(BigDecimal a, BigDecimal total) throws IOException {
        Date date = new Date();
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f = new File("detail.txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fw != null;
        PrintWriter pw = new PrintWriter(fw);
        pw.println("收益入账 +" + a + "  " + time.format(date) + "  余额:" + total);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

