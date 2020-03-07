//dit is onload document voor index is nodig voor verschillende opdrachten, niet wegdoen!
document.body.onload = onloadBody;

function onloadBody() {
    openSocket();
    getPersons();
    setInterval(refreshChatBoxes, 10000);
}

//sluit de socket als de pagina wordt gerefreshed of je weg gaat van de pagina, werkt niet altijd! console log wordt daarom bijgehouden
//werkt bij: chrome, firefox
window.onbeforeunload = function() {
    closeSocket();
    console.log("websocket closed");
};