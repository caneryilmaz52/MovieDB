package code.challenge.moviedb.models

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int?
)

data class Cast(
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val profile_path: String
)

data class Crew(
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val name: String,
    val profile_path: String
)