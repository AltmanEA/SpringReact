package rest

class Href{
    val href:String=""
}

class Links{
    val first:Href?=null
    val prev:Href?=null
    val self:Href?=null
    val next:Href?=null
    val last:Href?=null
    val profile:Href?=null
}

fun Links.str()=
        if (first!=null) {"First: "+first.href} else {""} +
        if (last!=null) {"Last: "+last.href} else {""} +
        if (next!=null) {"Next: "+next.href} else {""} +
        if (profile!=null) {"Profile: "+profile.href} else {""} +
        if (self!=null) {"Self: "+self.href} else {""}

class Page{
    val size:Int?=null
    val totalElements:Int?=null
    val totalPages:Int?=null
    val number:Int?=null
}

class Embedded<T>{
    val _embedded: T? =null
    val _links:Links? = null
    val page:Page? = null
}


class Property{
    val title:String=""
    val readOnly:String=""
    val type:String=""
}

class Schema<T>{
    val title:String?=null
    val properties:T?=null
}

fun <T> toArray(obj:dynamic):Array<T>{
    return js("""
    var array = []
    for (var prop in obj)
        array.push(obj[prop]);
    array
    """) as Array<T>
}

