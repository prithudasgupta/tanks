# cs0320 Term Project 2018

**Team Members:** 
Gokul Ajith
Michael Bardakji
Prithu Dasgupta
Ben Deckey

**Team Strengths and Weaknesses:**
Strengths:
Gokul - Databases, Front-End/Graphics, Debugging.
Michael - AI, Optimizations, Testing, SQL.
Prithu - Algorithms, Project Design, Testing.
Benjamin - Algorithms, Optimizations, Front-End/Graphics.

Weaknesses:
Gokul - Testing, Project Organization.
Michael- Debugging, Abstractions.
Prithu - Debugging, SQL.
Ben - Test Writing, Generics.

**Project Idea(s):** 
### Idea 1 Music Recommendation Platform:

*Rejected by Joe - not unique enough from existing apps (e.g. Spotify, Pandora).*

Describe what problem the idea is attempting to solve?
While many music platforms allow users to stream music and can give recommendations, there is rarely a direct option where the user can upload favorite songs and have the application return a playlist of recommended songs. Our goal is to feature this capability, along with the ability for users to create and share playlists.
One of our major competitors, Spotify, has many complaints regarding its most popular recommendation playlist titled Discover Weekly. We aim to improve upon this, utilizing algorithms to provide both personalized and themed playlists.

How will project solve them?
Algorithms will be written that utilizes data collected from the user’s music preferences. This way, the application will be consistently improving its suggestions. This will also be complemented with an algorithm that can also find lesser known music based on music types the user listens to.

Critical features you will need to develop?
-Automatic genre and related artist identification.
-Getting the database filled with songs legally.
-Algorithms that store and recommend songs based on individual's music data.

Why those features are being included?
-In order to probably predict the user’s music taste, we have to have
some way of identifying the type of music that the user listens to.
-We must have some sort of way to get the available songs legally, otherwise we will have no data to work with.
-We need to have multiple algorithms because each music genre should 
be  compared and ranked differently. 
What you expect will be most challenging about each feature?
The most challenging aspect of most of the features will be runtime. There are so many songs available and looking through each available song will be a hassle. We need to have some sort of way to stop our online search for songs to minimize runtime. 
Another challenging aspect of the recommendation algorithm is deciding which aspect should be prioritized in searching. If it is noted that many users who like one artist listen to another, should the search be prioritized on this related artist? Or should the search focus more on genre. Different cases will need to be analyzed to figure out which type of search is most efficient.

### Idea 2: Tanks

*Approved by Joe, provided that you differentiate it from the Wii version through an AI, map generation, etc.*

Describe what problem the idea is attempting to solve?
Tanks was a classic Wii game but unfortunately I broke my Wii... In all seriousness, the game was great and is very addictive because the AI of the game gets increasingly harder and every game is always different.

How will project solve them?
Create a game that is refreshing to come back to/ do no need to spend a lot of time on the game to advance. The user will be able to input difficulty as they pass through the levels, so they do not need to restart after coming back.

Critical features you will need to develop?
-Map Generation
-AI for enemies

Why those features are being included?
-With a random map generator, it will be less likely for users to
Play on the same map twice (more exciting). 
-You need to have some kind of interaction with the game. This will be done with AI to program bots/enemies in the game.

What you expect will be most challenging about each feature?
-Making sure that a randomly generated map is valid such as it does not prevent a user from winning.
-Programming the edge cases for the Ai bot.

### Idea 3 Venmo Task App: 

*Rejected by Joe. This idea has been done in the past and does not go well (JJ does not like and it does not provide interesting design decisions).*

Describe what problem the idea is attempting to solve?
Many people have numerous tasks to perform but too little time to perform them. While some people are too busy to perform these tasks, they should be able to post these tasks to the Internet so that other people can do them. Simultaneously, people with free time on their hands and who need money can accomplish these posted tasks for compensation.
 How will project solve them?
As a result, we plan on creating an application where users can post tasks that they would like done. Other users will be notified of current tasks available and will be able to accept them, perform them, and receive compensation through Venmo. We will use machine learning, radius search, nearest neighbor search, and shortest path search to find people who would most likely be able to perform these tasks. We will also have a ranking system for the top task posters and task acceptors. 
Critical features you will need to develop?
	-Identifying closeby friends within some radius.
	-Gamification of task completion.
	-Getting the Venmo API
Why those features are being included?
	-You don’t want a user to perform a task for someone who lives 
	Far away, which is why we need to limit use interaction to within some radius.
	-Once a user gets his task performed, he/she will have the option to 
	Rate whoever performed the task.
	-we need the venmo api to make it possible for users to use 
	Our app through venmo.
What you expect will be most challenging about each feature?
	-Accessing the gps coordinates of a user.
	-Getting our app working with venmo (the back-end).


**Mentor TA:** _Put your mentor TA's name and email here once you're assigned one!_

## Meetings
_On your first meeting with your mentor TA, you should plan dates for at least the following meetings:_

**Specs, Mockup, and Design Meeting:** _(Schedule for on or before March 16)_

**4-Way Checkpoint:** _(Schedule for on or before April 23)_

**Adversary Checkpoint:** _(Schedule once you are assigned an adversary TA)_

## Project Specs, Mockup, and Design (March 16)
_A link to your specifications, mockup, and your design presentation will go here!_

## How to Build and Run
_A necessary part of any README!_
