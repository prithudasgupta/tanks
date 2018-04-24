<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- In real-world webapps, css is usually minified and
         concatenated. Here, separate normalize from our code, and
         avoid minification for clarity. -->
    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/html5bp.css">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="image" href="/sprites/tank.png">
    <link rel="image" href="/sprites/bullet.png">
    <link rel="image" href="/sprites/tTreads.png">
    <link rel="image" href="/sprites/tank_cannon.png">
    <link rel="image" href="/sprites/wall.png">
    <link rel="image" href="/sprites/pothole.png">
    <link rel="image" href="/sprites/breakable.png">
    <link rel="image" href="/sprites/freeSpace.png">
    <link rel="image" href="/sprites/explo1.png">
    <link rel="image" href="/sprites/explo2.png">
    <link rel="image" href="/sprites/explo3.png">
  </head>
  <body>

     ${content}
     <!-- Again, we're serving up the unminified source for clarity. -->
     <script src="/js/jquery-2.1.1.js"></script>
     <script src="/js/jquery-3.1.1.js"></script>
     <script src="/js/sprite.js"></script>
     <script src="/js/collision.js"></script>
     <script src="/js/game.js"></script>

  </body>
  <!-- See http://html5boilerplate.com/ for a good place to start
       dealing with real world issues like old browsers.  -->
</html>
