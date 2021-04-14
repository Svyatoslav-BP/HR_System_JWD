package com.epam.entity;

import java.util.Set;

public class HR extends Account{

    private Set<Vacancy> vacancies;

    public HR() {
    }

    public Set<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public HR(Set<Vacancy> vacancies, long id, String login, String email, String password) {
        super(id, login, email, password);
        this.vacancies = vacancies;
    }
}
