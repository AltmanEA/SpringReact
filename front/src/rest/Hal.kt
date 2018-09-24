package rest

interface Href{
    val href:String?
}

interface Links{
    val self:Href?
    val profile:Href?
}

interface Embedded<T>{
    val _embedded:T
}

fun Links.str() = " Self: " + (self?.href?:"none") + " Profile: " + (profile?.href?:"none")