package com.tompietri.aoc2022.day07

fun day7FirstSolution(input: List<String>): Int {
    val directories = buildDirectories(input)

    return directories.map { it.value.size() }
        .filter { it <= 100000 }
        .sum()
}


fun day7SecondSolution(input: List<String>): Int {
    val directories = buildDirectories(input)

    val totalUsedSpace = directories[Path.root()]!!.size()
    val availableSpace = 70000000 - totalUsedSpace
    val neededSpace = 30000000 - availableSpace

    return directories.map { it.value.size() }
        .filter { it >= neededSpace }
        .minOf { it }
}

private fun buildDirectories(input: List<String>): MutableMap<Path, Directory> {
    var currentPath = Path.root()
    val directories = mutableMapOf<Path, Directory>()
    directories[currentPath] = Directory(currentPath, mutableListOf())

    input.drop(1).forEach { line ->
        if (line.startsWith("$ cd")) {
            val targetDirectory = line.split(" ").last()
            if (targetDirectory == "..") {
                currentPath = currentPath.back()
            } else {
                currentPath += targetDirectory
            }
        } else if (line.startsWith("$ ls")) {
            // do nothing
        } else if (line.startsWith("dir")) {
            val directoryPath = currentPath + line.split(" ").last()
            val newDirectory = Directory(directoryPath, mutableListOf())
            directories[currentPath]!!.content.add(newDirectory)
            directories[directoryPath] = newDirectory
        } else {
            val fileSize = line.split(" ").first().toInt()
            directories[currentPath]!!.content.add(DataFile(fileSize))
        }
    }
    return directories
}

private interface File {
    fun size(): Int;
}

private data class Directory(val path: Path, val content: MutableList<File>) : File {
    override fun size(): Int = content.sumOf { it.size() }
}

private data class DataFile(val size: Int) : File {
    override fun size(): Int = size
}

private data class Path(val parts: List<String>) {
    companion object {
        fun root() = Path(listOf())
    }

    operator fun plus(part: String): Path {
        return Path(parts + part)
    }

    fun back(): Path {
        return Path(parts.dropLast(1))
    }
}
