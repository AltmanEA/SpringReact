package rest

interface JsonEmployee {
    val firstName: String
    val lastName: String
    val description: String
    val _links: Links
}

interface JsonEmployees{
    val employees:Array<JsonEmployee>
}

fun JsonEmployee.str() = "FirstName: " + firstName +
        " LastName: " + lastName +
        " Description: " + description +
        " Links: " + _links.str()
