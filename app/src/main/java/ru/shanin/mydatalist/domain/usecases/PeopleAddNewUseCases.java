package ru.shanin.mydatalist.domain.usecases;


import ru.shanin.mydatalist.domain.entity.People;
import ru.shanin.mydatalist.domain.repository.PeopleDomainRepository;

public class PeopleAddNewUseCases {
    private final PeopleDomainRepository repository;

    public PeopleAddNewUseCases(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public void peopleAddNew(People people) {
        repository.peopleAddNew(people);
    }
}
