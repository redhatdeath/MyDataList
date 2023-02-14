package ru.shanin.mydatalist.app;

import android.app.Application;

import ru.shanin.mydatalist.data.repositoryImpl.PeopleArrayListRepositoryImpl;
import ru.shanin.mydatalist.domain.usecases.PeopleAddNewUseCases;
import ru.shanin.mydatalist.domain.usecases.PeopleDeleteByIdUseCase;
import ru.shanin.mydatalist.domain.usecases.PeopleEditByIdUseCase;
import ru.shanin.mydatalist.domain.usecases.PeopleGetByAllUseCase;
import ru.shanin.mydatalist.domain.usecases.PeopleGetByIdUseCase;


public class AppStart extends Application {
    public static PeopleArrayListRepositoryImpl impl;
    public static PeopleGetByAllUseCase peopleGetByAll;
    public static PeopleGetByIdUseCase peopleGetById;
    public static PeopleAddNewUseCases peopleAddNew;
    public static PeopleEditByIdUseCase peopleEditById;
    public static PeopleDeleteByIdUseCase peopleDeleteById;

    @Override
    public void onCreate() {
        super.onCreate();
        setupRepositoryImpl();
        setupUseCases();
    }

    private void setupRepositoryImpl() {
        impl = new PeopleArrayListRepositoryImpl();
    }

    private void setupUseCases() {
        peopleGetByAll = new PeopleGetByAllUseCase(impl);
        peopleGetById = new PeopleGetByIdUseCase(impl);
        peopleAddNew = new PeopleAddNewUseCases(impl);
        peopleEditById = new PeopleEditByIdUseCase(impl);
        peopleDeleteById = new PeopleDeleteByIdUseCase(impl);
    }
}
