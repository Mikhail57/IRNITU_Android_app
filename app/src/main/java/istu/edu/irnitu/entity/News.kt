package istu.edu.irnitu.entity

data class News(
        val id: Long,
        val title: String,
        val date: String,
        val categories: List<NewsCategory>,
        val url: String,
        val image: String,
        val shortDesc: String
)