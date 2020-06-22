import m from "mithril"
import { MithrilTsxComponent } from './mithrill-ts-component'
import { Clientes } from './clientes';

export default function() {
    const abc: number =  123;
    m.mount(document.getElementById("app"), App)

}

const estado = {
    contador: 1000,
    incrementar() {
        this.contador += 5;  
    },
    decrementar() {
        this.contador--
    }
}

class App extends MithrilTsxComponent<{}> {
    users = [
        {
            name: "N1",
            age: 18
        },
        {
            name: "N2",
            age: 22,
        } 
    ]
    view() {
        return (
            <div style={{...cssCentrado, fontSize: "5vh"}}>
                <div>
                    Contador = <Contador/>
                </div>
                <span style="margin-left: 1em">
                    <Decrementar/>
                    <Incrementar/>
                    <Clientes clientes={this.users}  />
                </span>
            </div>
        )
    }
}

class Incrementar extends MithrilTsxComponent<{}> {
    // prettier-ignore
    view() {
        return (
            <button class="btn btn-default" onclick={() => estado.incrementar()}>
                <i class="glyphicon glyphicon-plus"/>
            </button>
        )
    }
}

class Decrementar extends MithrilTsxComponent<{}> {
    // prettier-ignore
    view() {
        return (
            <button class="btn btn-default" onclick={() => estado.decrementar()}>
                <i class="glyphicon glyphicon-minus"/>
            </button>
        )
    }
}

class Contador extends MithrilTsxComponent<{}> {
    // prettier-ignore
    view() {
        return (
            <span style={{color: estado.contador < 0 ? "red" : "" }}>
                {estado.contador}
            </span>
        )
    }
}


const cssCentrado =  {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    height: "100vh",
}
