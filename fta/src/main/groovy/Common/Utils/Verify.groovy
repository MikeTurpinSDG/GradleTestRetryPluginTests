package Common.Utils


import static org.assertj.core.api.Assertions.assertThat

class Verify<T> {

    private T verificationObject

    static <T> Verify<T> that(T verifyObject) {
        new Verify(verificationObject: verifyObject)
    }

    Verify<T> and(T newObject = verificationObject){
        that(verificationObject:newObject)
        return this
    }

    Verify<T> isTrue() {
        assertThat(verificationObject).isTrue()
        return this
    }
}
