package ru.shanin.mydatalist.domain.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.shanin.mydatalist.domain.entity.People;

public interface PeopleDomainRepository {

    void peopleAddNew(People people);

    void peopleEditById(People people);

    void peopleDeleteById(People people);

    MutableLiveData<ArrayList<People>> peopleGetAll();

    People peopleGetById(int _id);

}
