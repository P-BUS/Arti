
data class OpenLibraryBook(
    val author_key: List<String>,
    val author_name: List<String>,
    val cover_i: Int,
    val edition_count: Int,
    val first_publish_year: Int,
    val has_fulltext: Boolean,
    val ia: List<String>,
    val key: String,
    val public_scan_b: Boolean,
    val title: String
)