package com.epam.domain;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HR hr = (HR) o;
        return Objects.equals(vacancies, hr.vacancies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vacancies);
    }
}
