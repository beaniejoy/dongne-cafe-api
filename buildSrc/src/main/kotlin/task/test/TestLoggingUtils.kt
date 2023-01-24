package task.test

import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestResult

class TestLoggingUtils {
    companion object {
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
                        "$it${" ".repeat(maxLength - it.length)}"
                            .replace(
                                oldValue = coloredResult.first.toString(),
                                newValue = coloredResult.second
                            )
                    }
                }
                |└${"─".repeat(maxLength)}┘
                """.trimMargin()
            )
        }

        fun printEachResult(desc: TestDescriptor, result: TestResult) {
            println("[${desc.className}] ${desc.displayName} >> result: ${colorResultType(result.resultType).second}")
        }

        fun colorResultType(resultType: TestResult.ResultType): Pair<TestResult.ResultType, String> {
            if (TestContainer.colorMode.not()) {
                return resultType to "${resultType}"
            }

            val color = when (resultType) {
                TestResult.ResultType.SUCCESS -> ANSI_GREEN
                TestResult.ResultType.FAILURE -> ANSI_RED
                else -> ""
            }

            return resultType to if (color.isNotEmpty()) {
                "${color}${resultType}$ANSI_RESET"
            } else "${resultType}"
        }
    }
}