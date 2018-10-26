package rest

import org.w3c.xhr.XMLHttpRequest

class Client {
    var xhttp= XMLHttpRequest();

    fun fetch(url:String, header: String ="application/hal+json",
              call: (String) -> Unit) {
        xhttp.open("GET", url, true);
        xhttp.setRequestHeader("Accept", header)
        xhttp.onload = {
            call(xhttp.responseText)
        }
        xhttp.send();
    }

    fun post(url:String, entity:String, call: (String) -> Unit = {}) {
        xhttp.open("POST", url, true);
        xhttp.setRequestHeader("Content-Type", "application/hal+json")
        xhttp.onload = {
            call(xhttp.responseText)
        }
        xhttp.send(entity);
    }

    fun delete(url:String, call: (String) -> Unit = {}){
        xhttp.open("DELETE", url, true);
        xhttp.setRequestHeader("Content-Type", "application/hal+json")
        xhttp.onload = {
            call(xhttp.responseText)
        }
        xhttp.send();
    }
}

