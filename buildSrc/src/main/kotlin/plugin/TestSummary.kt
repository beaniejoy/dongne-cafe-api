package plugin

import org.gradle.api.tasks.testing.TestResult

data class TestSummary(
    val projectName: String? = null,
    val taskName: String? = null,
    val result: TestResult,
    val lines: MutableList<String> = mutableListOf()
) {
    fun add(line: String) {
        lines.add(line)
    }

    fun maxWidth(): Int {
        return lines.maxByOrNull { it.length }?.length ?: 0
    }

    fun toLogList(): List<String> {
        return listOf(
            "${projectName}:${taskName} Test result: ${result.resultType}",
            "Test summary: ${result.testCount} tests, ${result.successfulTestCount} succeeded, ${result.failedTestCount} failed, ${result.skippedTestCount} skipped"
        )
    }
}