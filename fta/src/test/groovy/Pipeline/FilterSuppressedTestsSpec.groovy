package Pipeline

import Common.Annotations.PipelineTests
import groovy.util.logging.Slf4j
import org.spockframework.runtime.model.FeatureInfo
import spock.lang.Shared
import spock.lang.Specification

@Slf4j
class FilterSuppressedTestsSpec extends Specification {

    @Shared
    boolean verifyTestWasRun = false
    @Shared
    boolean verifyTestSuppressed = true

    @PipelineTests
    def VerifyTestIsSupressed() {
        verifyTestSuppressed = false
        //This test should be ignored via the pipeline suppression configuration with explicit all values
        expect:
        assert false
    }

    @PipelineTests
    def ConfirmVerifyTestIsSupressed() {
        //This test should be ignored via the pipeline suppression configuration with explicit all values
        expect:
        assert verifyTestSuppressed
    }

    @PipelineTests
    def VerifyTestIsNotSupressed() {
        verifyTestWasRun = true
        expect:
        assert true
    }

    @PipelineTests
    def ConfirmTestWasNotSuppressed() {
        expect:
        assert verifyTestWasRun
    }

    @PipelineTests
    def ConfirmTestIsSuppressedForAllBranchesEnvironmentsUsingDefaults() {
        expect:
        assert false
    }

    @PipelineTests
    def ConfirmTestIsSuppressedForAllBranchesEnvironmentsUsingStar() {
        expect:
        assert false
    }

    @PipelineTests
    def ConfirmNumberOfSuppressedFeaturesInThisSpec() {
        int numberOfExcludedTests = 0
        for(FeatureInfo featureInfo : specificationContext.currentSpec.getAllFeatures()){
            if(featureInfo.isSkipped())
                numberOfExcludedTests++
        }
        expect:
        assert numberOfExcludedTests == 3
    }
}