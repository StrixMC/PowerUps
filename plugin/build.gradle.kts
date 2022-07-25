import org.apache.tools.ant.filters.ReplaceTokens;

val libsPackage = property("libsPackage") as String

plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

dependencies {
    api(project(":api"))
    compileOnly("me.clip:placeholderapi:2.10.10")
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
    compileOnly("com.github.decentsoftware-eu:decentholograms:2.5.1")
    compileOnly("com.gmail.filoghost.holographicdisplays:holographicdisplays-api:2.4.9")

    implementation("com.github.StrixMC:ACID:1.2.0")
    implementation("com.github.Carleslc.Simple-YAML:Simple-Yaml:1.8")
    implementation("me.fixeddev:commandflow-bukkit:0.5.2")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        archiveFileName.set("PowerUps-${project.version}.jar")

        destinationDirectory.set(file("$rootDir/bin/"))

        minimize()

        relocate("org.simpleyaml", "${libsPackage}.simpleyaml")
        relocate("org.yaml.snakeyaml", "${libsPackage}.snakeyaml")
    }
    processResources {
        filesMatching("**/*.yml") {
            filter<ReplaceTokens>(
                "tokens" to mapOf("version" to project.version)
            )
        }
    }
}
