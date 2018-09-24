package comp

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.td
import react.dom.tr
import rest.Href
import rest.JsonEmployee

class Employee:RComponent<Employee.Props, RState>(){
    interface Props:RProps{
        var employee: JsonEmployee
        var key:String
    }

    override fun RBuilder.render() {
        tr {
            td { +props.employee.firstName }
            td { +props.employee.lastName }
            td { +props.employee.description }
        }
    }
}

fun RBuilder.Employee(employee:JsonEmployee, key: String) = child(Employee::class) {
    attrs.employee = employee
    attrs.key = key
}
