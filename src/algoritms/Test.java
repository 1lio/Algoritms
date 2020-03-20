package algoritms;

import java.util.Scanner;

public class Test {
    public static String[] mos(int xx) {
        int x = xx;
        x++;
        int razm = 1;
        String[] at = new String[3];
        if (x > 1) {
            for (int i = 1; i < x; i++) {
                razm *= i;
            }
            at = new String[razm];
            for (int i = 0; i < razm; i++) {

                at[i] = "";
                for (int j = 0; j < x; j++) {
                    int vremgo = 0;
                    while (("" + at[i]).contains(vremgo + "")) {
                        vremgo = (int) Math.floor(Math.random() * x);
                    }
                    at[i] = at[i] + vremgo;
                }

                for (int u = 0; u < i; u++) {
                    while (at[u].equals(at[i])) {
                        at[i] = "";
                        for (int j = 0; j < x; j++) {
                            int vremgo = 0;
                            while (("" + at[i]).contains(vremgo + "")) {
                                vremgo = (int) Math.floor(Math.random() * x);
                            }
                            at[i] = at[i] + vremgo;
                        }
                        u = 0;
                    }
                }

            }
        }
        return at;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = 0;
        double put = 0;
        double minput = 10000;
        double miput = 10000;
        int[] x;
        int[] y;
        double[][] yt;
        double[] max;
        n = in.nextInt();
        x = new int[n + 1];
        y = new int[n + 1];
        max = new double[100];
        yt = new double[n + 1][n + 1];
        x[0] = y[0] = 0;
        for (int i = 1; i <= n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        for (int i = 0; i <= n; i++) {
            put += Math.sqrt(Math.abs(x[i] * x[i] + y[i] * y[i]));
        }
        for (int i = 0; i <= n; i++) {
            System.out.println(i + "-(" + x[i] + ";" + y[i] + ")");
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                yt[i][j] = Math.sqrt(Math.abs(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2)));
                System.out.println("// от " + i + " до " + j + " " + yt[i][j]);
                if (yt[i][j] > max[0])
                    max[0] = yt[i][j];
            }
        }
        if (n > 0) {
            for (String xy : mos(n)) {
                String[] sss = xy.split("");
                miput = 0;
                for (int i = 1; i <= n; i++) {
                    miput += yt[Integer.parseInt(sss[i - 1])][Integer.parseInt(sss[i])];
                }
                minput = (Math.min(minput, miput));
                System.out.println(xy + ":" + miput);
            }

            System.out.println("при этом самый выгодный путь: " + minput);
        }
    }
}