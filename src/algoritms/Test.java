package algoritms;

import java.util.Scanner;

public class Test {

    private static String[] mos(int countCity) {

        int x = countCity + 1;
        int listSize = 1;

        for (int i = 1; i < x; i++) listSize *= i;

        String[] paths = new String[listSize];

        for (int i = 0; i < listSize; i++) {

            iteratePath(x, paths, i);

            for (int u = 0; u < i; u++) {

                while (paths[u].equals(paths[i])) {
                    iteratePath(x, paths, i);
                    u = 0;
                }
            }

        }

        return paths;
    }

    private static void iteratePath(int x, String[] at, int i) {
        at[i] = "";
        for (int j = 0; j < x; j++) {
            int tmp = 0;
            while (("" + at[i]).contains(tmp + "")) {
                tmp = (int) Math.floor(Math.random() * x);
            }
            at[i] = at[i] + tmp;
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int countCity = 0;
        double minPath = Double.MAX_VALUE;

        int[] x;
        int[] y;

        double[][] yt;
        double[] max;

        countCity = in.nextInt();

        x = new int[countCity + 1];
        y = new int[countCity + 1];
        max = new double[100];
        yt = new double[countCity + 1][countCity + 1];

        x[0] = y[0] = 0;

        for (int i = 1; i <= countCity; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        for (int i = 0; i <= countCity; i++) {
            System.out.println(i + "-(" + x[i] + ";" + y[i] + ")");
        }

        for (int i = 0; i <= countCity; i++) {
            for (int j = 0; j <= countCity; j++) {
                yt[i][j] = Math.sqrt(Math.abs(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2)));
                System.out.println("// от " + i + " до " + j + " " + yt[i][j]);
                if (yt[i][j] > max[0])
                    max[0] = yt[i][j];
            }
        }

        if (countCity > 0) {
            for (String xy : mos(countCity)) {
                String[] sss = xy.split("");

                double tmp = 0;

                for (int i = 1; i <= countCity; i++) {
                    tmp += yt[Integer.parseInt(sss[i - 1])][Integer.parseInt(sss[i])];
                }

                minPath = (Math.min(minPath, tmp));
                System.out.println(xy + ":" + tmp);
            }

            System.out.println("при этом самый выгодный путь: " + minPath);
        }
    }
}