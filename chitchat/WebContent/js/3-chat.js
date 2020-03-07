$(timer);
$("#sendmessage").on("click", sendChat);

function timer() {
    getChat();
    setInterval(getChat, 10000);
}

//send chat with post req ajax
function sendChat() {
    var message = 'message=' + $("#textfield").val();
    $("#textfield").val("");
    $.ajax({
        type: "POST",
        url: "chitchat?action=sendMessage",
        data: encodeURI(message) + '&id=' + encodeURI($("#chatid").val()),
        success: getChat
    });
}
//getchat with ajax get
function getChat() {
    var id = $("#chatid").val();
    $.get({
        url: "chitchat?action=chatJSON&id=" + encodeURI(id),
        success: function (data) {
            updateChat(data);
        },
    });
}

// update the chat --> clear chat and make new with processed json data
function updateChat(data) {

    var jsonData = $.parseJSON(data);
    var messages = jsonData.messages;

    var messageDiv = $(".chat-ul");
    messageDiv.html("");

    $.each(messages, function (i, item) {
        makeChat(item);
    });
}
//make chat 
function makeChat(chat) {

    var position = "left";
    var float = "";
    var align = "align-left";
    var fix = "";
    var onlineleft = "<i class='fa fa-circle left'></i>";
    var onlineRight = "";

    if (chat.person.username === $("#name").val()) {
        position = "right";
        float = "float-right";
        align = "align-right";
        fix = "class='clearfix'";
        onlineleft = "";
        onlineRight = "<i class='fa fa-circle right'></i>";
    }

    var username = $("#name").val();

    var list = $("<li " + fix + "></li>");
    var messageData = $("<div class='message-data " + align + "'></div>");
    var message = $("<div class='message " + position + "-message " + float + "'></div>");

    //messageData.attr("class", "message-data");

    //banner above message//
    var spanData = $("<span class='message-data-name'></span>");
    spanData.html("<p class='clearspacing'>" + onlineleft + " " + chat.person.username + " " + onlineRight + "</p> ");


    //actual message//
    message.html("<p class='clearspacing minwidth'>" + chat.message + "</p>");

    messageData.append(spanData);



    list.append(messageData);
    list.append(message);

    var messageDiv = $(".chat-ul");
    messageDiv.append(list);
}
