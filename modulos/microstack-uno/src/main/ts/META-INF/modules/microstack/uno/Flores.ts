import $ from "jquery"

export default function () {
    $("<style>")
        .text(`
        body {
            margin: 0;
            overflow: hidden;
            background: rgb(255,255,255);
            background: linear-gradient(90deg, rgba(255,255,255,1) 0%, rgba(66,63,181,1) 86%);
          }        
        `)
        .appendTo(document.head)

    $("<p>Flores</p>")
        .css("font-size", "10vh")
        .css("text-align", "center")
        .appendTo(document.body)
}



