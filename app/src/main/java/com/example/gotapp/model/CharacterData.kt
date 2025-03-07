package com.example.gotapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
// model de la bbdd
@Entity(tableName = "characters")
data class CharacterData(
    @PrimaryKey 
    val id: Int,
    
    @ColumnInfo(name = "full_name")
    val fullName: String,
    
    @ColumnInfo(name = "title")
    val title: String,
    
    @ColumnInfo(name = "family")
    val family: String,
    
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    
    @ColumnInfo(name = "is_dead")
    val isDead: Boolean = false
)