import java.lang.Exception

// Початок програми
fun main(args: Array<String>) {
    // Введення користувачем шляху до файлу
    print("Enter file path:")
    val filePath = readln()
    // Перевірка помилок
    try {
        // Заміна рядків у файлі
        FileManager.replaceStringInFile(filePath, "hi", "hello")
        // Копіювання файлу
        print("Enter .bak file name:")
        val fileName = readln()
        FileManager.copyToBakFile(filePath, fileName)
        // Розділення файлу на 2
        FileManager.splitFile(filePath)
        // Шифрування
        FileManager.encryptFile(filePath)
        // Розшифрування
        FileManager.decryptFile("$filePath(Encrypted)")
    }
    catch (e: Exception) {
        println(e)
    }
}