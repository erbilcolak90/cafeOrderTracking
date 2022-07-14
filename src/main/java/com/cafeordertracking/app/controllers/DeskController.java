package com.cafeordertracking.app.controllers;

import com.cafeordertracking.app.businness.services.DeskService;
import com.cafeordertracking.app.core.Result;
import com.cafeordertracking.app.entities.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Result addDesk(@Valid @RequestBody Desk desk){
        return this.deskService.addDesk(desk);
    }

    @GetMapping("/getAllDesks")
    public Result<List<Desk>> getAllDesks(){
        return this.deskService.getAllDesks();
    }

    @DeleteMapping("/deleteDesk")
    public Result deleteDesk(@Valid @RequestParam String deskId){
        return this.deskService.deleteDesk(deskId);
    }
}
