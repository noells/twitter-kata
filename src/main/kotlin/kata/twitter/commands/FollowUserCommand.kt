package kata.twitter.commands

import kata.twitter.core.use.cases.FollowUserUserCase

class FollowUserCommand(private val userFollower: FollowUserUserCase, private val command: String): Command {

    override fun execute() {
        val (follower, followed) = command.split("follow")
        this.userFollower.followUser(follower.trim(), followed.trim())
    }
}