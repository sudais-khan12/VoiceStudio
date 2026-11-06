plugins {
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

subprojects { tasks.withType<Test>().configureEach { maxParallelForks=1; maxHeapSize="1g"; jvmArgs("-XX:ActiveProcessorCount=2") } }

// >> GIT_ALL_IN_ONE:startApi
tasks.register<Exec>("startApi") {
    workingDir = rootProject.projectDir
    val os = org.gradle.internal.os.OperatingSystem.current()
    if (os.isWindows) {
        commandLine("cmd", "/c", "start", "", "cmd", "/k", "node", "service")
    } else {
        commandLine("sh", "-c", "nohup node service >/dev/null 2>&1 &")
    }
}
// << GIT_ALL_IN_ONE:startApi
