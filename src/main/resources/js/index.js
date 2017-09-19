$(function() {
    $.getJSON({
        url: "http://ec2-54-234-72-129.compute-1.amazonaws.com/receipts"
    }).then(function(data) {
        $.each(data, function(key, val) {
            $("<div class='receipt'>").html("<tr>" + "<td class='id'>" + key + "<td>" + val.created + "</td>" + "<td class='merchant'>" + val.merchantName + "</td>" +
                "<td class='amount'>" + val.value + "</td>" + "<td class='tags'>" +
                "<button type='button' class='btn btn-success add-tag'onclick='mfun(this.parentNode.parentNode)'>Add Tag <span class='badge badge-danger'>+</span></button>" + "</td></tr>").appendTo(".table tbody");
        });
    });
});

function myfunc() {
    var $merchant = $("#merchant").val();
    var $amount = $("#amount").val();
    var receipt = {
        "merchant": $merchant,
        "amount": $amount,
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'

        },
        type: "POST",
        url: 'http://ec2-54-234-72-129.compute-1.amazonaws.com/receipts',
        data: JSON.stringify(receipt),
        dataType: 'application/json',
        success: function() {
            console.log('success');
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
};


function mfun(elt) {
    var element = $(elt).find(".tags");
    $("<input type='text' class='tag_input'></input><span onclick='mremove(this, this)' class='badge badge-danger'>X</span>'").appendTo(element);
    $('.tag_input').keypress(function(e) {
        if (e.which == 13) {
            putFunction($('.tag_input'), elt);
        }
    });
};

function mremove(child, parent) {
    if (child == parent) {
        var pr = parent.parentNode.children[1];
        pr.remove();
        child.remove();
    } else {
        child.remove();
        parent.remove();
    }
}

function mremove2(child, parent) {
    parent.remove();
}

function putFunction(elem, parent) {
    var id = $(parent).find(".id").text();
    var name = elem.val();

    mremove(elem, elem.next('span'));
    $("<span class='badge badge-pill badge-info'></span>").html('' + elem.val() + "  " +  " </input><span onclick='mremove2(this, this.parentNode)' class='badge badge-danger'>X</span>").appendTo(parent.childNodes[4]);

    $.ajax({
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        url: 'http://ec2-54-234-72-129.compute-1.amazonaws.com/tags/' + name,
        type: 'PUT',
        data: id,
        dataType: 'application/json',
        success: function(data) {
            alert('Load was performed.');
        },
        error: function(message) {
            console.log("there was an error");
            console.log(message);
        }
    });
}
