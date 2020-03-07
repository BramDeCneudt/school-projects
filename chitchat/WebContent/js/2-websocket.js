var webSocket;

var url = "ws:localhost:8080/Chitchat/chitchatserver";


//websocket
function openSocket() {
    webSocket = new WebSocket(url);
//when the connection is opend
    webSocket.onopen = function (event) {
        writeResponse("Connection opened");
    };
//when the ws gets a message
    webSocket.onmessage = function (event) {
        writeJSON(event.data);
    };
//when the websocket is closed
    webSocket.onclose = function (event) {
        writeResponse("Connection closed");
    };
 //on error   
    webSocket.onerror = function(event) {
        writeResponse("error: " + event.data);
    }
}

var messages = document.getElementById("messages");
//send reaction on artikel post req
function send(id) {
    //get id of article is een integer value
    var index = id.getAttribute("id");
    //get naam value en reactie value
    var nameString = document.getElementById("naam" + index).value;
    var reactionString = document.getElementById("reaction" + index).value;
    
    //maak er json van
    var reactionJSON = JSON.stringify({name : nameString, reaction : reactionString, id : index});
    //verstuur het met een websocket
    webSocket.send(reactionJSON);
}
//close the socket
function closeSocket() {
    webSocket.close();
}
//parse and process the json
function writeJSON(text) {
    var topics = JSON.parse(text);

    makeTopicsFromJSON(topics, document.getElementById("articles"));
}
//write to messages 
function writeResponse(text) {
    messages.innerHTML += "<br/>" + text;
}
// make topics and reactions from json
function makeTopicsFromJSON(topics, parentElement) {

    parentElement.innerHTML = "";

    for (var i = 0; i < topics.length; i++) {

        var index = i + 1;
        var topic = topics[i];

        var article = document.createElement("article");

        var h3 = document.createElement("h3");
        var p = document.createElement("p");

        var naam = document.createElement("input");
        var reaction = document.createElement("input");
        var submit = document.createElement("button");


        h3.innerHTML = topic.naam;
        p.innerHTML = topic.description;


        article.appendChild(h3);
        article.appendChild(p);
        
        var reactions = topic.reactions;
        
        for (var j = 0; j < reactions.length; j++) {
            var h4 = document.createElement("h4");
            p = document.createElement("p");
            
            h4.innerHTML = reactions[j].name;
            p.innerHTML = reactions[j].reaction;
            
            article.appendChild(h4);
            article.appendChild(p);
        }
        
        naam.setAttribute("type", "text");
        naam.setAttribute("id", "naam" + index);

        reaction.setAttribute("type", "text");
        reaction.setAttribute("id", "reaction" + index);

        submit.innerHTML = "submit";
        submit.setAttribute("id", index);
        
        submit.addEventListener("click", function() {
            send(this);
        });

        article.appendChild(naam);
        article.appendChild(reaction);
        article.appendChild(submit);

        parentElement.appendChild(article);

    }

}
