package com.cafeordertracking.app.businness.services;

import com.cafeordertracking.app.core.Result;
import com.cafeordertracking.app.entities.Desk;

import java.util.List;

public interface DeskService {

    Result addDesk(Desk desk);

    Result<List<Desk>> getAllDesks();
    Result deleteDesk(String deskId);

}
