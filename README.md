# Android Chat App: The Client Side.

## Description
This Android hand-held system based app is a simple version of real time  
chat app, which contains the following functions: Register new user and  
saving his/her information, the username, avatar etc., quick login for re-  
turning user. The chat room supports multi people chatting, when one of  
the user joining or exiting the chat room, all of the other users will receive  
the notification.   
Beyond the regular message text, one can also send the emotion, which stored  
in the default image of emotion package. Above each bubble of message, the  
sending time information is also provided. Technically, for the communication  
between the client and the server, each message contains a flag to indicate its  
message type (register, login, exiting or regular message).  

In this version, the following modifications are made as improvements: 1. More   
friendly login menu as the first activity of opening the app, user now can choose  
his/her username here instead of in the chat room by clicking “manipulation”.  
2. Hided the server setting, including the server ip and the port number, thus  
preventing the manually setting these by user. Because in real case, the server is  
always available with invariable parameters and the clients don’t need to know.

## Outline of the code

#### UserLoginActivity.java
The `UserLoginActivity.java`

#### UserRegisActivity.java
The `UserRegisActivity.java`

#### ChatActivity.java
The `ChatActivity.java`

#### ChatMsgViewAdapter.java
The `ChatMsgViewAdapter.java`

#### ExpressionGvAdapter.java
The `ExpressionGvAdapter.java`

#### Message.java
The `Message.java`

#### User.java
The `User.java`

#### ContentFlag.java
The `ContentFlag.java`

#### MessageDbHelper.java
The `MessageDbHelper.java`

#### UserDbHelper.java
The `UserDbHelper.java`

#### IhandleMessage.java
The `IhandleMessage.java`

#### MessageService.java
The `MessageService.java`

#### RegisterService.java
The `RegisterService.java`

#### UserService.java
The `UserService.java`

#### ExpressionUtil.java
THe `ExpressionUtil.java`

#### FileDealTool.java
The `FileDealTool.java`

#### StreamTool.java
The `StreamTool.java`

#### SystemConstant.java
The `SystemConstant.java`


## Acknowledgement
I would like to give special thanks to Dr. Andy Li, Dr. Kaikai Liu and Dr. Ze Yu (In alphabetical  
order). Specifically, Dr. Li inspired me  interesting of java & Android programming and  
also always tries to help me to form a good habit of learning and programming. Dr. Liu  
and Dr. Yu are always available and willing to give kind help with regarding to my problems  
in the process of my learning.

## References
* Schildt, Herbert. Java: A Beginner's Guide Sixth Edition. McGraw-Hill, Inc., 2014.
* [Adam Porter. Programming Mobile Applications for Android Handheld Systems: Part 1](https://class.coursera.org/androidpart1-004)
* [Adam Porter. Programming Mobile Applications for Android Handheld Systems: Part 2](https://class.coursera.org/androidpart2-003)


## Origins
* [Ying Xu. The Chat App based on Android. 2013.](http://download.csdn.net/detail/jiangliloveyou/6457969)

### Contact me
* yangwenjincshn@gmail.com


