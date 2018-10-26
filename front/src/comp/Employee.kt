package comp

import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.td
import react.dom.tr
import rest.JsonEmployee

class Employee:RComponent<Employee.Props, RState>(){
    interface Props:RProps{
        var employee: JsonEmployee
        var key:String
        var onDelete:()->Unit
    }
    override fun RBuilder.render() {
        tr {
            td { +props.employee.firstName }
            td { +props.employee.lastName }
            td { +props.employee.description }
            td {
                button {
                    attrs { onClickFunction = {
                        props.onDelete() } }
                    +"Delete"
                }
            }
        }
    }
}
fun RBuilder.Employee(employee:JsonEmployee, key: String, onDelete:()->Unit)
        = child(Employee::class) {
    attrs.employee = employee
    attrs.key = key
    attrs.onDelete = onDelete
}
