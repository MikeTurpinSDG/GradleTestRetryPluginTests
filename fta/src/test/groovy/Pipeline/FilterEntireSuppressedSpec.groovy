package Pipeline

import Common.Annotations.PipelineTests
import spock.lang.Specification

//This entire class should be filtered From testing
class FilterEntireSuppressedSpec  extends Specification {
    @PipelineTests
    def VerifyTestIsSuppressed() {
        expect:
        assert false
    }
}
