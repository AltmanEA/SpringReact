package rest

import org.w3c.xhr.XMLHttpRequest


class Client {
    var xhttp= XMLHttpRequest();

    fun fetch(url:String, call: (String) -> Unit) {
//        call(testtext) // Для тестов
        xhttp.open("GET", url, true);
        xhttp.setRequestHeader("Accept", "application/hal+json" )
        xhttp.onload = {
            call(xhttp.responseText)
        }
        xhttp.send();
    }
}

val testtext = """
{
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
    },
    {
      "firstName" : "Bilbo",
      "lastName" : "Baggins",
      "description" : "burglar",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/employees/2"
        }
      }
    } ]
  }
}
""".trimIndent()
