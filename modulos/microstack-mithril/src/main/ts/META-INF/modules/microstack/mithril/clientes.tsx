import m from "mithril"
import { MithrilTsxComponent } from './mithrill-ts-component'

export class Clientes extends MithrilTsxComponent<{}> {
    
    users: Array<any>;

    constructor(vnode) {
        super();
        this.users = vnode.attrs.clientes
    }

    view()  {
        return (
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {this.users.map(user => <ClientItem name={user.name} age={user.age} /> )}
                </tbody>
            </table>
        )
    }

}

class ClientItem extends MithrilTsxComponent<{}> {
    name: string;
    age: number;

    constructor(vnode){
        super()
        this.name = vnode.attrs.name
        this.age = vnode.attrs.age
    }

    view() {
        return (
            <tr>
                <td>{this.name}</td>
                <td>{this.age}</td>
                <td><button class="btn btn-primary" onclick={() => this.age++}>Bday</button></td>
            </tr>
        )
    }

}