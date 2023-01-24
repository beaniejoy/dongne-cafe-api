package plugin

import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.tooling.events.FinishEvent
import org.gradle.tooling.events.OperationCompletionListener
import org.gradle.tooling.events.task.TaskFinishEvent

abstract class BuildOperationService : BuildService<BuildOperationService.Params>, OperationCompletionListener {
    interface Params : BuildServiceParameters {
        var lastTaskPath: String
    }

    override fun onFinish(event: FinishEvent?) {
        if (event == null || event !is TaskFinishEvent) {
            return
        }

        if (event.descriptor.taskPath == parameters.lastTaskPath) {
            TestContainer.printTotalResult(TestContainer.testResults)
        }
    }
}