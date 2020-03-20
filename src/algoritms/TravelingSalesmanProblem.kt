package algoritms

import java.util.*
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

// Задача коммивояжера методом полного перебора

object TravelingSalesmanProblem {

    @JvmStatic
    fun main(args: Array<String>) {

        // Получаем колличество городов
        val countCites = getCountCity()

        printGraphPo(countCites)

        // Получаем расстояния между городами
        //   val pointCites = generateRangeCity(countCites)

        // Считаем расстояние между городами
        //     val rangeCites = getRangeCities(countCites, pointCites)

        // Находим самый короткий путь
        //     getMinPath(countCites, rangeCites)
    }

    private fun getCountCity(): Int {
        val cin = Scanner(System.`in`)

        println("Введите колличество городов:")
        return cin.nextInt()
    }

    private fun generateRangeCity(countCity: Int): Pair<IntArray, IntArray> {

        val a = IntArray(countCity)
        val b = IntArray(countCity)

        for (x in 0 until countCity) {
            a[x] = (0..99).random()
            b[x] = (0..99).random()
        }

        // Выводим получившиеся расстояния
        for (i in 0 until countCity) {
            println("Город ${i + 1} - (${a[i]};${b[i]})")
        }

        return Pair(a, b)
    }

    private fun getRangeCities(countCites: Int, points: Pair<IntArray, IntArray>): Array<DoubleArray> {

        val range: Array<DoubleArray> = Array(countCites + 1) { DoubleArray(countCites + 1) }

        val x = points.first
        val y = points.second

        val max = DoubleArray(100)

        for (i in 0 until countCites) {
            for (j in 0 until countCites) {

                // Вычисляем растояние
                range[i][j] = sqrt(abs((x[i] - x[j].toDouble()).pow(2.0) + (y[i] - y[j].toDouble()).pow(2.0)))

                if (i != j) println("Расстояние от ${i + 1} до ${j + 1} ${range[i][j]}")

                if (range[i][j] > max[0]) max[0] = range[i][j]
            }
        }

        return range
    }

    private fun getMinPath(countCites: Int, range: Array<DoubleArray>) {

        var minPath = 100.0

        // Проходим граф пути
        for (xy in getGraphPoints(countCites)) {

            val sss: Array<String> = xy!!.split(".").toTypedArray()
            var miput = 0.0
            for (i in 1..countCites) {
                miput += range[sss[i - 1].toInt()][sss[i].toInt()]
            }

            minPath = minPath.coerceAtMost(miput)

            println("$xy:$miput")
        }

        println("Самый выгодный путь: $minPath")
    }

    // Функция возвращает массив строк, где в строке в случайном порядке стоят числа, меньшие, чем аргумент.
    // При этом повторения отсеиваются. Это нужно для того, чтобы мы прокладывали путь через все точки без исключения.

    private fun printGraphPo(countCity: Int) {
        val array: Array<String> = getGraphPoints(countCity)

        if (array.isNullOrEmpty()) println("Is null array")
        for (a in array) {
            println(a ?: "null")
        }
    }

    private fun getGraphPoints(countCity: Int): Array<String> {

        var s = ""
        // Создаем строку
        for (x in 0 until countCity)
            s += (x + 1).toString()

        // Кол-во комбинаций
        var comb = 1
        for (x in 1 until countCity) comb *= x

        val result: Array<String?> = arrayOfNulls(comb)
        result[0] = s

        println(result.size)
        println(result)


        return arrayOf()
    }

    /*  fun mos(xx: Int): Array<String?> {

          val x = xx + 1
          var razm = 1

          for (i in 1 until x) razm *= i

          val at: Array<String?> = arrayOfNulls(razm)

          for (i in 0 until razm) {
              at[i] = ""

              for (j in 0 until x) {
                  println("a1 = ${at[0]}")
                  var vremgo = 0
                  while (("" + at[i]).contains(vremgo.toString() + "")) {
                      vremgo = Math.floor(Math.random() * x).toInt()
                      println("a3 = ${at[0]}")
                  }
                  at[i] = at[i] + vremgo
              }

              var u = 0
              while (u < i) {
                  while (at[u] == at[i]) {
                      at[i] = ""
                      for (j in 0 until x) {
                          var vremgo = 0
                          while (("" + at[i]).contains(vremgo.toString() + "")) {
                              vremgo = Math.floor(Math.random() * x).toInt()
                          }
                          at[i] = at[i] + vremgo
                      }
                      u = 0
                  }
                  u++
              }
          }

          return at
      }*/
}