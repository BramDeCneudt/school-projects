var xHRObject = new XMLHttpRequest();
var newStatus = new XMLHttpRequest();
var person = new XMLHttpRequest();


var statusSubmit = document.getElementById("statusSubmit");
statusSubmit.addEventListener("click", sendStatus);

var statusSubmit = document.getElementById("addFriendSubmit");
statusSubmit.addEventListener("click", addFriend);

//send post request to add a friend xmlhttprequest protocol
function addFriend() {
    var username = document.getElementById("addFriend").value;
    document.getElementById("addFriend").value = "";
// encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
    var information = "username=" + encodeURI(username);
//init a request
    newStatus.open("POST", "chitchat?action=addFriend", true);
// belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
// protocol header information
    newStatus.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	newStatus.send(information);
}
//post request for new status
function sendStatus() {
    var status = document.getElementById("statusInput").value;
    document.getElementById("statusInput").value = "";
// encodeURIComponent om UTF-8 te gebruiken en speciale karakters om te zetten naar code
    var information = "status=" + encodeURI(status);
    newStatus.open("POST", "chitchat?action=editstatus", true);
// belangrijk dat dit gezet wordt anders kan de servlet de informatie niet interpreteren!!!
// protocol header information
    newStatus.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	newStatus.send(information);
    getPersonFromSession();
    
}
//get request to get person from session
function getPersonFromSession() {
   
    person.open("GET", "chitchat?action=getPersonFromSession", true);
    person.onreadystatechange = setInfo;
    person.send(null);
}
    
function setInfo() {
    if (person.readyState == 4) {
        
        if (person.status == 200) {
            var serverResponse = JSON.parse(person.responseText);
            
            var statusPerson = document.getElementById("statusPerson");
            statusPerson.innerHTML = serverResponse.status;
        }
        
    }
}
//get persons from friend list
function getPersons() {

    xHRObject.open("GET", "chitchat?action=getPersons", true);
    xHRObject.onreadystatechange = getData;
    xHRObject.send(null);
    
}

/*
    0 = unsent
    1 = opened
    2 = header_receiver
    3 = loading
    4 = done
*/
// make a table with your friends in
function getData() {

    
    if (xHRObject.readyState == 4) {
        // status = status van header
        if (xHRObject.status == 200) {
            var serverResponse = JSON.parse(xHRObject.responseText);
            
            //make friends table
            var tableFriend = document.getElementById("tableFriends");
            
            
            
            if (tableFriend.rows.length > 1) {
            deleteRows(tableFriend);
            }
            
            for (var i = 0; i < serverResponse.length; i++) {
                
                var friend = serverResponse[i];
                
                var tr = document.createElement("tr");
                var tdName = document.createElement("td");
                var tdStatus = document.createElement("td");
                
                var name = document.createTextNode(friend.username);
                var status = document.createTextNode(friend.status);
                
                tr.setAttribute("class", "username");
                tr.setAttribute("id", friend.username);
                tr.setAttribute("title", "click to start a chat");
                
                tdName.appendChild(name);
                tdStatus.appendChild(status);
                
                
                tr.appendChild(tdName);
                tr.appendChild(tdStatus);
                
                tableFriend.appendChild(tr);
            }
            setTimeout(getPersons, 10000);
        }
    }
    
}
//needed to delet the rows
function deleteRows(table) {
    
    var index = table.rows.length;
    index--;
    
    for (var i = index; i > 0; i--) {
        table.deleteRow(i);
    }
    
}