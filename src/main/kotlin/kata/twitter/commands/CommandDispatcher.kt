package kata.twitter.commands

interface CommandDispatcher {
    fun execute(command: String)
}