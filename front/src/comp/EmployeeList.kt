package comp

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.table
import react.dom.tbody
import react.dom.th
import react.dom.tr
import rest.Href
import rest.JsonEmployee

class EmployeeList: RComponent<EmployeeList.Props,RState>() {
    interface Props:RProps{
        var employees: ArrayList<JsonEmployee>
    }
    override fun RBuilder.render() {
        table {
            tbody {
                tr {
                    th { +"First Name" }
                    th { +"Last Name" }
                    th { +"Description" }
                }
                props.employees.map {
                    Employee(it, it._links.self?.href?:"")
                }
            }
        }

    }
}
fun RBuilder.EmployeeList(employees: ArrayList<JsonEmployee>) = child(EmployeeList::class) {
    attrs.employees = employees
}
