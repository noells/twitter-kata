package acceptance

import acceptance.utils.FakeConsole
import com.winterbe.expekt.expect
import kata.twitter.TwitterKata
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object PostsTest : Spek({
    describe("Given the Twitter Kata is running") {
        lateinit var console: FakeConsole
        lateinit var twitter: TwitterKata

        beforeGroup {
            console = FakeConsole()
            twitter = TwitterKata.of(console)
            twitter.run(false)
        }

        describe("Given Alice posts a few messages") {
            val aliceUsername = "Alice"
            lateinit var messages: List<String>
            beforeEachTest {
                messages =
                    listOf("Today is gonna be a good day", "Lewis Hamilton has won his 7th F1 World Championship")

                messages.forEach { message ->
                    println(message)
                    console.fakeReadLine("$aliceUsername -> $message")
                }
            }

            describe("When Bob reads Alice's messages") {
                it("Should obtain all messages by chronological order") {
                    val observableOutput = console.getOutputLines().take(2).toList()
                    console.fakeReadLine("Alice")
                    val consoleOutput = observableOutput.blockingGet()

                    expect(consoleOutput).to.equal(messages)
                }
            }
        }
    }
})
