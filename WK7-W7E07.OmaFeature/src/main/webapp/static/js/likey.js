function meGusta(event, formId, id) {
    event.preventDefault();

    var form = $("#" + formId);
    $.ajax({
        "type": "POST",
        "url": form.attr('action'),
        "data": form.serialize(),
        "success": function (result) {
            $("#like-text-" + id).text(result + " likes");
            $("#like-link-" + id).hide();
        }
    });
}

function initMeGustas() {
    var list = [];
    $(".like-text").each(function () {
        list.push($(this).attr("id").replace("like-text-", ""));
    });

    $.ajax({
        "url": '/likes',
        "data": {"ids": list},
        "traditional": true,
        "success": function (result) {
            $.each(result, function (key, value) {
                $("#like-text-" + key).text(value + " likes");
            });
        }
    });
}

initMeGustas();