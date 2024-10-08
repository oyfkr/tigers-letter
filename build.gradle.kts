plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "chat"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// 이메일
	implementation("org.springframework.boot:spring-boot-starter-mail")

	// 타임리프
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf:3.2.5")

	// 스케줄러를 위한 quartz
	implementation("org.springframework.boot:spring-boot-starter-quartz:3.2.4")

	// 로그 디펜던시
	implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

	// postgresql
	implementation("org.postgresql:postgresql:42.7.3")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
