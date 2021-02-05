package unit.commands

import kata.twitter.commands.FollowUserCommand
import kata.twitter.core.use.cases.FollowUserUserCase
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object FollowUserCommandTest: Spek({
    describe("Given a UserFollower") {
        lateinit var userFollerMock: FollowUserUserCase

        beforeGroup {
            userFollerMock = mock(FollowUserUserCase::class.java)
        }

        it("The command should use properly the follower") {
            val alice = "Alice"
            val bob = "Bob"
            val rawCommand = "$alice follows $bob"

            val followCommand = FollowUserCommand(userFollerMock, rawCommand)
            followCommand.execute()
            Mockito.verify(userFollerMock).followUser(alice, bob)
        }
    }
})