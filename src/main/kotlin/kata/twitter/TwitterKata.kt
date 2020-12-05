package kata.twitter

import io.reactivex.rxjava3.kotlin.subscribeBy
import kata.twitter.commands.CommandDispatcher
import kata.twitter.commands.CommandExecutor
import kata.twitter.frontal.Console
import kata.twitter.frontal.ObservableConsole
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val twitter = TwitterKata.of()
    twitter.run()
}

internal class TwitterKata private constructor(
    private val console: Console,
    private val commandDispatcher: CommandDispatcher
) {
    companion object {
        fun of(console: Console = ObservableConsole()): TwitterKata {
            return TwitterKata(console, CommandExecutor())
        }
    }


    fun run() {
        this.console.getLines()
            .subscribeBy(
                onNext = { println(it) },
                onError = { it.printStackTrace() },
                onComplete = {
                    println("See ya!")
                    exitProcess(0)
                }
            )

        while(true) {
            this.console.readLine()
        }
    }

}
