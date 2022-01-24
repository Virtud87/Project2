package com.investing_app.app;

import com.investing_app.controllers.SharkController;
import com.investing_app.dao.PitchDAO;
import com.investing_app.dao.PitchDAOImplemented;
import com.investing_app.dao.SharkDAO;
import com.investing_app.service.SharkService;
import com.investing_app.service.SharkServiceImplemented;
import com.investing_app.controllers.PitchController;
import com.investing_app.dao.SharkDAOImplemented;
import com.investing_app.service.PitchService;
import com.investing_app.service.PitchServiceImplemented;
import io.javalin.Javalin;
import com.investing_app.dao.BusinessDAO;
import com.investing_app.dao.BusinessDAOImp;
import com.investing_app.dao.CommentingDAO;
import com.investing_app.dao.CommentingDAOImp;
import com.investing_app.controllers.BusinessController;
import com.investing_app.controllers.CommentingController;
import com.investing_app.service.BusinessServices;
import com.investing_app.service.BusinessServicesImp;
import com.investing_app.service.CommentingServices;
import com.investing_app.service.CommentingServicesImp;

public class App {
    public static void main(String[] args) {
        // app variable to creates Javalin connection
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        // need to do dependency injection for DAO & service layer here
        BusinessDAO businessDAO = new BusinessDAOImp();
        BusinessServices businessServices = new BusinessServicesImp(businessDAO);
        BusinessController businessController = new BusinessController(businessServices);

        CommentingDAO commentingDAO = new CommentingDAOImp();
        CommentingServices commentingServices = new CommentingServicesImp(commentingDAO);
        CommentingController commentingController = new CommentingController(commentingServices);

        // Shark
        SharkDAO sharkDAO = new SharkDAOImplemented();
        SharkService sharkService = new SharkServiceImplemented(sharkDAO);
        SharkController sharkController = new SharkController(sharkService);

        // Pitch
        PitchDAO pitchDAO = new PitchDAOImplemented();
        PitchService pitchService = new PitchServiceImplemented(pitchDAO);
        PitchController pitchController = new PitchController(pitchService);

        // business routes
        app.get("/business/{id}", businessController.getBusiness);
        app.get("/businesses", businessController.getAllBusinesses);
        app.post("/business/login", businessController.getBusinessLogin);
        app.post("/business/create", businessController.createBusiness);

        // comments routes
        app.get("/commenting/{id}", commentingController.getComment);
        app.get("/commentingAll", commentingController.getAllComments);
        app.post("/commenting/create", commentingController.createComment);


        // Shark routes
        app.post("/shark", sharkController.createSharkProfile);
        app.post("/shark/login", sharkController.sharkLogin);

        //Pitch routes
        app.post("/pitch", pitchController.createPitch);
        app.get("/pitches", pitchController.viewPitches);
        app.patch("/offer", pitchController.makeOffer);
        app.patch("/accept/{pitchId}", pitchController.acceptOffer);

        // start app connection
        app.start();
    }
}

