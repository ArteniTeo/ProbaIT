package com.probaIT.ProbaIt.domain.controller;

import com.probaIT.ProbaIt.domain.entities.Option;
import com.probaIT.ProbaIt.domain.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequiredArgsConstructor
public class OptionController {

    private final OptionService service;

    @GetMapping(value = "options")
    public List<Option> getAllOptionsOfAPollById(@RequestParam(name = "id") Long id) {
        return service.getAllOptionsOfAPoll(id);
    }

    @PostMapping(value = "option")
    public ResponseEntity<Option> createOption(@RequestBody Option option) {
        Option createdOption = service.createOption(option);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOption);
    }

    @PutMapping(value = "option")
    public Option editOption(@RequestBody Option option) {
        return service.editOption(option);
    }

    @DeleteMapping(value = "option")
    public void deleteOption(@RequestParam(name = "id") Long id) {
        service.removeOption(id);
    }

    @DeleteMapping(value = "optionByPollId")
    public void deleteOptionByPollId(@RequestParam(name = "id") Long pollId) {
        service.removeOptionByPollId(pollId);
    }

}
