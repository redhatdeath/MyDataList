package ru.shanin.mydatalist.domain.usecases;

import ru.shanin.mydatalist.domain.entity.People;
import ru.shanin.mydatalist.domain.repository.PeopleDomainRepository;

public class PeopleGetByIdUseCase {

    private final PeopleDomainRepository repository;

    public PeopleGetByIdUseCase(PeopleDomainRepository repository) {
        this.repository = repository;
    }

    public People peopleGetById(String _id) {
        return repository.peopleGetById(_id);
    }
}