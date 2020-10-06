package Common.Utils

import Data.Constants

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

    Verify<T> is(T value) {
        assertThat(verificationObject).isEqualTo(value)
        return this
    }

    Verify<T> isNot(T value) {
        assertThat(verificationObject).isNotEqualTo(value)
        return this
    }

    Verify<T> contains(T value) {
        assertThat(verificationObject).contains(value)
        return this
    }

    Verify<T> hasSize(int value) {
        assertThat(verificationObject).hasSize(value)
        return this
    }

    Verify<T> isGreaterThan(T value) {
        assertThat(verificationObject).isGreaterThan(value.asType(verificationObject.class))
        return this
    }

    Verify<T> isGreaterThanOrEqualTo(T value) {
        assertThat(verificationObject).isGreaterThanOrEqualTo(value.asType(verificationObject.class))
        return this
    }

    Verify<T> isLessThan(T value) {
        assertThat(verificationObject).isLessThan(value.asType(verificationObject.class))
        return this
    }

    Verify<T> isLessThanOrEqualTo(T value) {
        assertThat(verificationObject).isLessThanOrEqualTo(value.asType(verificationObject.class))
        return this
    }

    Verify<T> isEmpty() {
        assertThat(verificationObject).isEmpty()
        return this
    }

    Verify<T> isNotEmpty() {
        assertThat(verificationObject).isNotEmpty()
        return this
    }

    Verify<T> isNull() {
        assertThat(verificationObject).isNull()
        return this
    }

    Verify<T> isNotNull() {
        assertThat(verificationObject).isNotNull()
        return this
    }

    Verify<T> isFalse() {
        assertThat(verificationObject).isFalse()
        return this
    }

    Verify<T> isTrue() {
        assertThat(verificationObject).isTrue()
        return this
    }

    Verify<T> deepEquals(T value) {
        assertThat(verificationObject)
                .usingRecursiveComparison()
                .isEqualTo(value)
        return this
    }

    Verify<T> matches(String regex) {
        assertThat(verificationObject).matches(regex)
        return this
    }

    Verify<T> isBefore(T value) {
        assertThat(verificationObject).isBefore(value)
        return this
    }

    Verify<T> isBeforeOrEqualTo(T value) {
        assertThat(verificationObject).isBeforeOrEqualTo(value)
        return this
    }

    Verify<T> isAfter(T value) {
        assertThat(verificationObject).isAfter(value)
        return this
    }

    Verify<T> isAfterOrEqualTo(T value) {
        assertThat(verificationObject).isAfterOrEqualTo(value)
        return this
    }

    Verify<T> isBetween(T before, T after) {
        assertThat(verificationObject).isBetween(before, after)
        return this
    }
}
