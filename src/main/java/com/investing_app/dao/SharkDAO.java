package com.investing_app.dao;

import com.investing_app.entities.Shark;

import java.util.List;

public interface SharkDAO {
    Shark createSharkProfile(Shark o);

    Shark getSharkByUsername(String username);

    List<Shark> getAllSharks();
}
