package ru.shanin.mydatalist.presentation.fragments.about_people;

import androidx.lifecycle.ViewModel;

import ru.shanin.mydatalist.app.AppStart;
import ru.shanin.mydatalist.domain.entity.People;
import ru.shanin.mydatalist.domain.usecases.PeopleGetByIdUseCase;

public class AboutPeopleViewModel extends ViewModel {
    private final PeopleGetByIdUseCase getById = AppStart.peopleGetById;

    protected People getPeople(int peopleId) {
        return getById.peopleGetById(peopleId);
    }

}