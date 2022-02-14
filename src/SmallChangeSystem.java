import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
            RandomAccessFile raf = new RandomAccessFile("detail.txt", "r");
            long len = raf.length();
            String lastLine = "";
            if (len != 0L) {
                long pos = len - 1;
                while (pos > 0) {
                    pos--;
                    raf.seek(pos);
                    if (raf.readByte() == '\n') {
                        lastLine = raf.readLine();
                        lastLine = new String(lastLine.getBytes("8859_1"), StandardCharsets.UTF_8);
                        break;
                    }
                }
                total = new BigDecimal(lastLine.substring(lastLine.lastIndexOf("余额") + 3));
            }
            raf.close();
            do {
                out.println("----------零钱通菜单----------");
                out.println("        1.零钱通明细");
                out.println("        2.收益入账");
                out.println("        3.消费");
                out.println("        4.清空记录");
                out.println("        5.退出");

                out.print("请选择(1-5):");
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
                        System.out.println("2.收益入账");
                        BigDecimal a = BigDecimal.valueOf(in.nextDouble());
                        int r=a.compareTo(BigDecimal.ZERO);
                        if (r < 0){
                            out.println("数据有误");
                        }else{
                            total = total.add(a);
                            income(a, total);
                            out.println("入账记录成功");
                        }
                    }
                    case "3" -> {
                        System.out.println("3.消费");
                        BigDecimal a = BigDecimal.valueOf(in.nextDouble());
                        int r=a.compareTo(BigDecimal.ZERO);
                        if (r < 0){
                            out.println("数据有误");
                        }else{
                            total = total.subtract(a);
                            outcome(a, total);
                            out.println("消费记录成功");
                        }
                    }
                    case "4" -> {
                        System.out.println("4.清空记录");
                        System.out.println("确认清空记录请输入 y");
                        String ensure=in.next();
                        if (Objects.equals(ensure, "y")){
                            clean(detail);
                            out.println("已清空");
                        }else{
                            out.println("错误，已退出");
                            loop = false;
                        }
                    }
                    case "5" -> {
                        out.println("5.退出");
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

    public void income(BigDecimal a, BigDecimal total) {
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

    public void outcome(BigDecimal a, BigDecimal total) {
        FastScanner in = new FastScanner(System.in);
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
        pw.println(in.next() + " -" + a + "  " + time.format(date) + "  余额:" + total);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clean(File detail){
        try {
            FileWriter fw =new FileWriter(detail);
            fw.write("\n");
            fw.flush();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

