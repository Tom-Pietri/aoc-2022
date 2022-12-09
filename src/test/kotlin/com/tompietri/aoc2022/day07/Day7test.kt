package com.tompietri.aoc2022.day07

import com.tompietri.utils.readInputWithMultipleLines
import com.tompietri.utils.readInputWithSingleLine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day7test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 7)
        assertThat(day7FirstSolution(input)).isEqualTo(2031851)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 7)
        assertThat(day7SecondSolution(input)).isEqualTo(2568781)
    }
}