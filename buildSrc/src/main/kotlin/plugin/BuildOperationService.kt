package plugin

import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.tooling.events.FinishEvent
import org.gradle.tooling.events.OperationCompletionListener
import org.gradle.tooling.events.task.TaskFinishEvent

abstract class BuildOperationService : BuildService<BuildServiceParameters.None>, OperationCompletionListener {
    private val targetProjects = listOf("dongne-account-api", "dongne-service-api")
    private val targetTask = "test"

    override fun onFinish(event: FinishEvent?) {
        if (event == null || event !is TaskFinishEvent) {
            return
        }

        if (event.descriptor.taskPath in targetProjects.map { ":$it:$targetTask" }) {
            println("####### onFinish 222 ${event}")
        }
    }
}