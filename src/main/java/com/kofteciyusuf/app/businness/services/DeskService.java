package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.entities.Desk;

import java.util.List;

public interface DeskService {

    Desk addDesk(Desk desk);

    List<Desk> getAllDesks();
    Desk deleteDesk(String deskId);

}
