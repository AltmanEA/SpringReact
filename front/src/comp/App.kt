
import comp.CreateDialog
import comp.EmployeeList
import react.*
import rest.*

class App : RComponent<RProps, App.State>() {
    private val client = Client()
    private fun url(str:String) =
            "http://localhost:8080/api/$str"
    private fun cleanUrl(str:String?) =
            str?.substringBefore("{")!!

    init {
        state.apply {
            employees = ArrayList()
            attributes = ArrayList()
            links = Links()
            pageSize = 2
        }
    }

    interface State:RState{
        var attributes: List<Property>
        var employees: List<JsonEmployee>
        var links:Links
        var pageSize:Int
    }

    override fun componentDidMount() {
        loadFromServer(state.pageSize)
    }

    override fun RBuilder.render() {
        CreateDialog(state.attributes, ::onCreate)
        EmployeeList(state.employees, state.links, state.pageSize, ::onNavigate, ::onDelete, ::onUpdatePageSize)

    }

    private fun loadFromServer(pageSize: Int){
        client.fetch(url("employees?size=$pageSize")) { e ->
            val embed = JSON.parse<Embedded<JsonEmployees>>(e)
            val profileURL = embed._links?.profile?.href?:""
            client.fetch(profileURL, "application/schema+json") {s->
                val schema = JSON.parse<Schema<*>>(s)
                val props = toArray<Property>(schema.properties)
                setState {
                    attributes = props.toList()
                    employees = embed._embedded?.employees?.toList()?:ArrayList()
                    links = embed._links ?: Links()
                }
            }
        }
    }

    private fun onCreate(newEmployee: JsonEmployee) {
        val json = JSON.stringify(newEmployee)
        val h = state.links.last?.href?:""
        client.post(cleanUrl(state.links.self?.href),json) {
            onNavigate(h)
        }
    }

    private fun onDelete(employee: JsonEmployee){
        client.delete(employee._links.self?.href?:""){loadFromServer(state.pageSize)}
    }

    private fun onNavigate(url:String){
        client.fetch(url){
            val embed = JSON.parse<Embedded<JsonEmployees>>(it)
            setState{
                employees = embed._embedded?.employees?.toList()?:ArrayList()
                links = embed._links ?: Links()
            }
        }
    }

    private fun onUpdatePageSize(pageSize: Int) {
        if(pageSize!=state.pageSize) {
            setState {
                this.pageSize = pageSize
            }
            loadFromServer(pageSize)
        }
    }

}

fun RBuilder.app() = child(App::class) {}
