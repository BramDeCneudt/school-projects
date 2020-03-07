var wall = new Freewall(".grid");
$("body").ready(function () {
    wall.reset({
        selector: '.grid-item',
        draggable: false,
        animate: false,
        cellW: '200',
        cellH: 'auto',
        onResize: function () {
            wall.fitWidth();
        }
    });


});

$(".photo").on("load", function() {
    wall.fitWidth();
});