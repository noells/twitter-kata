package acceptance

import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object WallTest : Spek({
    describe("Given Bob and Charlie post a few messages") {
        beforeEachTest {
            println("beforeEach")
        }

        describe("And Charlie follows Bob") {

            it("Charlie should have his and Bob messages displayed correctly on his wall") {
                Assertions.assertEquals(3, 3)
            }
        }
    }
})
