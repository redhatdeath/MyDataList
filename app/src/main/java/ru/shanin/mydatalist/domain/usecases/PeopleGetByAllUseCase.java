package ru.shanin.mydatalist.domain.usecases;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.shanin.mydatalist.domain.entity.People;
import ru.shanin.mydatalist.domain.repository.PeopleDomainRepository;

public class PeopleGetByAllUseCase {

    private final PeopleDomainRepository repository;

    public PeopleGetByAllUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<People>> peopleGetByAll() {
        return repository.peopleGetAll();
    }
}