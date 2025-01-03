plugins {
	kotlin("jvm") version "1.9.0"
}

group = "ru.zentsova"
version = "1.0-SNAPSHOT"
val junitversion = "5.11.4"

repositories {
	mavenCentral()
}

dependencies {
	testImplementation(kotlin("test"))
	testImplementation("org.junit.jupiter:junit-jupiter-api:$junitversion")
	testImplementation("org.assertj:assertj-core:3.27.0")
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
  jvmToolchain(8)
}