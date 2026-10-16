import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.1.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("round01.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

// ---------------------------------------------------------------
// 字节码目标固定为 Java 11。
//
// 为什么必须显式指定：本机 ~/.gradle/gradle.properties 把 Gradle daemon
// 指定到了 JDK 21（org.gradle.java.home=...jbr-21.0.6）。如果不写这一段，
// Kotlin 会跟随 daemon 产出 Java 21 字节码（class 文件 major = 65），
// 而 IDEA 的运行配置用的是 JAVA_HOME（JDK 11，最高只认 major = 55），
// 于是报 UnsupportedClassVersionError / LinkageError。
//
// 固定成 11 之后，无论 Gradle 跑在哪个 JDK 上，产物都能在 JDK 11 上运行。
// ---------------------------------------------------------------
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

// 源码与运行输出统一用 UTF-8，避免 Windows 控制台里的中文乱码
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

// stdout.encoding 是 JDK 18+ 的属性，sun.* 变体供 JDK 11 使用，两个都带上更稳
tasks.withType<JavaExec>().configureEach {
    jvmArgs(
        "-Dfile.encoding=UTF-8",
        "-Dstdout.encoding=UTF-8",
        "-Dstderr.encoding=UTF-8",
        "-Dsun.stdout.encoding=UTF-8",
        "-Dsun.stderr.encoding=UTF-8"
    )
}
