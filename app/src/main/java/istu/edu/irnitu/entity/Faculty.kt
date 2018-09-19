package istu.edu.irnitu.entity

data class Faculty(
    val title: String,
    val courses: Map<Int, List<String>>
)