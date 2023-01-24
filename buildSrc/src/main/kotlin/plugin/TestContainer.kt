package plugin

import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.TestResult.*

class TestContainer {
    companion object {
        var testResults: TestSummary? = null

        const val ANSI_RESET = "\u001B[0m"
        const val ANSI_GREEN = "\u001B[32m"
        const val ANSI_RED = "\u001B[31m"

        fun printTotalResult(summary: TestSummary?) {
            if (summary == null) return

            val maxLength = summary.maxWidth()

            println(
                """
                |┌${"─".repeat(maxLength)}┐
                |${
                    summary.toLogList().joinToString("│\n│", "│", "│") {
                        val coloredResult = colorResultType(summary.result.resultType)
                        val str = "$it${" ".repeat(maxLength - it.length)}"
                            .replace(
                                oldValue = coloredResult.first.toString(),
                                newValue = coloredResult.second
                            )
                        str
                    }
                }
                |└${"─".repeat(maxLength)}┘
                """.trimMargin()
            )
        }

        fun printEachResult(desc: TestDescriptor, result: TestResult) {
            println("[${desc.className}] ${desc.displayName} result: ${colorResultType(result.resultType).second}")
        }

        fun colorResultType(resultType: ResultType): Pair<ResultType, String> {
            val color = when (resultType) {
                ResultType.SUCCESS -> ANSI_GREEN
                ResultType.FAILURE -> ANSI_RED
                else -> ""
            }

            return resultType to if (color.isNotEmpty()) {
                "${color}${resultType}${ANSI_RESET}"
            } else "${resultType}"
        }
    }
}