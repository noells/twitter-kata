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
            beforeGroup {
                messages =
                    listOf("Today is gonna be a good day", "Lewis Hamilton has won his 7th F1 World Championship")

                messages.forEach { message ->
                    console.fakeReadLine("$aliceUsername -> $message")
                }
            }

            describe("When Bob reads Alice's messages") {
                it("Should obtain all Alice's messages") {
                    val observableOutput = console.getOutputLines().take(2).toList()
                    console.fakeReadLine("Alice")
                    val consoleOutput = observableOutput.blockingGet()

                    expect(consoleOutput).to.equal(messages)
                }
            }

            describe("And she post more messages") {
                lateinit var newMessage: String
                beforeEachTest {
                    console.resetOutputLines()
                    newMessage = "Kotlin rocks!"
                    console.fakeReadLine("$aliceUsername -> $newMessage")
                }
                it("Should obtain the old and the new one") {
                    console.resetOutputLines()
                    val observableOutput = console.getOutputLines().take(3).toList()
                    console.fakeReadLine("Alice")
                    val consoleOutput = observableOutput.blockingGet()

                    expect(consoleOutput).to.equal(messages.plus(newMessage))
                }
            }
        }
        describe("Given Bob posts a few messages") {
            val bobUsername = "Bob"
            lateinit var bobMessages: List<String>
            beforeEachTest {
                console.resetOutputLines()
                bobMessages =
                    listOf("TDD is a must", "Be SOLID, my friend")

                bobMessages.forEach { message ->
                    println(message)
                    console.fakeReadLine("$bobUsername -> $message")
                }
            }

            describe("When someone reads Bob's messages") {
                it("Should obtain all Bob's messages") {
                    val observableOutput = console.getOutputLines().take(2).toList()
                    console.fakeReadLine(bobUsername)
                    val consoleOutput = observableOutput.blockingGet()

                    expect(consoleOutput).to.equal(bobMessages)
                }
            }
        }
    }
})
