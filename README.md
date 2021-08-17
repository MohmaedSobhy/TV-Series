TV-Series Android Studio App
this app show data from EpisoDate website in tv series app tools i used to build this app
1- Retrofit api
2- RecyclerView
3- View Model
4- RoomDataBase
5- FireBase
6- materail Design

How app it Work:
user muste be create accounte(username,email,phone,password) or login(email,password) if have account
if user forget password will enter email and he can change password

when open app Main Home
it's and navigation drawer (home,search,whisListed,setting)

home fragement show :
List Series when click any item of list
go to anthor Details Page:show Details of series (image,title,description,country,network) and two button (add to favourite,visite Website) v
search fragement: Containing search bar when user search by name of series show item
and if he click to this item go to Details Page

Favourite fragement : show list of favourite items

setting Fragement : show image of user and name,emial,phone;
user can change password or logout or change the image


package i used :
material :
implementation 'com.google.android.material:material:1.3.0'

Retrofit: implementation 'com.squareup.retrofit2:retrofit:2.9.0' implementation 'com.squareup.retrofit2:converter-gson:2.4.0'

Room DataBase:
def room_version = "2.3.0"
implementation "androidx.room:room-runtime:$room_version"
annotationProcessor "androidx.room:room-compiler:$room_version"

Circle Image view :
implementation 'de.hdodenhof:circleimageview:3.1.0'
ViewModel :

def lifecycle_version='2.1.0'
implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
implementation 'com.github.bumptech.glide:glide:4.12.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'ate
