package kata.twitter.commands

class CommandExecutor(private val commandFactory: CommandFactory): CommandDispatcher {

    override fun execute(command: String) {
        this.commandFactory.from(command).execute()
    }
}