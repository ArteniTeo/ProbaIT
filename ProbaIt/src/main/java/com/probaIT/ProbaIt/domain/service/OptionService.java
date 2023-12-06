package com.probaIT.ProbaIt.domain.service;

import com.probaIT.ProbaIt.domain.entities.Option;
import com.probaIT.ProbaIt.domain.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository repository;

    public Option createOption(Option option) {
        return repository.save(option);
    }

    public List<Option> getAllOptionsOfAPoll(Long pollId) {
        return repository.findByPollId(pollId);
    }

    public Option editOption(Option option) {
        return repository.save(option);
    }

    public void removeOption(Long id) {
        repository.deleteById(id);
    }

    public void removeOptionByPollId(Long id) {
        List<Option> optionsToBeDeleted = getAllOptionsOfAPoll(id);
        for (Option option : optionsToBeDeleted) {
            removeOption(option.getId());
        }
    }

}
