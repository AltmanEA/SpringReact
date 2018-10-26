package comp

import kotlinx.html.js.onBlurFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import rest.JsonEmployee
import rest.Links

class EmployeeList: RComponent<EmployeeList.Props,RState>() {
    var refs:dynamic = null

    interface Props:RProps{
        var employees: List<JsonEmployee>
        var onNavigate: (String)->Unit
        var onDelete: (JsonEmployee)->Unit
        var onUpdatePageSize: (Int)->Unit
        var links: Links
        var pageSize:Int
    }

    override fun RBuilder.render() {
        input {
            attrs["ref"] = "pageSize"
            attrs["defaultValue"] = props.pageSize
//            attrs { onClickFunction = ::handleInput }
            attrs { onBlurFunction = ::handleInput }
        }
        table {
            tbody {
                tr {
                    th { +"First Name" }
                    th { +"Last Name" }
                    th { +"Description" }
                    th { +"delete" }
                }
                props.employees.map {
                    Employee(it, it._links.self?.href ?: ""){props.onDelete(it)}
                }
            }
        }
        val l = props.links
        arrayOf("<<" to l.first,
                "<" to l.prev,
                ">" to l.next,
                ">>" to l.last)
                .map{(str, link)->
                    if(link!=null)
                        button {
                            attrs{onClickFunction = {handleNav(it, link.href)}}
                            +str
                        }
                }
    }

    private fun handleNav(e: Event, href:String){
        e.preventDefault()
        props.onNavigate(href)
    }

    private fun handleInput(e: Event) {
        e.preventDefault()
        val input = findDOMNode(this.refs["pageSize"]) as HTMLInputElement
        val ps = input.value.toInt()
        if("^[0-9]+$".toRegex().matches(input.value))
            props.onUpdatePageSize(ps)
        else
            input.value = props.pageSize.toString()
    }
}

fun RBuilder.EmployeeList(employees: List<JsonEmployee>,
                          links:Links,
                          pageSize:Int,
                          onNavigate: (String)->Unit,
                          onDelete: (JsonEmployee)->Unit,
                          onUpdatePageSize: (Int)->Unit)
        = child(EmployeeList::class) {
    attrs.employees = employees
    attrs.links = links
    attrs.pageSize = pageSize
    attrs.onNavigate = onNavigate
    attrs.onDelete = onDelete
    attrs.onUpdatePageSize = onUpdatePageSize
}
