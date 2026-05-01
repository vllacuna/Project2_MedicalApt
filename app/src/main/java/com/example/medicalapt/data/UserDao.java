package com.example.medicalapt.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(User user);

    @Query("SELECT * FROM users WHERE user_id = :userId LIMIT 1")
    User getUserById(int userId);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User getUserByCredentials(String username, String password);

    @Query("SELECT COUNT(*) FROM users")
    int countUsers();
}
