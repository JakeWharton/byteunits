import org.gradle.internal.jvm.Jvm
import ru.vyarus.gradle.plugin.animalsniffer.AnimalSnifferExtension
import ru.vyarus.gradle.plugin.animalsniffer.AnimalSnifferPlugin

plugins {
  alias(libs.plugins.animalsniffer) apply false
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.mavenPublish)
}

kotlin {
  jvm {
    withJava() // Required for Animal Sniffer to work.
  }

  iosX64()
  iosArm64()
  iosSimulatorArm64()

  sourceSets {
    val commonMain by getting

    val commonTest by getting {
      dependencies {
        implementation(libs.kotlin.test.junit)
      }
    }

    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)
    }

    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }
  }
}

tasks.withType(Test::class.java).all {
  testLogging.exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
}

repositories {
  mavenCentral()
}

// Animal Sniffer only works on JDK 11 or older currently.
if (Jvm.current().javaVersion?.isJava12Compatible == false) {
  project.apply<AnimalSnifferPlugin>()

  configure<AnimalSnifferExtension> {
    sourceSets = listOf(project.sourceSets.getByName("main"))
  }

  val signature: Configuration by configurations

  dependencies {
    signature(variantOf(libs.animalSniffer.android) { artifactType("signature") })
    signature(variantOf(libs.animalSniffer.java) { artifactType("signature") })
  }
}
