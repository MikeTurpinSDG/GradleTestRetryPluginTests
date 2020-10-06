package Extensions.global


import groovy.util.logging.Slf4j
import org.spockframework.runtime.extension.AbstractGlobalExtension
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

import java.util.regex.Pattern

@Slf4j
class BuildPipelineTestSuppression extends AbstractGlobalExtension {

    private listOfSuppressedTests = null
    private Properties pipelineProperties = null

    @Override
    void start() {
        //Static list is used as stand in for API call to get list of tests to suppress
       listOfSuppressedTests = new ArrayList<String>()
        listOfSuppressedTests.add("Pipeline.FilterSuppressedTestsSpec.ConfirmTestIsSuppressedForAllBranchesEnvironmentsUsingStar")
        listOfSuppressedTests.add("Pipeline.FilterSuppressedTestsSpec.VerifyTestIsSupressed")
        listOfSuppressedTests.add("Pipeline.FilterSuppressedTestsSpec.ConfirmTestIsSuppressedForAllBranchesEnvironmentsUsingDefaults")
        listOfSuppressedTests.add("Pipeline.FilterEntireSuppressedSpec")

    }

    @Override
    void visitSpec(SpecInfo spec) {
        if (System.getProperty('forceAllTests') == null || !(System.getProperty('forceAllTests').toBoolean())) {
            if (spec) {
                String packageName = spec.getPackage()
                String specName = spec.getName()
                String specFullName = packageName + "." + specName
                for (Pattern testSpecPattern : listOfSuppressedTests) {
                    if (specFullName =~ testSpecPattern) {
                        spec.skip("Identified as a build pipeline suppressed Spec")
                        break;
                    }
                }
                def featureList = spec.getAllFeatures()
                if (!spec.isSkipped()) {
                    for (FeatureInfo featureInfo : featureList) {
                        String methodName = featureInfo.getFeatureMethod().name
                        String fullFeatureName = specFullName + "." + methodName
                        for (Pattern testSpecPattern : listOfSuppressedTests) {
                            if (fullFeatureName =~ testSpecPattern) {
                                featureInfo.skip("Identified as a build pipeline suppressed test")
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
