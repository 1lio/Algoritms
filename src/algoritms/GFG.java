package algoritms;

class GFG {


// Функция для проверки любой комбинации
// элементов массива сумм к k

    static boolean checkSum(int a[], int n, int k) {

        // Отметить переменную, чтобы проверить,
        // сумма существует

        int flag = 0;
        // Рассчитать количество бит
        int range = (1 << n) - 1;

        // Генерируем комбинации, используя биты
        for (int i = 0; i <= range; i++) {
            int x = 0, y = i, sum = 0;

            while (y > 0) {

                // Подсчитать сумму
                if ((y & 1) == 1) sum = sum + a[x];

                x++;

                y = y >> 1;

            }
            // Если сумма найдена, установить флаг в 1
            // и завершаем цикл

            if (sum == k)
                return true;
        }

        return false;

    }


    public static void main(String[] args) {
        int k = 6;
        int a[] = {3, 4, 1, 2};
        int n = a.length;

        if (checkSum(a, n, k))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}