plugins { java }

subprojects {
    apply(plugin = "java-library")

    repositories {
        mavenCentral()
        mavenLocal()

        maven(url = "https://jitpack.io")
        maven(url = "https://repo.codemc.io/repository/maven-public/")
        maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven(url = "https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
}

//compileJava.dependsOn clean tasks.build.dependsOn shadowJar