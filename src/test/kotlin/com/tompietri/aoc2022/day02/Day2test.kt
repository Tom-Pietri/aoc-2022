package com.tompietri.aoc2022.day02

import com.tompietri.utils.readInputWithMultipleLines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2test {
    @Test
    fun `First solution - My input should return 68775`() {
        val input = readInputWithMultipleLines(2022, 2)
        assertThat(day2FirstSolution(input)).isEqualTo(17189)
    }

    @Test
    fun `Second solution - My input should return 1215`() {
        val input = readInputWithMultipleLines(2022, 2)
        assertThat(day2SecondSolution(input)).isEqualTo(13490)
    }
}