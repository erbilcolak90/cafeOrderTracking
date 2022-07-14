package com.cafeordertracking.app.businness.services.managers;

import com.cafeordertracking.app.businness.services.DeskService;
import com.cafeordertracking.app.core.Result;
import com.cafeordertracking.app.entities.Desk;
import com.cafeordertracking.app.repositories.DeskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class DeskManager implements DeskService {

    private DeskRepository deskRepository;

    @Autowired
    public DeskManager(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }


    @Override
    public Result addDesk(Desk desk) {

        try {
            desk.setCreateDate(new Date());
            desk.setUpdateDate(new Date());
            desk.setActiveOrderId(null);
            //save desk
            this.deskRepository.save(desk);
            return new Result(true,"Desk added",null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<Desk>> getAllDesks() {
        try {
            return new Result<List<Desk>>(true,"Desks listed",this.deskRepository.findAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result deleteDesk(String deskId) {
        try {
            Desk desk = this.deskRepository.findById(deskId).orElseThrow();
            desk.setDeleted(true);
            desk.setUpdateDate(new Date());
            //save desk database
            this.deskRepository.save(desk);
            return new Result(true,"Desk deleted",null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
