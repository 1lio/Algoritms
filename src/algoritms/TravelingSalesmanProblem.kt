package algoritms

import java.util.*
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

// Задача коммивояжера методом полного перебора

object TravelingSalesmanProblem {

    // Константы
    private val PERMIT_RANGE_COUNT_CITY = 3..8
    private const val MSG_SET_COUNT_CITY = "Введите колличество городов:"
    private val MSG_PERMIT_COUNT_CITY =
        "Max городов от ${PERMIT_RANGE_COUNT_CITY.first} до ${PERMIT_RANGE_COUNT_CITY.last}"

    @JvmStatic
    fun main(args: Array<String>) {

        // Получаем колличество городов
        val countCites = getCountCity()

        // Получаем расстояния между городами
        val pointCites = generateRangeCity(countCites)

        // Считаем расстояние между городами
        val rangeCites = getRangeCities(countCites, pointCites)

        // Находим самый короткий путь
        getMinPath(countCites, rangeCites)
    }

    private fun getCountCity(): Int {
        val cin = Scanner(System.`in`)
        var countCites = 0

        var isCorrectIn = false

        while (!isCorrectIn) {
            println(MSG_SET_COUNT_CITY)
            countCites = cin.nextInt()
            isCorrectIn = when (countCites) {
                in PERMIT_RANGE_COUNT_CITY -> true
                else -> {
                    println(MSG_PERMIT_COUNT_CITY)
                    false
                }
            }
        }

        println()
        return countCites
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

        println()
        return Pair(a, b)
    }

    private fun getRangeCities(countCites: Int, points: Pair<IntArray, IntArray>): Array<IntArray> {

        val range: Array<IntArray> = Array(countCites + 1) { IntArray(countCites + 1) }

        val x = points.first
        val y = points.second

        val max = IntArray(10)

        for (i in 0 until countCites) {
            for (j in 0 until countCites) {

                // Вычисляем расстояние
                range[i][j] = sqrt(
                    abs((x[i] - x[j].toDouble()).pow(2.0) + (y[i] - y[j].toDouble()).pow(2.0))
                ).toInt() // Для облегчения избовляемся от дробей

                if (i != j) println("Расстояние: от ${i + 1} до ${j + 1} = ${range[i][j]}")

                if (range[i][j] > max[0]) max[0] = range[i][j]
            }
        }

        println()
        return range
    }

    private fun getMinPath(countCites: Int, range: Array<IntArray>) {

        // Для наглядности выводим матрицу
        println("Матрица путей:")
        for (r in 0..range.size - 2) {
            for (s in 0..range[r].size - 2) {
                print("${range[r][s]} ")
            }
            println()
        }

        println()
        println("Обработка...")
        println()

        // Все возможные пути
        val iteratePath = iteratePath(countCites)

        println("Возможные варианты обхода:")
        for (x in iteratePath.indices) println(iteratePath[x])
        println()

        // Расстояние / Путь
        val map: MutableMap<String, Int> = mutableMapOf()


        // Идем по возможным путям (132, 123 ..)
        println("Расстояние согласно пути:")

        for (path in iteratePath) {

            var tmp = 0

            for (x in 0 until countCites) {

                val city: Int = path!![x].toString().toInt() - 1 // номер пути (3)
                val nextCity: Int = if (x == countCites - 1) break else path[x + 1].toString().toInt() - 1 // (2)

                // print("${city + 1} ${nextCity + 1}") // (3) -> (2)
                tmp += range[city][nextCity]
            }

            map[path!!] = tmp
            println("$path : $tmp")
        }

        val min = map.minBy { it.value }

        println()
        println("Минимальный путь: ${min!!.key} : расстояние ${min.value}")
    }

    // Функция возвращает массив строк, где в строке в случайном порядке стоят числа, меньшие, чем аргумент.
    // При этом повторения отсеиваются. Это нужно для того, чтобы мы прокладывали путь через все точки без исключения.

    private fun iteratePath(countCity: Int): Array<String?> {

        val x = countCity + 1
        var listSize = 1

        // Находим размерность
        for (i in 1 until x) listSize *= i

        val paths = arrayOfNulls<String>(listSize)

        // Перебераем возможные варианты

        for (i in 0 until listSize) {
            iterate(x, paths, i)
            var u = 0
            while (u < i) {
                while (paths[u] == paths[i]) {
                    iterate(x, paths, i)
                    u = 0
                }
                u++
            }
        }

        // Наводим красоту!!
        fun String.removeCharAt(pos: Int) = this.substring(0, pos) + this.substring(pos + 1)

        val finalPaths = arrayOfNulls<String>(listSize)

        for (i in paths.indices) {
            finalPaths[i] = paths[i]?.trim()
            finalPaths[i] = finalPaths[i]?.removeCharAt(0)
        }

        return finalPaths
    }

    private fun iterate(x: Int, at: Array<String?>, i: Int) {
        at[i] = ""
        for (j in 0 until x) {
            var tmp = 0
            while (("" + at[i]).contains(tmp.toString() + "")) {
                tmp = floor(Math.random() * x).toInt()
            }
            at[i] = at[i] + tmp
        }
    }
}
