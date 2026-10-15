# DSH-AtomicKotlin

Atomic Kotlin 学习与练习工作区（DeepSeek Harness 工作目录）。

- 书籍官网：<https://www.atomickotlin.com/>
- 官方课程仓库（练习与解答来源）：<https://github.com/svtk/AtomicKotlinCourse>

## 环境

- Kotlin **2.1.0** / Gradle **8.9**（wrapper 已就位）
- JDK 11（Launcher JVM）；Gradle daemon 走本机 `~/.gradle/gradle.properties` 里配置的 JDK 21

## 目录结构

```
DSH-AtomicKotlin/
├── build.gradle.kts / settings.gradle.kts
├── gradlew / gradlew.bat / gradle/wrapper/
├── run-quick.bat                 # 切到 UTF-8 代码页后运行（避免中文乱码）
├── exercises/
│   └── round-01.md               # Round 01 题目规格（28 道题 + 4 个综合项目）
└── src/main/kotlin/
    ├── util/Check.kt             # 自测小工具（不用改）
    └── round01/
        ├── Main.kt               # 自测入口，实现一题就取消一条注释
        ├── Ex01..Ex28*.kt        # 逐题空壳，函数体为 TODO()
        ├── ext/numberx/          # 第 25 题：跨包 import 实验
        ├── external/Product.kt   # 第 27 题：视为不可修改的"第三方类"
        └── ProjectP1..P4*.kt     # 4 个综合项目空壳
```

## 怎么用

```bat
:: 编译
gradlew.bat --offline compileKotlin

:: 运行自测入口（推荐用 run-quick.bat，它会先切 UTF-8 代码页）
run-quick.bat
```

在 IDEA 里直接打开本目录（Gradle 项目），然后运行 `round01/Main.kt` 即可。

## 学习流程

1. 读 `exercises/round-01.md`，按题目要求实现 `src/main/kotlin/round01/` 里对应的 `TODO()`。
2. 每实现一题，就在 `Main.kt` 里取消对应断言，用 `Check` 工具自测。
3. 完成后告诉 DSH，它会真实编译并运行你的代码，逐题 review，给出代码与学习建议。
4. 根据 review 暴露的问题，生成下一轮（Round 02）练习。

## 约定

- 练习题严格限定在**已学 atom** 范围内，`exercises/round-01.md` 第一节列明了允许与禁止使用的语法。
- 每题保留给定的函数 / 类**签名**，只替换函数体。
- IDE 与构建产物（`.idea/`、`.gradle/`、`build/`、`out/`）已由 `.gitignore` 排除。
