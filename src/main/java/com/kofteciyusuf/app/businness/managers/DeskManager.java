package com.kofteciyusuf.app.businness.managers;

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

        try{
            desk.setCreateDate(new Date());
            desk.setUpdateDate(new Date());
            desk.setActiveOrderId(null);

            this.deskRepository.save(desk);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return desk;
    }

    @Override
    public List<Desk> getAllDesks() {
        return this.deskRepository.findAll();
    }

}
