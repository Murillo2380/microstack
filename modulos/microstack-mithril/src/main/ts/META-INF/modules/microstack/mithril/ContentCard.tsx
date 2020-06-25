import m from "mithril"
import { MithrilTsxComponent } from './mithrill-ts-component'
import HeartIcon from './HeartIcon';


export default class ContentCard extends MithrilTsxComponent<{}> {
    profile = "";
    picture = "";
    avatar = "";
    comments = []
    constructor(vnode){
        super();
        this.profile = vnode.attrs.profile
        this.avatar = vnode.attrs.avatar
        this.picture = vnode.attrs.picture
        this.comments = vnode.attrs.comments
    }

    handleComment(e) {
        const comment = e.target.value;
        this.comments.push({ value: comment, personName: "you" })
        e.target.value = ""
    }

    view() {
        return (
            <div style={{ 
                background: "#FFF", 
                marginTop: "30px", 
                marginBottom: "30px",
                border: "1px solid #dbdbdb"
            }}>
                <div style={{ padding: "16px" }}>
                    <img style={{ borderRadius: "50%", width: "32px", height: "32px", marginRight: "8px" }} src={this.avatar} />
                    <strong>{this.profile}</strong>
                </div>
                <div>
                    <img 
                        style={{ 
                            width: "100%", 
                            height: "700px", 
                            objectFit: "cover" 
                        }} 
                        src={this.picture} 
                    />
                </div>
                <div style={{ padding: "16px" }}>
                    <HeartIcon />
                </div>
                {this.comments && this.comments.length > 0 && ( 
                    <div style={{ paddingLeft: "16px", paddingRight: "16px", paddingBottom: "16px" }}>
                        {this.comments.map(comment => <Comment person={comment.personName} comment={comment.value} />)}
                    </div>
                )}
                <div style={{ padding: "16px", borderTop: "1px solid #dbdbdb" }}>
                    <input style={{ width: "100%", border: "none" }} placeholder="Add a comment..." onchange={e => this.handleComment(e)} />
                </div>
            </div>
        )
    }
}

class Comment extends MithrilTsxComponent<{}> {
    view(vnode){
        const { person, comment } = vnode.attrs
        return (
            <div>
                <strong style={{ marginRight: "4px" }}>
                    <small>{person}:</small>
                </strong>
                <small>{comment}</small>
                <span style={{ float: "right" }}>
                    <HeartIcon small />
                </span>
            </div>)
    }
}