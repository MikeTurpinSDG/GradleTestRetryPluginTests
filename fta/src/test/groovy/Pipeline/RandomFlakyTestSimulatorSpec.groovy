package Pipeline

import Common.Annotations.PipelineTests
import Common.Utils.Verify
import spock.lang.Shared
import spock.lang.Specification

class RandomFlakyTestSimulatorSpec extends Specification {

    @Shared
    Integer randomNumberValue = null

    private void generateRandomNumberValue() {
        Random rnd = new Random()
        int randomNumberVal = rnd.nextInt(4)
        randomNumberValue = randomNumberVal
        println(randomNumberVal)
    }

    @PipelineTests
    def SimulateFlakyTestEven1() {
        given: "we assume the value is not even"
        Boolean intIsEven = false
        when: "Random number is even"
        if (randomNumberValue == null) {
            generateRandomNumberValue()
        }
        if (randomNumberValue % 2 == 0) {
            intIsEven = true
        }
        then: "Verify that the variables has been set to true"
        Verify.that(intIsEven).isTrue()
    }

    @PipelineTests
    def SimulateFlakyTestOdd1() {
        given: "we assume the value is not odd"
        Boolean intIsOdd = false
        when: "Random number is odd"
        if (randomNumberValue == null) {
            generateRandomNumberValue()
        }
        if (randomNumberValue % 2 != 0) {
            intIsOdd = true
        }
        then: "Verify that the variables has been set to true"
        Verify.that(intIsOdd).isTrue()
    }

    @PipelineTests
    def SimulateFlakyTestEven2() {
        given: "we assume the value is not even"
        Boolean intIsEven = false
        when: "Random number is even"
        if (randomNumberValue == null) {
            generateRandomNumberValue()
        }
        if ((randomNumberValue + 2) % 2 == 0) {
            intIsEven = true
        }
        then: "Verify that the variables has been set to true"
        Verify.that(intIsEven).isTrue()
    }

    @PipelineTests
    def SimulateFlakyTestOdd2() {
        given: "we assume the value is not odd"
        Boolean intIsOdd = false
        when: "Random number is odd"
        if(randomNumberValue == null){
            generateRandomNumberValue()
        }
        if ((randomNumberValue + 2) % 2 != 0) {
            intIsOdd = true
        }
        then: "Verify that the variables has been set to true"
        Verify.that(intIsOdd).isTrue()
    }
}
