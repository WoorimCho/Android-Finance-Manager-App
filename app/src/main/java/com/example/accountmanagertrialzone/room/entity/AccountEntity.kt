package com.example.accountmanagertrialzone.room.entity;

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntity (

//    @ColumnInfo(name = "account_name")
    val name:String,
//    @ColumnInfo(name = "total_account_amount")
    val amount:Int,
//    @ColumnInfo(name = "account_available_amount")
    val availableAmount:Int,
//    @ColumnInfo(name = "account_invested_amount")
    val investedAmount:Int,
//    @ColumnInfo(name = "account_notes")
    val notes:String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

    )

//{
//    private String name;
//    private int amount;
//
//    public Account (String name, int amount){
//        this.name = name;
//        this.amount = amount;
//    }
//
//    public void deposit (int amount){
//        this.amount += amount;
//    }
//
//    public void withdraw (int amount){
//        this.amount -= amount;
//    }
//
//    public String getName(){
//        return name;
//    }
//
//}
