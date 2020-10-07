package Pipeline

import Common.Annotations.PipelineTests
import Common.Utils.Verify
import groovy.util.logging.Slf4j
import org.spockframework.runtime.model.FeatureInfo
import spock.lang.Shared
import spock.lang.Specification

@Slf4j
class RandomFlakyTestSimulatorSpec extends Specification {

    @Shared
    Integer randomNumberValue = null
    @Shared
    boolean forceSecondSuccess = true
    @Shared
    boolean forceThirdFailure = true
    @Shared
    boolean finalTestSuccessful = false

    private void generateRandomNumberValue() {
        Random rnd = new Random()
        int randomNumberVal = rnd.nextInt(4)
        randomNumberValue = randomNumberVal
        println(randomNumberVal)
    }

    @PipelineTests
    def FlakyConfirmNumberOfSuppressedFeaturesInThisSpec() {

        int numberOfExcludedTests = 0
        for (FeatureInfo featureInfo : specificationContext.currentSpec.getAllFeatures()) {
            if (featureInfo.isSkipped())
                numberOfExcludedTests++
        }
        expect:
        assert numberOfExcludedTests == 0
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
