import m from "mithril"
import { MithrilTsxComponent } from './mithrill-ts-component'


export default class HeartIcon extends MithrilTsxComponent<{}> {
    selected = false
    
    view(vnode) {
        const { small } = vnode.attrs        
        return (
            <a style={{ cursor: "pointer" }} onclick={() => this.selected = !this.selected}>
                {this.selected && <FilledHeart small={small} /> || <EmptyHeart small={small} />}
            </a>
        )
    }
}
class FilledHeart extends MithrilTsxComponent<{}>{

    view(vnode) {
        const { small } = vnode.attrs        
        return (
            <svg class="bi bi-heart-fill" width={small ? "1rem" : "1.5em"} height={small ? "1rem" : "1.5em"} viewBox="0 0 16 16" fill="#ed4956" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
            </svg>
        );
    }
}

class EmptyHeart extends MithrilTsxComponent<{}>{

    view(vnode) {
        const { small } = vnode.attrs        
        return (
            <svg class="bi bi-heart" width={small ? "1rem" : "1.5em"} height={small ? "1rem" : "1.5em"} viewBox="0 0 16 16" fill="black" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
            </svg>
        );
    }
}
