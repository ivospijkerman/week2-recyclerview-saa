package com.example.movierecycler

data class Movie(
    val id: Int,
    val title: String,
    val director: String,
    val releaseYear: Int
) {
    companion object {
        fun getAll(): List<Movie> {
            return listOf(
                Movie(5, "The Shawshank Redemption", "F. Darabont", 1994),
                Movie(-100, "The Godfather", "F.F. Coppolla", 1972),
                Movie(9, "The Godfather II", "F.F. Coppolla", 1974)
            )
        }

        fun getById(id: Int): Movie? {
            return getAll().firstOrNull { movie -> movie.id == id }
        }
    }

    override fun toString(): String {
        return "$title ($releaseYear)"
    }
}
