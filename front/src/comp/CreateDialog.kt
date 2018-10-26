package comp

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*
import kotlinx.html.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import rest.JsonEmployee
import rest.Property
import rest.fromPropsArray
import kotlin.browser.window

class CreateDialog: RComponent<CreateDialog.Props, RState>() {
    var refs:dynamic = null

    interface Props:RProps{
        var attributes: List<Property>
        var onCreate:(JsonEmployee)->Unit
    }

    fun handleSubmit(e: Event) {
        e.preventDefault()
        val newEmployee = Array(props.attributes.size) {
            (findDOMNode(this.refs[props.attributes[it].title]) as HTMLInputElement).value.trim()
        }
        props.onCreate(fromPropsArray(newEmployee))
        for(attr in props.attributes){
            (findDOMNode(this.refs[attr.title]) as HTMLInputElement).value = ""
        }
        window.location.href = "#"
    }

    override fun RBuilder.render(){
        div {
            a("#createEmployee") { +"Create" }
            div("modalDialog") {
                attrs["id"] = "createEmployee"
                div {
                    a("#", "close") { +"X" }
                    h2 { +"Create new employee" }
                    form {
                        props.attributes.map { prop ->
                            p() {
                                attrs["key"] = prop.title
                                input(type = InputType.text, classes = "field") {
                                    attrs["placeholder"] = prop.title
                                    attrs["ref"] = prop.title
                                }
                            }
                        }
                        button {
                            attrs { onClickFunction = { handleSubmit(it) } }
                            +"Create"
                        }
                    }
                }
            }
        }
    }
}

fun RBuilder.CreateDialog(attributes: List<Property>, onCreate:(JsonEmployee)->Unit)
        = child(CreateDialog::class) {
    attrs.attributes = attributes
    attrs.onCreate = onCreate
}
