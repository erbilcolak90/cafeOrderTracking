package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.core.Result;
import com.kofteciyusuf.app.entities.Desk;

import java.util.List;

public interface DeskService {

    Result addDesk(Desk desk);

    Result<List<Desk>> getAllDesks();
    Result deleteDesk(String deskId);

}
