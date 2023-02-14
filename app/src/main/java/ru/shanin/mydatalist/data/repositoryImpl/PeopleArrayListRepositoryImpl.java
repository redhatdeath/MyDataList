package ru.shanin.mydatalist.data.repositoryImpl;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.shanin.mydatalist.domain.entity.People;
import ru.shanin.mydatalist.domain.repository.PeopleDomainRepository;

public class PeopleArrayListRepositoryImpl implements PeopleDomainRepository {

    private final MutableLiveData<ArrayList<People>> dataLiveData;
    private final ArrayList<People> data;

    {
        data = new ArrayList<>();
        dataLiveData = new MutableLiveData<>();
    }


    @Override
    public void peopleAddNew(People people) {
        data.add(people);
        data.sort(People.byID);
        updateList();
    }

    private void updateList() {
        dataLiveData.postValue(new ArrayList<>(data));
    }

    @Override
    public void peopleEditById(People people) {
        People people_old = peopleGetById(people.get_id_sha256());
        peopleDeleteById(people_old);
        People people_new = new People(people.getPeopleInfo());
        peopleAddNew(people_new);
    }

    @Override
    public void peopleDeleteById(People people) {
        data.remove(people);
        updateList();
    }

    @Override
    public MutableLiveData<ArrayList<People>> peopleGetAll() {
        return dataLiveData;
    }

    @Override
    public People peopleGetById(String _id) {
        People people = findByIdInData(_id);
        if (people != null)
            return people;
        else throw new RuntimeException("There is no element with id = " + _id);
    }

    private People findByIdInData(String id) {
        for (People p : data)
            if (p.get_id_sha256().equals(id))
                return p;
        return null;
    }
}
