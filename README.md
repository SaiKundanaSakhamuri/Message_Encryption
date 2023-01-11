# MESSAGE ENCRYPTION
## TEAM MEMBERS
[Chakradhar Prathivada](https://github.com/Chakri15099)<br>
[Shruthi Patkur](https://github.com/pshruthi04)<br>
[Sai Lakshmi Kundana Sakhamuri](https://github.com/kundanaSakhamuri98)<br>
## ABSTRACT
Message Encryption is an Android  mobile application where a text  can be encrypted using different algorithms and can be sent over to another app user, where they can decrypt it with a shared key.<br>

## INTRODUCTION TO THE PROBLEM

Users communicate over social media, but messages are not secured when it passes through network. Intruder can access messages easily. We want to secure communication while transmitting the data.

## PROPOSED SOLUTION

So here we proposed a solution where the user message will  be encrypted by choosing the algorithm type from AES, DES, Caesar Cipher and gets decrypted when the receiver uses the shared key.


## FEATURES
1.User Friendly<br>
2.Authentication<br>
3. Responsive Interface<br>
4. Chat Support<br>


## TEST CREDENTIALS
1.User ID: Encry@gmail.com  <br>
  Password:encry@123 <br>
  
2.User ID: Shruthi@gmail.com<br>
  Password: Shruthi@123<br>
  
3.User ID:Chakri@gmail.com<br>
   Password:Chakri@123<br>
 
4.User ID: Sai@gmail.com<br>
   Password:Sai@123 <br>


## STEPS TO USE THE APP
1.Login with the given Test credentials<br>
2.If you are not registered create a new account and then login, You will be directed to the home page<br>
3. Inorder to check the messages go to the view messages button which will display either nowmal messages or encrypted codes which  the user recived.<br>
4. Copy the Encrypted code and click the decrypt button you will be directed to the decryption page. Or you can send a new message <br>
5. Paste the encrypted message here and enter the key that is shared personally. <br>
6. Click the decrypt button to decrypt the coded message. The Decrypted message will be displayed<br>
7. To send a new encrypted message click the encrypted message button which will direct you to the encryption page.<br>
8. Select an algorithm of your choice among the provided algorithms.<br>
9. Now enter a message you want to send and assign a secured key to it.<br>
10. Click the Encrypt button to make your message into an encrypted code.<br>
11. Copy the code and click the send button to send it the other user, you will be directed to send page.<br>
12. Paste the encrypted code in the given box and enter an existing username whom you want to send the message and click send.<br>
13. The message will be sent. In the reciever end to check the messages go to the vieew messages card and follow the 4,5,6 steps mentioned above.<br>

## DESCRIPTION OF PERSISTENT DATA<br>
Usage of  Realtime Database allows operations to execute quickly and also provides great realtime experience that can serve users without compromising on responsiveness.<br>
For our app we opted to maintain our data on Firebase platform.<br>

## API/ LIBRARY USED<br>
1. circleimageview:3.1.0<br>     
2. firebase-auth:21.0.1<br>
3. firebase-database:20.0.4<br>	
4. firebase-storage:20.0.1<br>

## PROBLEMS FACED <br>
Trying to reduce complexity over the activities<br>
Time Management<br>
Framing the Database Structure<br>
Retrieving the required data from the database<br>

## FUTURE IMPROVEMENTS <br>
Ability to create or implement encryption using existing algorithms other than currently specified ones in our app.<br>
Encryption for larger encryptions.<br>
Providing user manual.<br>
Ability to delete the read messages.<br>
Pop Up notifications<br>












