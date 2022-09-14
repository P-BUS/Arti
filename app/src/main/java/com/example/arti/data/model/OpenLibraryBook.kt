
data class OpenLibraryBook(
    val authors: List<Author>,
    val classifications: Classifications,
    val contributions: List<String>,
    val covers: List<Int>,
    val created: Created,
    val first_sentence: FirstSentence,
    val identifiers: Identifiers,
    val isbn_10: List<String>,
    val isbn_13: List<String>,
    val key: String,
    val languages: List<Language>,
    val last_modified: LastModified,
    val latest_revision: Int,
    val local_id: List<String>,
    val number_of_pages: Int,
    val ocaid: String,
    val publish_date: String,
    val publishers: List<String>,
    val revision: Int,
    val source_records: List<String>,
    val title: String,
    val type: Type,
    val works: List<Work>
)

data class Author(
    val key: String
)

class Classifications

data class Created(
    val type: String,
    val value: String
)

data class FirstSentence(
    val type: String,
    val value: String
)

data class Identifiers(
    val goodreads: List<String>,
    val librarything: List<String>
)

data class Language(
    val key: String
)

data class LastModified(
    val type: String,
    val value: String
)

data class Type(
    val key: String
)

data class Work(
    val key: String
)


/*
data class OpenLibraryBook(
    val _version_: Long,
    val author_alternative_name: List<String>,
    val author_facet: List<String>,
    val author_key: List<String>,
    val author_name: List<String>,
    val contributor: List<String>,
    val cover_edition_key: String,
    val cover_i: Int,
    val ddc: List<String>,
    val ddc_sort: String,
    val ebook_access: String,
    val ebook_count_i: Int,
    val edition_count: Int,
    val edition_key: List<String>,
    val first_publish_year: Int,
    val has_fulltext: Boolean,
    val ia: List<String>,
    val ia_collection_s: String,
    val id_goodreads: List<String>,
    val id_librarything: List<String>,
    val isbn: List<String>,
    val key: String,
    val language: List<String>,
    val last_modified_i: Int,
    val lcc: List<String>,
    val lcc_sort: String,
    val lccn: List<String>,
    val lending_edition_s: String,
    val lending_identifier_s: String,
    val number_of_pages_median: Int,
    val oclc: List<String>,
    val person: List<String>,
    val person_facet: List<String>,
    val person_key: List<String>,
    val place: List<String>,
    val place_facet: List<String>,
    val place_key: List<String>,
    val printdisabled_s: String,
    val public_scan_b: Boolean,
    val publish_date: List<String>,
    val publish_place: List<String>,
    val publish_year: List<Int>,
    val publisher: List<String>,
    val publisher_facet: List<String>,
    val seed: List<String>,
    val subject: List<String>,
    val subject_facet: List<String>,
    val subject_key: List<String>,
    val subtitle: String,
    val time: List<String>,
    val time_facet: List<String>,
    val time_key: List<String>,
    val title: String,
    val title_suggest: String,
    val type: String
) */