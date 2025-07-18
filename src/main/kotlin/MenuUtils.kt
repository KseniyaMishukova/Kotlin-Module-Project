package main.kotlin
import java.util.Scanner

object MenuUtils {
    private val scanner = Scanner(System.`in`)
    fun showMenu(
        title: String,
        options: List<String>,
        onSelect: (Int) -> Unit
    ) {
        while (true) {
            println("\n$title")
            options.forEachIndexed { index, option -> println("$index. $option") }
            print("Выбери пункт меню: ")
            val input = scanner.nextLine()
            val choice = input.toIntOrNull()
            if (choice == null) {
                println("Ошибся, но где? Введите номер пункта.")
                continue
            }
            if (choice !in options.indices) {
                println("Ошибся, но где? Такого пункта, увы, нет.")
                continue
            }
            onSelect(choice)
            break
        }
    }
    fun readNonEmptyLine(prompt: String): String {
        while (true) {
            print(prompt)
            val input = scanner.nextLine().trim()
            if (input.isEmpty()) {
                println("Ошибся, но где? Поле не может быть пустым.")
            } else {
                return input
            }
        }
    }
}