import m from "mithril"
//
// Ver: https://github.com/andraaspar/mithril-tsx-component
//
// Esto funciona al usar jsx.d.ts y configurar tsconfig.json
//

export abstract class MithrilTsxComponent<A> implements m.ClassComponent<A> {
    // Required for type checking JSX attributes
    private props: A & m.Lifecycle<A, this> & { key?: string | number }

    // Copy of m.ClassComponent<A>.view required by TS
    abstract view(vnode: m.Vnode<A, this>): m.Children | null | void
}
