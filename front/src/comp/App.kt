
import comp.EmployeeList
import react.*
import react.dom.*
import rest.*

class App : RComponent<RProps, App.State>() {
    private val client = Client()
    private fun url(str:String) =
            "http://localhost:8080/api/$str"

    init {
        state.apply { employees = ArrayList()}
    }
    interface State:RState{
        var employees: ArrayList<JsonEmployee>
    }

    override fun componentDidMount() {
        client.fetch(url("employees")) {
            val embed = JSON.parse<Embedded<JsonEmployees>>(it)
            state.employees.clear()
            state.employees.addAll(embed._embedded.employees)
            setState{}
        }
    }

    override fun RBuilder.render() {
        EmployeeList(state.employees)
    }
}

fun RBuilder.app() = child(App::class) {}
