var chatbox_array = new Array();

$("body").on("click", ".sendmessage", function () {
    var id = $(this).attr("data-id");
    sendChat(id);
});

$("body").on("click", ".username", function () {
    getChatBox($(this).attr("id"));
});

$("body").on("click", ".chathead", function () {
    var chathead = $(this);
    var chat = chathead.parent().find(".chat");


    if (chat.css('display') === 'none') {
        chat.show();
    } else {
        chat.hide();
    }
});

$("body").on("click", ".close", function () {
    $(this).parent().parent().remove();
});


function refreshChatBoxes() {
    for (var i = 0; i < chatbox_array.length; i++) {
        getChat(chatbox_array[i]);
    }
}

function sendChat(id) {

    var chatbox = $("#" + id);

    var textfield = chatbox.find(".textfield");

    var message = 'message=' + textfield.val();
    textfield.val("");

    $.ajax({
        type: "POST",
        url: "chitchat?action=sendMessage",
        data: encodeURI(message) + '&id=' + encodeURI(id),
        success: function () {
            getChat(id);
        }
    });
}

function getChatBox(username) {
    $.get({
        type: "GET",
        url: "chitchat?action=getchatbox&id=" + username,
        success: function (data) {
            makeChatBox($.parseJSON(data));
        }
    });
}

function getChat(id) {

    $.get({
        url: "chitchat?action=chatJSON&id=" + encodeURI(id),
        success: function (data) {
            $("#" + id).find(".chat-ul").html("");
            var jsonData = $.parseJSON(data);
            $.each(jsonData.messages, function (i, item) {
                makeChat(id, item);
            });
        }
    });
}

function makeChatBox(chat) {

    var count = $('.chatbox').length;
    var id = chat.id;

    if ($("#" + id).length > 0 || $(".chatbox").length > 4) {
        return;
    }

    var chatbox = $("<div id='" + chat.id + "' class='chatbox'></div>");
    chatbox.append("<p><input type='hidden' id='chatid' value='" + chat.id + "' /></p>");
    chatbox.append("<p><input type='hidden' class='name' value='" + $("#usernamesession").val() + "' /></p>");

    var chathead = $("<div class='chathead' ></div>");
    chathead.append("<h3 class='pull-left'>" + chat.name + "</h3> <button class='close' class='pull-right'>X</button>");
    chatbox.append(chathead);

    var chatHTML = $("<div class='chat' ><div class='chat-history'><div class='chat-ul'></div></div></div>");
    chatbox.append(chatHTML);

    var inputfield = $("<div class='inputfield'><p><input class='textfield' type='text' placeholder='text here...'></input><input type='submit' class='sendmessage' data-id='" + chat.id + "'></input></p></div>")

    chatHTML.append(inputfield);

    var rightValue = (count * 300) + 50;

    chatbox.css('right', rightValue + "px");

    $("body").append(chatbox);

    $.each(chat.messages, function (i, item) {
        makeChat(id, item);
    });
    chatbox_array.push(id);
}


function makeChat(id, chat) {

    var position = "left";
    var float = "";
    var align = "align-left";
    var fix = "";
    var onlineleft = "<i class='fa fa-circle left'></i>";
    var onlineRight = "";

    var username = $("#" + id).find(".name").val();

    if (chat.person.username === username) {
        position = "right";
        float = "float-right";
        align = "align-right";
        fix = "class='clearfix'";
        onlineleft = "";
        onlineRight = "<i class='fa fa-circle right'></i>";
    }


    var list = $("<li " + fix + "></li>");
    var messageData = $("<div class='message-data " + align + "'></div>");
    var message = $("<div class='message " + position + "-message " + float + "'></div>");

    //messageData.attr("class", "message-data");

    //banner above message//
    var spanData = $("<span class='message-data-name'></span>");
    spanData.html("<p class='clearspacing'>"+onlineleft+" " + chat.person.username + " "+ onlineRight +"</p> ");


    //actual message//
    message.html("<p class='clearspacing minwidth'>" + chat.message + "</p>");

    messageData.append(spanData);



    list.append(messageData);
    list.append(message);

    var messageDiv = $("#" + id).find(".chat-ul");
    messageDiv.append(list);
}
