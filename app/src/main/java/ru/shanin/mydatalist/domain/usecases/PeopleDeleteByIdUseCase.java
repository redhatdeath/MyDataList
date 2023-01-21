package ru.shanin.mydatalist.domain.usecases;


import ru.shanin.mydatalist.domain.entity.People;
import ru.shanin.mydatalist.domain.repository.PeopleDomainRepository;

public class PeopleDeleteByIdUseCase {
    private PeopleDomainRepository repository;

    public PeopleDeleteByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public void peopleDeleteById(People people) {
        repository.peopleDeleteById(people);
    }

}
