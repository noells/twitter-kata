package kata.twitter.frontal

import java.util.*


class DefaultConsole: Console {
    private var scanner: Scanner = Scanner(System.`in`)

    override fun readLine(): String {
        print("> ")
        return this.scanner.nextLine()
    }

    override fun writeLine(line: String) {
        println(line)
    }
}