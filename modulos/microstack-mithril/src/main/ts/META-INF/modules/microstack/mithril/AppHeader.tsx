import m from "mithril"
import { MithrilTsxComponent } from './mithrill-ts-component'
import HeartIcon from './HeartIcon';


export default class AppHeader extends MithrilTsxComponent<{}> {
    onSearch;
    pictureUrl = ""
    constructor(vnode) {
        super();
        console.log(vnode.attrs.onSearch)
        this.onSearch = vnode.attrs.onSearch;
        this.pictureUrl = vnode.attrs.pictureUrl;
    }

    handleInput(e){
        console.log(e.target.value)
        this.onSearch(e.target.value)
    }

    view() {
        return (
            
                <div style={{
                    display: "flex", 
                    flexDirection: "row",
                    width: "100%",
                    maxWidth: "935px",
                    margin: "auto",
                }}>
                    <img src="//instagram.com/static/images/web/mobile_nav_type_logo.png/735145cfe0a4.png" />
                    <div style={{margin: "auto"}}>
                        <input 
                            placeholder="search" 
                            onchange={e => this.handleInput(e)} 
                            style={{
                                background: "#fafafa",
                                border: "solid 1px #dbdbdb",
                                textAlign: "center",
                                borderRadius: "3px",
                            }}
                            />
                    </div>
                    <div >
                        <img src={this.pictureUrl} style={{borderRadius: "50%", width: "22px", height: "22px"}} />
                    </div>
                </div>
        )
    }
}
