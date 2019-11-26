package com.stackroute.graphcomservice.service;

import com.stackroute.graphcomservice.model.*;


import java.util.Collection;
import java.util.List;

public interface PersonService {

        public Person savePerson(Person person,String gender);
        public Person setLabel(Person person,String gender);
        public Person setLabel1(Person person,String gender);
        public Religion saveReligion(Religion religion);
        public Manglik saveManglik(Manglik manglik);
        public CurrentCity saveCurrentCity(CurrentCity currentCity);
        public Language saveLanguage(Language language);
        public NativeCity saveNativeCity(NativeCity nativeCity);
        public Hobbies saveHobbies(Hobbies hobbies);
        public Interests saveInterests(Interests interests);
        public AgeRange saveAgeRange(AgeRange ageRange);
        public HeightRange saveHeightRange(HeightRange heightRange);
        public Drink saveDrink(Drink drink);
        public Smoke saveSmoke(Smoke smoke);
        public Challenged saveChallenged(Challenged challenged);
        public Diet saveDiet(Diet diet);
        public Marital saveMarital(Marital marital);
        public Profession saveProfession(Profession profession);
        public Education saveEducation(Education education);
        public Company saveCompany(Company company);
        public Experience saveExperience(Experience experience);
        public IncomeRange saveIncomeRange(IncomeRange incomeRange);
        public Field saveField(Field field);
        public Institution saveInstitution(Institution institution);


        public Collection<String> PersonReligion(Person person, Religion religion);
        public Collection<String> PersonManglik(String email, Manglik manglik);
        public Collection<String> PersonLanguage(Person person, Language language);
        public Collection<String> PersonCCity(Person person, CurrentCity currentCity);
        public Collection<String> PersonnNCity(Person person, NativeCity nativeCity);
        public Collection<String> PersonHobbies(Person person, Hobbies hobbies);
        public Collection<String> PersonInterests(Person person, Interests interests);
        public Collection<String> PersonAgeRange(Person person, AgeRange ageRange);
        public Collection<String> PersonHeightRange(Person person, HeightRange heightRange, float height);
        public Collection<String> PersonDrink(Person person, Drink drink);
        public Collection<String> PersonSmoke(Person person, Smoke smoke);
        public Collection<String> PersonChallenged(Person person, Challenged challenged);
        public Collection<String> PersonDiet(Person person, Diet diet);
        public Collection<String> PersonMarital(Person person, Marital marital);
        public Collection<String> PersonProfession(String email, Profession profession);
        public Collection<String> PersonEducation(String email, Education education);
        public Collection<String> PersonCompany(Profession profession, Company company);
        public Collection<String> PersonExperience(Profession profession, Experience experience);
        public Collection<String> PersonIncomeRange(Profession profession, IncomeRange incomeRange, float income);
        public Collection<String> PersonField(Education education, Field field);
        public Collection<String> PersonInstitution(Education education, Institution institution);


}
