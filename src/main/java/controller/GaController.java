package controller;

import data.Data;
import ga.GaCore;
import ga.Nemu;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EnableAutoConfiguration
@RestController
public class GaController {
    @RequestMapping(value = "/{genLength}/{baseGenLength}", produces = "application/json")
    public HashMap<Integer, Double> sayHello(@PathVariable Integer genLength, @PathVariable Integer baseGenLength) {
        HashMap<Integer, List> map;
        Nemu nemu = new Nemu(genLength, baseGenLength);
        return nemu.run(100);
    }
}
