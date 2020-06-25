import m from "mithril"
import { MithrilTsxComponent } from './mithrill-ts-component'
import AppHeader from './AppHeader'
import ContentCard from './ContentCard';

export default function() {
    m.mount(document.getElementById("app"), App)
}

const posts = [

    {
        name: "9Gag",
        avatar: `https://randomuser.me/api/portraits/thumb/${Math.random() * 75 > 0.5 ? "men" : "women"}/${Math.ceil(Math.random() * 75)}.jpg`,
        picture: "https://picsum.photos/600/700?random=1",
        comments: [
            {
                value: "Bonito!",
                personName: "Juan"
            },
            {
                value: "¡Desfrute!",
                personName: "Maria"
            }
        ]  
    },
    {
        name: "PerfilGuay",
        avatar: `https://randomuser.me/api/portraits/thumb/${Math.random() * 75 > 0.5 ? "men" : "women"}/${Math.ceil(Math.random() * 75)}.jpg`,
        picture: "https://picsum.photos/600/700?random=2",
        comments: []
    },
    {
        name: "Jose",
        avatar: `https://randomuser.me/api/portraits/thumb/${Math.random() * 75 > 0.5 ? "men" : "women"}/${Math.ceil(Math.random() * 75)}.jpg`,
        picture: "https://picsum.photos/600/700?random=3",
        comments: [{
            value: "jajajajajajaja",
            personName: "e3pw_lovik"
        }]
    },
    {
        name: "Cloud9",
        avatar: `https://randomuser.me/api/portraits/thumb/${Math.random() * 75 > 0.5 ? "men" : "women"}/${Math.ceil(Math.random() * 75)}.jpg`,
        picture: "https://picsum.photos/600/700?random=4",
        comments: [{
            value: "No creooooooooo",
            personName: "__pdr0"
        }]
    }

]

class App extends MithrilTsxComponent<{}> {
    searchText = "";
    constructor(){
        super();
    }
    view() {
        return (
            <div style={{ background: "#fafafa", height: "100%" }} >
                <div style={{ 
                    height: "54px", 
                    background: "white", 
                    display: "flex",
                    alignItems: "center",
                    borderBottom: "1px solid #dbdbdb",
                    margin: "auto",
                    position: "fixed",
                    top: 0,
                    width: "100%"
                }}>
                    <AppHeader 
                        pictureUrl={`https://randomuser.me/api/portraits/thumb/${Math.random() * 75 > 0.5 ? "men" : "women"}/${Math.ceil(Math.random() * 75)}.jpg`} 
                        onSearch={value => this.searchText = value} 
                    />
                </div>
                <div style={{ minHeight: "92vh", width: "100%", maxWidth: "935px", marginLeft: "auto", marginRight: "auto" }}>
                    <div style={{ maxWidth: "600px", paddingTop: "32px" }}>
                        {
                            posts
                                .filter(post => post.name.includes(this.searchText))
                                .map(post => <ContentCard key={post.name} profile={post.name} picture={post.picture} comments={post.comments} avatar={post.avatar} />)
                        }
                    </div>
                </div>
            </div>
        )
    }
}