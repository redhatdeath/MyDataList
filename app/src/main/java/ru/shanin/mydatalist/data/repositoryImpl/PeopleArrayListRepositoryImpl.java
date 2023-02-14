package ru.shanin.mydatalist.data.repositoryImpl;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import ru.shanin.mydatalist.app.AppStart;
import ru.shanin.mydatalist.data.NewData;
import ru.shanin.mydatalist.domain.entity.People;
import ru.shanin.mydatalist.domain.repository.PeopleDomainRepository;

public class PeopleArrayListRepositoryImpl implements PeopleDomainRepository {

    private final MutableLiveData<ArrayList<People>> dataLiveData;
    private final ArrayList<People> data;

    {
        data = new ArrayList<>();
        dataLiveData = new MutableLiveData<>();
    }

    private static int autoIncrementId = 0;

    {
        for (int i = 0; i < 50; i++) {
            peopleAddNew(NewData.newPeople());
        }
    }

    private void updateList() {
        dataLiveData.postValue(new ArrayList<>(data));
        if (AppStart.isLog) {
            Log.w("PeopleArrayListRepositoryImpl", data.size() + "\n");
        }
    }

    @Override
    public void peopleAddNew(People people) {
        data.add(people);
        data.sort(People.byID);
        updateList();
    }

    @Override
    public void peopleEditById(People people) {
        People people_old = peopleGetById(people.get_id_sha256());
        peopleDeleteById(people_old);
        peopleAddNew(people);
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
        // TODO find by position in arraylist. not by id. need change
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
