package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.build.event.BuildEventListenerRegistryInternal
import org.gradle.invocation.DefaultGradle

class BuildLifecyclePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val gradle = project.gradle

        val services = (gradle as DefaultGradle).services
        val registry = services[BuildEventListenerRegistryInternal::class.java]

        val operationService = gradle.sharedServices.registerIfAbsent("operationService", BuildOperationService::class.java) {
                gradle.taskGraph.whenReady {
                parameters.targetTaskPaths =
                    this.allTasks
                        .map { it.path }
                        .filter { it.endsWith(":test") }
            }
        }

        registry.subscribe(operationService)
    }
}
