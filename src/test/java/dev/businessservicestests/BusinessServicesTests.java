package dev.businessservicestests;

import com.investing_app.dao.BusinessDAO;
import com.investing_app.customexceptions.BusinessNotFound;
import com.investing_app.customexceptions.UsernameOrPasswordError;
import com.investing_app.entities.Business;
import com.investing_app.dao.BusinessDAOImp;
import com.investing_app.service.BusinessServices;
import com.investing_app.service.BusinessServicesImp;
import org.testng.annotations.Test;

public class BusinessServicesTests {

    static BusinessDAO businessDAO = new BusinessDAOImp();
    static BusinessServices businessServices = new BusinessServicesImp(businessDAO);

    @Test(expectedExceptions = BusinessNotFound.class, expectedExceptionsMessageRegExp = "Business not found")
    void badIdForBusiness(){
        Business anotherBusiness = businessServices.getBusinessByIdService(2345);
    }

    @Test(expectedExceptions = UsernameOrPasswordError.class, expectedExceptionsMessageRegExp = "Username or Password are incorrect")
    void badLogin(){
        Business newBusiness = businessServices.getBusinessLoginService("Lex", "Luther");
    }
}
