package com.epam.entity;

import java.util.Date;
import java.util.Set;

public class Vacancy {
    private long id;
    private HR hr;
    private Company company;
    private Date addingDate;
    private Date closeDate;
    private String description;
    private Set<VacancyRequest> vacancyRequests;

    public Vacancy(long id, HR hr, Company company, Date addingDate, Date closeDate, String description, Set<VacancyRequest> vacancyRequests) {
        this.id = id;
        this.hr = hr;
        this.company = company;
        this.addingDate = addingDate;
        this.closeDate = closeDate;
        this.description = description;
        this.vacancyRequests = vacancyRequests;
    }

    public HR getHr() {
        return hr;
    }

    public void setHr(HR hr) {
        this.hr = hr;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public Vacancy() {
    }
}
