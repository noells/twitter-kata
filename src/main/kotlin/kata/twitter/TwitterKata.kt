package kata.twitter

import kata.twitter.commands.CommandDispatcher
import kata.twitter.commands.CommandExecutor
import kata.twitter.frontal.Console
import kata.twitter.frontal.DefaultConsole

fun main(args: Array<String>) {
    val twitter = TwitterKata.of()
    twitter.run()
}

internal class TwitterKata private constructor(
    private val defaultConsole: Console,
    private val commandDispatcher: CommandDispatcher
) {
    companion object {
        fun of(console: Console = DefaultConsole()): TwitterKata {
            return TwitterKata(console, CommandExecutor())
        }
    }

    fun run() {
        while (true) {
            val userCommand = this.defaultConsole.readLine()
            println("USER ENTERED $userCommand")
            //TODO
            this.commandDispatcher.execute(userCommand)
            Thread.sleep(2000)
        }
    }
}
