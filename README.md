Welcome to Rush Management UI

You can also use other versions with the available maven profiles: myfaces22, myfaces23, mojarra23

`mvn clean jetty:run -Pmyfaces22`

`mvn clean jetty:run -Pmyfaces23`

`mvn clean jetty:run -Pmojarra22`

`mvn clean jetty:run -Pmojarra23`

### Server Port

By default the application runs on port 8080 but if you would like to use a different port you can pass `-Djetty.port=5000` like:

`mvn clean jetty:run -Djetty.port=5000`