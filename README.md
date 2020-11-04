# Chat System (mid-term assignment)
## Chat System 2.0: this app is an application which is work for clients communicate by TCP protocol and multiple thread.
###    1.Client package:
        There are two classes of Client which are Client1 and Client2. 
        These classes are client server. 
        They all can connect to Server to communicate each other by TCP protocol.
        They all need login and confirmed by Server before connection.
        There are two thread that ones is work for send and one is work for get message from Server.
        They cannot visit database directly without Server, this is for security of system.
###    2.Data package:
        There are three classes for data class. 
        These class make code more reusable.
        1) ChatMessage.class:
            It is working for packaging chat message data to ChatMessage.
            There are many public functions such as constructor, getter and setter.
        2) UserInformation.class:
            It is working for packaging user information data to UserInformation
        3) Database.class:
            It is working for communicating with databases which are messages.csv and user.csv
            It can save message data and user information data into the database.
            It can get message data and user information data from the database.
###    3.Server package:
        There is a Server class in this package.
        Server can communicate with every client by TCP pipe.
        There are two thread, one is for send messages to clients, and one is for get messages from clients. 
        It can get and save message and user information data into the database by some class which are classes from Data package.
        It send all history chat message to clients.
        It send unread chat message to clients.
        It can check user login to connect server security.
        It get messages which are sent by clients and save them into database and send them to all clients.
###    4.resources:
        There are three files:
        1) message.csv: 
            It is databese which save all chat messages for system.
        2) user.csv:
            It is database which save all information of users.
        3) UserInterface.html:
            This is a web page for web interface development in futrue.
###    5.test:
        This package test all class in data package.  
          
