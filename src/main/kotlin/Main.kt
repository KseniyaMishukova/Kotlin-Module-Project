package main.kotlin
fun main() {
    val archives = mutableListOf<Archive>()
    mainMenu(archives)
}

fun mainMenu(archives: MutableList<Archive>) {
    var exit = false
    while (!exit) {
        val options = mutableListOf("Создать личный архив")
        options.addAll(archives.map { it.name })
        options.add("Выход из программы")
        MenuUtils.showMenu("Список архивов:", options) { choice ->
            when (choice) {
                0 -> createArchive(archives)
                options.lastIndex -> {
                    println("Выход тут ->")
                    exit = true
                }
                else -> archiveMenu(archives[choice - 1])
            }
        }
    }
}

fun createArchive(archives: MutableList<Archive>) {
    val name = MenuUtils.readNonEmptyLine("Введи название архива: ")
    archives.add(Archive(name))
    println("Личный архив \"$name\" создан.")
}

fun archiveMenu(archive: Archive) {
    var exit = false
    while (!exit) {
        val options = mutableListOf("Жмём-создаём заметку")
        options.addAll(archive.notes.map { it.title })
        options.add("Назад")
        MenuUtils.showMenu("Архив: ${archive.name}", options) { choice ->
            when (choice) {
                0 -> createNote(archive)
                options.lastIndex -> exit = true
                else -> noteMenu(archive.notes[choice - 1])
            }
        }
    }
}

fun createNote(archive: Archive) {
    val title = MenuUtils.readNonEmptyLine("Введи название заметки: ")
    val content = MenuUtils.readNonEmptyLine("Введи текст заметки: ")
    archive.notes.add(Note(title, content))
    println("Вайвай,заметка \"$title\" создана!")
}

fun noteMenu(note: Note) {
    while (true) {
        println("\nЗаметка: ${note.title}")
        println(note.content)
        println("0. Назад")
        print("Выбери пункт меню: ")
        val input: String? = readLine()
        if (input == "0") return
        println("Ошибся, но где? Такого пункта точно нет.")
    }
}