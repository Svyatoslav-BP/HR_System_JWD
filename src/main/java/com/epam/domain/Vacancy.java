package com.epam.domain;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Vacancy {
    private long id;
    private HR hr;
    private Date addingDate;
    private Date closeDate;
    private String description;
    private Set<VacancyRequest> vacancyRequests;

    public Vacancy(long id, HR hr, Date addingDate, Date closeDate, String description, Set<VacancyRequest> vacancyRequests) {
        this.id = id;
        this.hr = hr;
        this.addingDate = addingDate;
        this.closeDate = closeDate;
        this.description = description;
        this.vacancyRequests = vacancyRequests;
    }

    public Vacancy() {
    }

    public HR getHr() {
        return hr;
    }

    public void setHr(HR hr) {
        this.hr = hr;
    }

    public Date getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(Date addingDate) {
        this.addingDate = addingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<VacancyRequest> getVacancyRequests() {
        return vacancyRequests;
    }

    public void setVacancyRequests(Set<VacancyRequest> vacancyRequests) {
        this.vacancyRequests = vacancyRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id && Objects.equals(hr, vacancy.hr) && Objects.equals(addingDate, vacancy.addingDate) && Objects.equals(closeDate, vacancy.closeDate) && Objects.equals(description, vacancy.description) && Objects.equals(vacancyRequests, vacancy.vacancyRequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hr, addingDate, closeDate, description, vacancyRequests);
    }
}
