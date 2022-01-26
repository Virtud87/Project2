package com.investing_app.service;

import com.investing_app.entities.Shark;

import java.util.List;

public interface SharkService {
    Shark createSharkProfileService(Shark o);

    Shark sharkLoginService(String username, String password);
}
