# MyCarsSixt
<table>
<tr>
<td>
  In this application, cars can be listed, viewed on the map, and be added to favorites. Extra libraries such as Retrofit, Room, Lottie, Dagger-Hilt2 were used in the project. An architecture was made for the network structure and the application was written with a clean architecture.
</td>
</tr>
</table>

## Mobile Application

### Demo


https://user-images.githubusercontent.com/59707019/197070220-50ae25bd-d5ae-4455-a61e-dc332a3cd6dc.mov



### Cars Page
All cars are listed downward. You can learn the details by clicking on it.
<br>
<br>
<img src="https://user-images.githubusercontent.com/59707019/197068170-5f35c772-bbab-4073-80f4-789d555c34a1.jpg" width="200" height="400">

### Cars on Map Page
Car icons show available cars. Introductory information can be obtained by clicking on the marker. If you click on the box that appears, details about the car can be learned from here.
<br>
<br>
<img src="https://user-images.githubusercontent.com/59707019/197068626-82402345-da20-4ff8-885e-6ac68853f046.jpg" width="200" height="400">

### Cars Detail
The details about the cars are shown. From the top right, the car can be added to favorites.
<br>
<br>
<img src="https://user-images.githubusercontent.com/59707019/197068829-21f838c5-89ac-4f79-a0bb-3a29a08f653b.jpg" width="200" height="400">

### Profile Page
The Profile page contains the username and user's favorite cars.
<br>
<br>
<img src="https://user-images.githubusercontent.com/59707019/197068975-d06577dd-6526-460f-9958-bfc99ec1a680.jpg" width="200" height="400">

## Development

Dagger hilt was used as dependency injection. Lottie is used to show animation. Animation kicks in when the internet request is made. In case of delay in internet request, user experience is increased with animation. Network requests were made with retrofit. Call adapter is added while making network requests. Thanks to this call adapter, http codes returned from internet requests have been arranged and error codes have been translated into classes defined in the application. According to the errors received, the dialog box is shown to the user. Clean architecture is used from the application architecture.
<br>
<br>
<img src="https://user-images.githubusercontent.com/59707019/197069276-2f8efaf4-986a-4311-adac-9a9c1a47e9f9.PNG" width="800" height="400">
<br>
<br>
The architecture is made as shown in the photo. I created the architecture in the style I always use in my own projects. I wrote my own base classes. I added extension functions and made the connection between viewmodel activity or fragment with ViewState.

I have uploaded all the files of the application. At this time, it was uploaded in google map key. The key currently in the files is old. The new key is in the file in the mail.

Dogukan Ali Gundogan




