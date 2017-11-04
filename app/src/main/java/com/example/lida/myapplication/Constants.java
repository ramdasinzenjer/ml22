package com.example.lida.myapplication;


public interface Constants {

    //Progress Message
    String LOGIN_MESSAGE="Logging in...";
    String REGISTER_MESSAGE="Register in...";


   //String BASE_URL="http://192.168.1.21/mla/";
   String BASE_URL="http://192.168.1.9/mla/";

    String LOGIN_URL=BASE_URL+"login.php?";
    String REGISTRATION_URL=BASE_URL+"registration.php?";
    String  MLA_URL=BASE_URL+"mlalist.php?";
    String COMPLAINT_REG_URL=BASE_URL+"complaint_reg.php?";

    //Details


    String ID="id";
    String NAME="Name";
    String PASSWORD="Password";
    String EMAIL="Email";
    String LOGINSTATUS="LoginStatus";
    String SMSSERVICE="SmsService";
    String CALLSERVICE="CallService";
    String BOOKINGNUMBER="BookingNumber";
    

    //SharedPreference

    String PREFERENCE_PARENT="Parent_Pref";

   
}
