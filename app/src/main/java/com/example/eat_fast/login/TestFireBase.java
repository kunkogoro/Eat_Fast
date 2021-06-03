package com.example.eat_fast.login;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestFireBase {

    FirebaseDatabase nodeRoot;
    DatabaseReference reference;

    public TestFireBase() {
        nodeRoot = FirebaseDatabase.getInstance();

        reference = nodeRoot.getReference("user");

        reference.child("student").child("lop1").setValue("lop1");

    }

    public static void main(String[] args) {
        TestFireBase testFireBase = new TestFireBase();
    }



}
