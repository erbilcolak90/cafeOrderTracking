package com.kofteciyusuf.app.businness.services.managers;

import com.kofteciyusuf.app.businness.services.DeskService;
import com.kofteciyusuf.app.core.DataResult;
import com.kofteciyusuf.app.core.Result;
import com.kofteciyusuf.app.core.SuccessDataResult;
import com.kofteciyusuf.app.core.SuccessResult;
import com.kofteciyusuf.app.entities.Desk;
import com.kofteciyusuf.app.repositories.DeskRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            return new SuccessResult("Desk added");

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Desk not created",ex);
        }
    }

    @Override
    public DataResult<List<Desk>> getAllDesks() {
        try {
            return new SuccessDataResult<List<Desk>>("Desks listed",this.deskRepository.findAll());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Desks not listed",ex);
        }
    }

    @Override
    public Result deleteDesk(String deskId) {
        try {
            Desk desk = this.deskRepository.findById(deskId).orElseThrow();
            desk.setDeleted(true);
            desk.setUpdateDate(new Date());
            //save desk database
            this.deskRepository.save(desk);
            return new SuccessResult("Desk deleted");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"Desk not deleted",ex);
        }
    }
}
