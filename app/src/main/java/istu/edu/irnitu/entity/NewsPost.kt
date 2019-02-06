package istu.edu.irnitu.entity

data class NewsPost(
    val id: Long,
    val title: String,
    val date: String,
    val categories: List<NewsCategory>,
    val url: String,
    val mainImageUrl: String,
    val content: String,
    val images: List<Image>
)