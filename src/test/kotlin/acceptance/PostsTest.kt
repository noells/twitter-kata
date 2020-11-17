package acceptance

import acceptance.utils.FakeTwitterKata
import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object PostsTest : Spek({
    describe("Given the Twitter Kata is running") {
        lateinit var twitter: FakeTwitterKata

        beforeGroup {
            twitter = FakeTwitterKata()
        }

        describe("Given Alice posts a few messages") {
            val aliceUsername = "Alice"
            lateinit var messages: Array<String>
            beforeEachTest {
                messages =
                    arrayOf("Today is gonna be a good day", "Lewis Hamilton has won his 7th F1 World Championship")

                messages.forEach { message ->
                    println(message)
                    twitter.fakeConsoleCommand("$aliceUsername -> $message")
                }
            }

            describe("When Bob reads Alice's messages") {


                val alicePosts = arrayOf("pepe")
                println(alicePosts)
                Assertions.assertEquals(messages, alicePosts)
            }
        }
    }
})
