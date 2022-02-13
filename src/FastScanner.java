import java.io.*;
import java.util.StringTokenizer;

public class FastScanner {
    public BufferedReader br;
    public StringTokenizer st;


    public FastScanner(InputStream in) {
        br = new BufferedReader(new InputStreamReader(in));
    }

    public FastScanner(String fileName) {
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public boolean hasMoreTokens() {
        while (st == null || !st.hasMoreElements()) {
            String line;
            try {
                line = br.readLine();
            } catch (IOException e) {
                return false;
            }
            if (line == null) {
                return false;
            }
            st = new StringTokenizer(line);
        }
        return true;
    }

    public String next() {
        while (st == null || !st.hasMoreElements()) {
            String line;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new UnknownError();
            }
            if (line == null) {
                throw new UnknownError();
            }
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public int[] nextIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nextInt();
        }
        return ret;
    }

    public long[] nextLongArray(int n) {
        long[] ret = new long[n];
        for (int i = 0; i < n; i++) {
            ret[i] = nextLong();
        }
        return ret;
    }

}