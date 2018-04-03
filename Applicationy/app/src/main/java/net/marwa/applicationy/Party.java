package net.marwa.applicationy;


import com.google.firebase.auth.FirebaseUser;

public class Party {
    FirebaseUser user;
    String date;
    String hall;
    String decor;
    String  band;
    String clown;
    String custom;
    String dj;
    String appetizer;
    String mainCourse;
    String dessert;
    String cake;
    String hair;
    String photo;
    String makeup;
    String  singer;


    public Party(){

    }

    public Party(FirebaseUser user,String date,String hall, String decor, String band, String clown, String custom, String dj, String appetizer,String mainCourse,String dessert,String cake, String hair, String photo, String makeup, String singer){
this.user=user;
        this.hall=hall;
        this.decor=decor;
        this.band=band;
        this.clown= clown;
        this.custom= custom;
        this.dj=dj;
        this.appetizer= appetizer;
        this.mainCourse= mainCourse;
        this.dessert= dessert;
        this.cake= cake;
        this.hair= hair;
        this.photo= photo;
        this.makeup=makeup;
        this.singer=singer;
this.date=date;
    }

}
