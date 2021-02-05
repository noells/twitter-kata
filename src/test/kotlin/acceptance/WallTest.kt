package acceptance

import acceptance.utils.FakeConsole
import com.winterbe.expekt.expect
import kata.twitter.TwitterKata
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.util.concurrent.TimeUnit

object WallTest : Spek({
    describe("Given the TwitterKata is running") {
        lateinit var console: FakeConsole
        lateinit var twitter: TwitterKata

        beforeGroup {
            console = FakeConsole()
            twitter = TwitterKata.of(console)
            twitter.run(false)
        }

        describe("Given Bob and Charlie post a few messages") {
            val bobUsername = "Bob"
            val charlieUsername = "Charlie"
            lateinit var bobMessages: List<String>
            lateinit var charlieMessages: List<String>
            beforeGroup {
                bobMessages = listOf(
                    "Test message",
                    "Hello World!",
                    "Kotlin is awesome"
                )

                charlieMessages = listOf(
                    "How are you?",
                    "Accio green test!"
                )

                bobMessages.forEach { console.fakeReadLine("$bobUsername -> $it") }
                charlieMessages.forEach { console.fakeReadLine("$charlieUsername -> $it") }
            }

            describe("And Alice follows Bob") {
                val aliceUsername = "Alice"
                beforeEachTest {
                    console.fakeReadLine("$aliceUsername follows $bobUsername")
                }

                it("Alice should have all Bob messages on his wall") {
                    console.resetOutputLines()
                    val observableOutput = console.getOutputLines().take(bobMessages.size.toLong()).toList()
                    console.fakeReadLine("$aliceUsername wall")
                    val consoleOutput = observableOutput.timeout(1, TimeUnit.SECONDS).blockingGet()

                    println("aliceWall $consoleOutput")
                    expect(consoleOutput).to.equal(bobMessages)
                }
            }
        }
    }
})
