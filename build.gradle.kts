import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
	kotlin("plugin.jpa") version "1.4.10"
	kotlin("plugin.allopen") version "1.4.10"
	kotlin("plugin.noarg") version "1.4.10"
	kotlin("plugin.serialization") version "1.4.10"
}

group = "com.demo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
}

allOpen {
	annotation("javax.persistence.Entity")
}

noArg {
	annotation("javax.persistence.Entity")
}

dependencies {
	implementation(platform("software.amazon.awssdk:bom:2.15.7"))
	implementation("software.amazon.awssdk:sqs")

	implementation("org.springframework.cloud:spring-cloud-starter-aws-messaging:2.2.4.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis:2.3.4.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")

	implementation("au.com.console:kassava:2.0.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.3")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.3")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0-M1")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	runtimeOnly("mysql:mysql-connector-java")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
