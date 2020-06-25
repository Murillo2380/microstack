
import { MithrilTsxComponent } from "./mithrill-ts-component"
import m from "mithril"
import "./test"

export default class SomeComponent extends MithrilTsxComponent<{}> {
    someData: string;
    user: any
    searchText = ""
    clients = ["abc", "123"]
    constructor(vnode){
        super();
        this.someData = vnode.attrs.someData
        setTimeout(() => {
            this.user = "Murillo Ferreira"
            m.redraw()
        }, 1000)
    }

    handleInput(e: any){
        this.searchText = e.target.value
        console.log({value: e.target.value})
    }

    view() {
        return (
            <div>
                Hello world, data given is {this.someData} and name is {this.user} finished = 
                <input onchange={(e) => this.handleInput(e)} />
                <div>Search text is {this.searchText}</div>
                <div>Clients {this.clients.map(client => <span>{client}</span>)}</div>
                <div>Filtered {this.clients.filter(client => client.includes(this.searchText)).map(client => <span>{client}</span>)}</div>
            </div>
        )

    }
}