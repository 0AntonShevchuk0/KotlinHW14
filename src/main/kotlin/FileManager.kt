import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class FileManager {
    // Декілька екземплярів класу не потрібно, тому зробимо функції статичними
    companion object {
        fun replaceStringInFile(filePath: String, oldStr: String, newStr: String) {
            // Отримання змісту файлу в 1 рядку
            val newContent = Files.readAllBytes(Paths.get(filePath))
            // Створення потоку запису в файл
            val writer = File("$filePath(Changed)").bufferedWriter()
            // Запис у файл та закриття потоку
            writer.write(String(newContent).replace(oldStr, newStr))
            writer.close()
        }

        fun copyToBakFile(filePath: String, fileName: String) {
            val fileCopyFrom = File(filePath)
            // Зміна шляху до файлу
            val fileCopyTo = File(filePath.replace(filePath.split("[/\\\\]".toRegex()).last(), "$fileName.bak"))
            // Копіювання вмісту файлу з заміною попереднього
            Files.copy(fileCopyFrom.toPath(), fileCopyTo.toPath(), StandardCopyOption.REPLACE_EXISTING)
        }

        fun splitFile(filePath: String) {
            val writerOdd = File("$filePath(Odd)").bufferedWriter()
            val writerEven = File("$filePath(Even)").bufferedWriter()

            // Розподіл рядків
            var counter = 0;
            for (line in File(filePath).readLines()) {
                if (++counter % 2 == 0) {
                    writerOdd.write("$line\n")
                }
                else {
                    writerEven.write("$line\n")
                }
            }
            writerOdd.close()
            writerEven.close()
        }

        fun encryptFile(filePath: String) {
            val newContent = StringBuilder()
            val writer = File("$filePath(Encrypted)").bufferedWriter()
            // Зміна кожного символу в файлі окрім нового рядка
            for (line in File(filePath).readLines()) {
                for (char in line) {
                    newContent.append(char + 3)
                }
                newContent.append('\n')
            }
            writer.write(newContent.toString())
            writer.close()
        }

        fun decryptFile(filePath: String) {
            val newContent = StringBuilder()
            val writer = File("$filePath(Decrypted)").bufferedWriter()
            // Зміна кожного символу в файлі окрім нового рядка
            for (line in File(filePath).readLines()) {
                for (char in line) {
                    newContent.append(char - 3)
                }
                newContent.append('\n')
            }
            writer.write(newContent.toString())
            writer.close()
        }
    }
}