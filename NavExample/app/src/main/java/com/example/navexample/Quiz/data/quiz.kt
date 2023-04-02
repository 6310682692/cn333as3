package com.example.navexample.Quiz.data

data class Question(
    val question: String,
    var options: List<String>,
    val correctAnswer: String
)

val questions = listOf(
    Question(
        "What is this project about",
        listOf("coding", "running", "sleeping", "gaming"),
        "coding"
    ),
    Question(
        "What is the color of ripe apple",
        listOf("rainbow", "white", "red", "orange"),
        "red"
    ),
    Question(
        "Who is the first person to step on moon",
        listOf("Galileo", "ajarn Jack", "Armstrong", "Krittakorn"),
        "Armstrong"
    ),
    Question(
        "What is not the ingredient of pie",
        listOf("Mercury", "Cream", "Flour", "Sugar"),
        "Mercury"
    ),
    Question(
        "What is CS133",
        listOf("Cesium 133", "Calcium 133g", "Computer Science 133", "Just CS133"),
        "Cesium 133"
    ),
    Question(
        "What is the largest planet of solar system",
        listOf("Earth", "Sun", "Jupyter", "Saturn"),
        "๋Jupyter"
    ),
    Question(
        "What year is it now",
        listOf("2555", "2666", "2565", "2566"),
        "2566"
    ),
    Question(
        "What is default django hash",
        listOf("mono alphabetic cipher", "SHA256", "AES", "None"),
        "SHA256"
    ),
    Question(
        "If dad age is 2x son when son was 10 now son age is 20 so what age is dad right now",
        listOf("20", "30", "40", "60"),
        "30"
    ),
    Question(
        "What is dad older brother called",
        listOf("Auntie", "Grandad", "Uncle", "Son"),
        "Uncle"
    )
)