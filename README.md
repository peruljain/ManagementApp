# **Entry Management App**

## The Goal :

An Android Application for collecting the information of visitor and host so that there is a record of every visitor in Office.This App will be used in various cases . For Example - An appointment app which will be used by doctors also for track the record of appointment.This Application will send message to host when user checksIn and when user checksout the user will get complete information of appointment including checkIn and checkOuttime.

### work flow of App

1) User click On App icon
2) Splash Screen will open
3) Visitor will enter information as Given below

![Test Image 1](Screenshot_20191127-162857_innovaccer.jpg)

4) when user click on checkIn button , this will send request to the server(https://github.com/peruljain/management_app_server) and following information will send to the Host.

**Information to Host**
Name
Email
Phone
CheckIn-Time
CheckOut-Time

All above information will be saved in django server.

5) After Clicking on check-In user will get this Screen.
