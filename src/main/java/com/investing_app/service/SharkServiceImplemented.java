package com.investing_app.service;

import com.investing_app.customexceptions.*;
import com.investing_app.dao.SharkDAO;
import com.investing_app.entities.Shark;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class SharkServiceImplemented implements SharkService {

    SharkDAO sharkDAO;

    public SharkServiceImplemented (SharkDAO sharkDAO) {
        this.sharkDAO = sharkDAO;
    }

    @Override
    public Shark createSharkProfileService(Shark o) {
        List<Shark> sharks = this.sharkDAO.getAllSharks();

        if ((o.getFirstName().length() == 0) || (o.getLastName().length() == 0) || (o.getBusinessName().length() == 0)
                || (o.getUsername().length() == 0) || (o.getPassword().length() == 0) || (o.getRole().length() == 0))
            throw new NullValue("You must enter a value!");
        if ((o.getFirstName().length() > 20) || (o.getLastName().length() > 20) || (o.getBusinessName().length() > 20)
                || (o.getUsername().length() > 20) || (o.getPassword().length() > 30) || (o.getRole().length() > 10))
            throw new TooManyChar("You are exceeding the value length");
        if (o.getUsername().length() < 5)
            throw new UsernameTooShort("Username must be at least 5 characters!");
        if (o.getPassword().length() < 8)
            throw new PasswordTooShort("Password must be at least 8 characters!");
        if (!Pattern.matches("^[a-zA-Z]*$", o.getFirstName()) ||
                !Pattern.matches("^[a-zA-Z]*$", o.getLastName()) ||
                !Pattern.matches("^[a-zA-Z]*$", o.getRole()))
            throw new IncorrectDataType("Input type not allowed");
        for (Shark _shark : sharks) {
            if (Objects.equals(_shark.getUsername(), o.getUsername()))
                throw new UsernameTaken("That username is already taken! Please try again.");
        }
        return this.sharkDAO.createSharkProfile(o);
    }


    @Override
    public Shark sharkLoginService(String username, String password) {
        Shark shark = this.sharkDAO.getSharkByUsername(username);
        if ((username.length() > 20) || (password.length() > 30))
            throw new TooManyChar("You are exceeding the value length");
        if ((username.length() == 0) || (password.length() == 0))
            throw new NullValue("You must enter a value!");
        if (!Objects.equals(shark.getUsername(), username) || (!Objects.equals(shark.getPassword(), password)))
            throw new UsernameOrPasswordIncorrect("Either your username or password or both are incorrect!");
        return shark;
    }
}
