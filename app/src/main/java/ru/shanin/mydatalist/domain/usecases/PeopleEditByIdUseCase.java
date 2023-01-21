package ru.shanin.mydatalist.domain.usecases;


import ru.shanin.mydatalist.domain.entity.People;
import ru.shanin.mydatalist.domain.repository.PeopleDomainRepository;

public class PeopleEditByIdUseCase {
    private PeopleDomainRepository repository;

    public PeopleEditByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public void peopleEditById(People people) {
        repository.peopleEditById(people);
    }

}
