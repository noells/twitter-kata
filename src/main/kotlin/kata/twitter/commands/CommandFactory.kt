package kata.twitter.commands

import kata.twitter.core.use.cases.CreatePostUseCase
import kata.twitter.core.use.cases.FollowUserUserCase
import kata.twitter.core.use.cases.ReadPostsUseCase
import kata.twitter.core.use.cases.WallUseCase

class CommandFactory(
    private val postUseCase: CreatePostUseCase,
    private val readPostsUseCase: ReadPostsUseCase,
    private val followUserUserCase: FollowUserUserCase,
    private val wallUseCase: WallUseCase
) {

    fun from(command: String): Command {
        return when {
            command.contains("->") -> CreatePostCommand()
            command.contains("follow") -> FollowUserCommand()
            command.contains("wall") -> WallCommand()
            else -> ReadPostCommnad()
        }
    }
}