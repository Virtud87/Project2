package com.investing_app.service;

import com.investing_app.entities.Business;

import java.util.List;

public interface BusinessServices {
    Business getCreateBusinessService(Business business);

    Business getBusinessByIdService(int id);

    List<Business> getAllBusinessesService();

    Business getBusinessLoginService(String username, String password);
}
