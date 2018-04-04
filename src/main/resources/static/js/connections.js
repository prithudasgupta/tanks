
function instructions() {
    alert("Bacon Instructions: \n This interface will allow you to connect actors via their bacon trails. That is " +
        "a connection they have with other actors from starring in the same films as them. Type in each actor into " +
        "the text boxes in the browser and the path will display on the screen. Click on the suggestions generated to" +
        " fill in the text box. Once the list is generated you can visit that actor/movie page to see the stars in a movie" +
        " or movies the actor has acted in. All names and movie titles are clickable! Have fun!" +
        " \n The database of actors must be loaded into the system using the command line." +
        " \n Click the connect button on actor and movie pages to return to the connection screen." +
        " \n Note: If the suggestion box stays up click the text box again and it will dissappear :)");
}


$(document).ready(function(){

    let url = window.location.href;
    if (url.includes("/actor/")) {
        let last = url.lastIndexOf("/");
        let namehyp = url.substr(last + 1, url.length-1);
        let name = namehyp.replace(/-/g, ' ');
        $("header.BaconP").html(document.title);
        $.post('/connections', {'actorName': name}, responseJSON => {
            const respObject = JSON.parse(responseJSON);
            let movies = respObject.movies;
            for (let m of movies) {
                let id = m.first;
                let movie = m.second;
                $("#movies1").append('<li class="stars" id= "' + id + '">' + movie + '</li>');
            }
        });
    }

    if (url.includes("/movie/")) {
        let last = url.lastIndexOf("/");
        let namehyp = url.substr(last + 1, url.length-1);
        let name = namehyp.replace(/-/g, ' ');
        $("header.BaconP").html(document.title);
        $.post('/connections', {'filmName': name}, responseJSON => {
            const respObject = JSON.parse(responseJSON);
        let actors = respObject.actors;
        for (let m of actors) {
            let id = m.first;
            let actor = m.second;
            $("#actors1").append('<li class="stars" id= "' + id + '">' + actor + '</li>');
        }
    });
    }

    $("#connect").click(function() {



        $("#connect").prop("disabled",true);

        const  $act1 = document.getElementById("act1").value;
        const  $act2 = document.getElementById("act2").value;

        if($act1.length == 0 || $act2.length == 0) {
            $("#connect").prop("disabled",false);
            document.getElementById("warning").innerHTML = "Please enter actor names.";
            return;
        }


        $("#actFrom").empty();
        $("#actTo").empty();
        $("#movies").empty();
        $('p#warning').empty();
        document.getElementById("warning").innerHTML = "";

        document.getElementById("loading").classList.toggle("show");

        console.log($act1 + " " + $act2)

        $.post('/connections', {
            'act1': $act1, 'act2': $act2}, responseJSON => {


        const respObject = JSON.parse(responseJSON);
        let from = respObject.from;
        let to = respObject.to;
        let movies = respObject.movie;
        let hasSource = respObject.connection;
        let contains = respObject.contains;

        if (hasSource === "false") {
            document.getElementById("loading").classList.toggle("show");
            document.getElementById("warning").innerHTML = "Error: No source";
        } else if (contains === "false") {
            document.getElementById("loading").classList.toggle("show");
            document.getElementById("warning").innerHTML = "Error: Names not in source.";
        } else if (movies.length == 0) {
            let fromName = from[0].second;
            let fromId = from[0].first;
            let toName = to[0].second;
            let toId = to[0].first;
            document.getElementById("loading").classList.toggle("show");
            $("#actFrom").append('<li class="stars" id= "' + fromId + '">' + fromName + '</li>');
            $("#actTo").append('<li class="stars" id= "' + toId + '">' + toName + '</li>');
            document.getElementById("warning").innerHTML = "No Path between names.";
        } else {
            document.getElementById("loading").classList.toggle("show");
            for (let w of from) {
                let actor = w.second;
                let id = w.first;
                $("#actFrom").append('<li class="stars" id= "' + id + '">' + actor + '</li>');
            }
            for (let w of to) {
                let actor = w.second;
                let id = w.first;
                $("#actTo").append('<li class="stars" id= "' + id + '">' + actor + '</li>');
            }
            for (let w of movies) {
                let movie = w.second;
                let id = w.first;
                $("#movies").append('<li class="stars" id= "' + id + '">' + movie + '</li>');
            }
        }
        $("#connect").prop("disabled",false);
    });
    });

    $('#actorList1').on('click', 'li', function() {
        let name = (event.target.id).replace(/\//g, '$');
        let next = url.lastIndexOf("/");
        let newUrl = url.substr(0, next) + "/actor/" + name;
        window.location.replace(newUrl);
    });
    $('#actorList2').on('click', 'li', function() {
        let name = (event.target.id).replace(/\//g, '$');
        let next = url.lastIndexOf("/");
        let newUrl = url.substr(0, next) + "/actor/" + name;
        window.location = newUrl;
    });
    $('#actorList3').on('click', 'li', function() {
        let name = (event.target.id).replace(/\//g, '$');
        let next = url.lastIndexOf("/");
        let newUrl = url.substr(0, next);
        next = newUrl.lastIndexOf("/");
        newUrl = url.substr(0, next);
        newUrl = newUrl + "/actor/" + name;
        window.location = newUrl;
    });
    $('#movieList').on('click', 'li', function() {
        let name = (event.target.id).replace(/\//g, '$');
        let next = url.lastIndexOf("/");
        let newUrl = url.substr(0, next) + "/movie/" + name;
        window.location = newUrl;
    });
    $('#movieList2').on('click', 'li', function() {
        let name = (event.target.id).replace(/\//g, '$');
        let next = url.lastIndexOf("/");
        let newUrl = url.substr(0, next);
        next = newUrl.lastIndexOf("/");
        newUrl = url.substr(0, next);
        newUrl = newUrl + "/movie/" + name;
        window.location = newUrl;
    });

});

