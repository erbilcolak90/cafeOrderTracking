package com.kofteciyusuf.app.businness.services.managers;

import com.kofteciyusuf.app.businness.services.DeskService;
import com.kofteciyusuf.app.entities.Desk;
import com.kofteciyusuf.app.repositories.DeskRepository;
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
    public Desk addDesk(Desk desk) {

        try {
            desk.setCreateDate(new Date());
            desk.setUpdateDate(new Date());
            desk.setActiveOrderId(null);
            //save desk
            this.deskRepository.save(desk);
            return desk;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Desk> getAllDesks() {
        try {
            return this.deskRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Desk deleteDesk(String deskId) {
        try {
            Desk desk = this.deskRepository.findById(deskId).orElseThrow();
            desk.setDeleted(true);
            desk.setUpdateDate(new Date());
            //save desk database
            this.deskRepository.save(desk);
            return desk;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
