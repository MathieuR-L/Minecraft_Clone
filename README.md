# Minecraft Clone

A Minecraft Clone made with love !
![Portfolio](res/main_menu.png)

### Built With

- ![Java]
- ![OpenGL](https://img.shields.io/badge/OpenGL-%23FFFFFF.svg?style=for-the-badge&logo=opengl)

## Getting Started

### Installation

1. Clone the repo

```sh
git clone https://github.com/MathVK/Minecraft_Clone.git
```

2. Open the project in an IDE that supports Maven to install dependencies.

3. Or if you want install them with the following commands:
```sh
mvn clean package
```

### How to play ?

The game can handle two multiplayer modes. Either with a local server that runs on your machine or you can host the game server in a remote host.

1. Configure the client configuration server IP in `res/client_config.json`
```
{
    "ip": "localhost"
    "port": 50000
}
```

2. Compile the server jar with Maven.

```sh
$ mvn clean package
$ java -jar target/minecraft-clone-1.0-SNAPSHOT-jar-with-dependencies.jar
Serveur en écoute sur le port 50000...
```

3. Run the client with the `ClientMain.java` class
4. Click the Play button and you're done ! ✨ 

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

[Java]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white