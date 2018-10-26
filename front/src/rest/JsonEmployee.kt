package rest

class JsonEmployee(
    val firstName: String="",
    val lastName: String="",
    val description: String="",
    val _links: Links= Links())

interface JsonEmployees{
    val employees:Array<JsonEmployee>
}

fun JsonEmployee.str() =
        "FirstName: " + firstName +
        " LastName: " + lastName +
        " Description: " + description +
        " Links: " + _links.str()


fun fromPropsArray(employee: Array<String>):JsonEmployee=
        JsonEmployee(employee[0],employee[1],employee[2])
