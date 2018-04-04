/*
 * This javascript will keep track of variables on the autocorrect page and
 * suggest words from the corpus inputted at the REPL.
 */

const $select = $(".0-15");
for (i=0;i<=15;i++) {
    $select.append($('<option></option>').val(i).html(i))
}


function setDefaults() {
    $('#pre').val("on");
    $('#ws').val("on");
    $('#led').val("2");
}

let dict = false;

function addDict() {
    if (dict === false) {
        dict = true;
        $.post('/suggestions', {'dict': 'on'},responseJSON => {
    });
}

}

// Waits for DOM to load before running
$(document).ready(() => {
    
    $("#tbox").on("keyup", function() {

        // If a capital or lowercase letter is pressed, return suggestions
        if ((event.which >= 97 && event.which <= 122) || (event.which >= 65 && event.which <= 90)
            || (event.which == 8) || (event.which == 32))

        {

            $("#suggestions").empty();
            // set the values for the different types of searches and the previous word
            let pref = false,
                white = false,
                smart = false,
                led = 0,
                prev = "";


            // get all the elements needed for the search
            const $prefix = document.getElementById("pre").value;
            const $whitespace = document.getElementById("ws").value;
            const $led = document.getElementById("led").value;
            const $text = document.getElementById("tbox").value;
            const $smart = document.getElementById("smart").value;

            if ($text === "") {
                return;
            }

            // $('#smart').val("on");
            // console.log(document.getElementById("smart").value);
            if ((!($text.endsWith(" ")))) {
                // fix the text so the spaces are removed
                let list = $text.replace(/\s+/g, ' ').split(" ");

                // check the length and make sure a empty space wasnt recorded for the last word
                if (list[list.length - 1] != "") {
                    word = list[list.length - 1];
                    if (list.length >= 2) {
                        prev = list[list.length - 2];
                    }
                }
                word = word.toLowerCase();
                prev = prev.toLowerCase();


                if ($prefix == "on") {
                    pref = true;
                } else {
                    pref = false;
                }

                if ($smart == "on") {
                    smart = true;
                } else {
                    smart = false;
                }

                if ($whitespace == "on") {
                    white = true;
                } else {
                    white = false;
                }

                $.post('/suggestions', {
                    'word': word, 'prev': prev,
                    'whitespace': white, 'led': $led, 'prefix': pref, 'smart': smart
                }, responseJSON => {

                    const respObject = JSON.parse(responseJSON);
                let list = respObject.suggestions;
                for (let w of list) {
                    $("#suggestions").append('<li>' + w + '</li>');
                }
                $('#suggestions li:gt(4)').remove();
            });
            }
        }
    });

    $('#suggestions').on('click', 'li', function() {
        let lastIndex = $('textarea#tbox').val().lastIndexOf(" ");
        let str = $('textarea#tbox').val().substring(0, lastIndex);
        $('textarea#tbox').val(str + " " + event.target.innerHTML);
    });
});
