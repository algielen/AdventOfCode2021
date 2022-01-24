interface Criteria<T> {
    fun select(zeros: MutableList<T>, ones: MutableList<T>): MutableList<T>
}

public class OxygenCriteria<T> : Criteria<T> {
    override fun select(zeros: MutableList<T>, ones: MutableList<T>): MutableList<T> {
        if (zeros.size > ones.size) {
            return zeros
        } else {
            return ones
        }
    }
}

public class CO2Criteria<T> : Criteria<T> {
    override fun select(zeros: MutableList<T>, ones: MutableList<T>): MutableList<T> {
        // meh
        if (ones.size == 0) {
            return zeros
        } else if (zeros.size == 0) {
            return ones
        }

        if (zeros.size > ones.size) {
            return ones
        } else {
            return zeros
        }
    }
}