package rest

fun test(expected:String, actual:String?){
    if(expected!=actual)
        throw Error(expected)
}



fun testHAL(): String {
    var result = "OK"
    val parse = JSON.parse<Embedded<JsonEmployees>>(testJSON1)
    val parseS = JSON.parse<Schema<*>>(testJSON2)
    val props = toArray<Property>(parseS.properties)

    try {
        test("http://localhost:8080/api/employees?page=0&size=2", parse._links?.first?.href)
        test("http://localhost:8080/api/employees?page=2&size=2", parse._links?.last?.href)
        test("Frodo",parse._embedded?.employees?.get(0)?.firstName)
        test("http://localhost:8080/api/employees/2", parse._embedded?.employees?.get(1)?._links?.self?.href)
        test("3", parse.page?.totalPages.toString())

        test("Employee", parseS.title)
        test(props[0].title,"First name")
        test(props[1].type,"string")
    } catch (e:Error){
        result =  e.toString()
    }


    return result
}

val testJSON1 = """
{
  "_links" : {
    "first" : {
      "href" : "http://localhost:8080/api/employees?page=0&size=2"
    },
    "self" : {
      "href" : "http://localhost:8080/api/employees"
    },
    "next" : {
      "href" : "http://localhost:8080/api/employees?page=1&size=2"
    },
    "last" : {
      "href" : "http://localhost:8080/api/employees?page=2&size=2"
    }
  },
  "_embedded" : {
    "employees" : [ {
      "firstName" : "Frodo",
      "lastName" : "Baggins",
      "description" : "ring bearer",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/employees/1"
        }
      }
    }, {
      "firstName" : "Bilbo",
      "lastName" : "Baggins",
      "description" : "burglar",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/employees/2"
        }
      }
    } ]
  },
  "page" : {
    "size" : 2,
    "totalElements" : 6,
    "totalPages" : 3,
    "number" : 0
  }
}
""".trimIndent()

val testJSON2 = """
{
  "title" : "Employee",
  "properties" : {
    "firstName" : {
      "title" : "First name",
      "readOnly" : false,
      "type" : "string"
    },
    "lastName" : {
      "title" : "Last name",
      "readOnly" : false,
      "type" : "string"
    },
    "description" : {
      "title" : "Description",
      "readOnly" : false,
      "type" : "string"
    }
  },
  "definitions" : { },
  "type" : "object"
}
""".trimIndent()
