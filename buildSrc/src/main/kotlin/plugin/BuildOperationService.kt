package plugin

import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.tooling.events.FinishEvent
import org.gradle.tooling.events.OperationCompletionListener
import org.gradle.tooling.events.task.TaskFinishEvent
import task.test.TestContainer
import task.test.TestLoggingUtils

abstract class BuildOperationService : BuildService<BuildOperationService.Params>, OperationCompletionListener {
    interface Params : BuildServiceParameters {
        var targetTaskPaths: List<String>
    }

    override fun onFinish(event: FinishEvent?) {
        if (event == null || event !is TaskFinishEvent) {
            return
        }

        if (parameters.targetTaskPaths.contains(event.descriptor.taskPath)) {
            TestLoggingUtils.printTotalResult(TestContainer.testResults)
            TestContainer.testResults = null
        }
    }
}