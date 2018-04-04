

function dropdown1() {
    document.getElementById("myDropdown1").classList.toggle("show");
}

function dropdown2() {
    document.getElementById("myDropdown2").classList.toggle("show");
}


$(document).ready(() => {
    $("#act1").on("keyup", function() {
    $('p#warning').empty();
    // If a capital or lowercase letter is pressed, return suggestions
    if ((event.which >= 97 && event.which <= 122) || (event.which >= 65 && event.which <= 90)
        || (event.which == 8) || (event.which == 32)) {
        $("#myDropdown1").empty();
        let $name = document.getElementById("act1").value;
        if ($name != "") {
            $.post('/dropdown', {'name': $name}, responseJSON => {
                const respObject = JSON.parse(responseJSON);
                let suggs = respObject.suggestions;
                for (let w of suggs) {
                    $("#myDropdown1").append('<li>' + w + '</li>');
                }
                $('#myDropdown1 li:gt(4)').remove();
            });
        }
    }
    });
    $("#act2").on("keyup", function() {
        $('p#warning').empty();
        // If a capital or lowercase letter is pressed, return suggestions
        if ((event.which >= 97 && event.which <= 122) || (event.which >= 65 && event.which <= 90)
            || (event.which == 8) || (event.which == 32)) {
            $("#myDropdown2").empty();
            let $name = document.getElementById("act2").value;
            if ($name != "") {
                $.post('/dropdown', {'name': $name}, responseJSON => {
                    const respObject = JSON.parse(responseJSON);
                let suggs = respObject.suggestions;
                for (let w of suggs) {
                    $("#myDropdown2").append('<li>' + w + '</li>');
                }
                $('#myDropdown2 li:gt(4)').remove();
            });
            }
        }
    });
    $('#myDropdown1').on('click', 'li', function() {
        $('input#act1').val(event.target.innerHTML);
        setTimeout(dropdown1(), 500);
    });
    $('#myDropdown2').on('click', 'li', function() {
        $('input#act2').val(event.target.innerHTML);
        setTimeout(dropdown2(), 500);
    });
});



