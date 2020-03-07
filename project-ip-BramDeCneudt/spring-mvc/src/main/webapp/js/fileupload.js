var counterSucces = 0;
var counterFails = 0;
var started = false;

var options = {
    url: "addphoto/json",
    method: "POST",
    addRemoveLinks: "dictRemoveFile",
    paramName: "image", // The name that will be used to transfer the file
    maxFilesize: 100, // MB 
    autoProcessQueue: false,
    uploadMultiple: false,
    autoDiscover: false,
    maxThumbnailFilesize: 100,
    previewsContainer: '#previewzone',
    previewTemplate: $("#preview-template").html(),
    parallelUploads: 1,
    accept: function (file, done) {
        accept(file, done);
    }
};

var dropzone = new Dropzone("div#uploadzone", options);


function accept(file, done) {
    done();
}
//TODO hier miss nog checken op get uploade files??
function send() {

    if (!started) {
        $("#messages").append("<p class='alert alert-info'><strong> uploading " + dropzone.getQueuedFiles().length + " files </strong><p>");
        started = true;
    }

    if (dropzone.getQueuedFiles().length > 0) {
        dropzone.processQueue();
        setTimeout(send, 1000);
    }


}
$("#uploadbutton").click(send);

dropzone.on("addedfile", function (file) {
    var preview = $(file.previewElement);
    preview.attr("class", "preview-template");

    var fileName = file.name;
    var afkorting = "def";

    var type_and_name = fileName.substring(0, fileName.lastIndexOf("."));


    var type_and_name_array = type_and_name.split(/_(.+)/);
    
    if (type_and_name_array.length < 2) {
        name = type_and_name_array[0];
        preview.find(".name").val(name);
        return;
    }

    var name = type_and_name_array[1].replace(/_/g, ' ');
    afkorting = type_and_name_array[0];



    preview.find(".name").val(name);

    var option_type = preview.find('[data-abbreviation="' + afkorting + '"]');

    if (option_type.length > 0) {
        var type = option_type.val();
        preview.find('.type').val(type);
    }

});

dropzone.on("error", function (file, errorMessage, xhr) {
    counterFails++;
    $("#messages").append("<p class='alert alert-danger'>" + file.name + "failed to upload try again </p>");

});

dropzone.on("success", function (file, response) {
    counterSucces++;
    $("#messages").append("<p class='alert alert-info'>" + file.name + " succeeded </p>");

});


dropzone.on("complete", function (file) {
    dropzone.removeFile(file);
});

dropzone.on("queuecomplete", function () {
    $("#messages").append("<p class='alert alert-success'>-------------------------------------DONE-----------------------------------------</p>");
    $("#messages").append("<p class='alert alert-danger'> a total of " + counterFails + " files have failed</p>");
    $("#messages").append("<p class='alert alert-info'> a total of " + counterSucces + " files have succeeded</p>");
    started = false;
    counterFails = 0;
    counterSucces = 0;
});



dropzone.on("sending", function (file, xhr, formData) {
    var preview = $(file.previewElement);
    var name = preview.find(".name").val();
    var description = preview.find(".description").val();
    var type = preview.find(".type").val();
    var project = $("#project").val();

    formData.append("project", project);
    formData.append("id", 0);
    formData.append("name", name);
    formData.append("description", description);
    formData.append("type", type);

});
