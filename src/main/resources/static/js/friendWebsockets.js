const MESSAGE_TYPE = {
  CONNECT: 0,
  SCORE: 1,
  UPDATE: 2
};

let conn;
let username;
let id;

// Setup the WebSocket connection for live updating of scores.
const setup_live_scores = () => {
  
	// TODO Create the WebSocket connection and assign it to `conn`
	conn = new WebSocket("ws://localhost:4567/friends");

  conn.onerror = err => {
    console.log('Connection error:', err);
  };

  conn.onmessage = msg => {
    const data = JSON.parse(msg.data);
    switch (data.type) {
      default:
        console.log('Unknown message type!', data.type);
        break;
      case MESSAGE_TYPE.CONNECT:
      
    	  conn.send(JSON.stringify(getUserPayload()));
    	  
        break;
      case MESSAGE_TYPE.UPDATE:
        // TODO Update the relevant row or add a new row to the scores table
          $("#output").empty();
          let db = JSON.parse(data.payload.scoresCache);
          for (let i of Object.keys(db)) {
              $("#output").append('<li>' + 'id: ' + i  + ' has a score: ' + db[i] + '</li>');
          }
        break;
    }
  };
}

function newFriendRequest(){
	conn.send(JSON.stringify(friendRequestPayload()));
}

// Should be called when a user makes a new guess.
// `guesses` should be the array of guesses the user has made so far.
const new_send = profile => {
  // TODO Send a SCORE message to the server using `conn`
	
	conn.send(JSON.stringify(profile));
}
