package com.kofteciyusuf.app.controllers;

import com.kofteciyusuf.app.businness.services.DeskService;
import com.kofteciyusuf.app.entities.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desks")
public class DeskController {

    private DeskService deskService;

    @Autowired
    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @PostMapping("/addDesk")
    public Desk addDesk(@RequestBody Desk desk){
        return this.deskService.addDesk(desk);
    }

    @GetMapping("/getAllDesks")
    public List<Desk> getAllDesks(){
        return this.deskService.getAllDesks();
    }
}
