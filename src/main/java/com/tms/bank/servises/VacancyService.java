package com.tms.bank.servises;


import com.tms.bank.dto.VacancyDTO;
import com.tms.bank.mapper.VacancyMapper;
import com.tms.bank.models.Vacancy;
import com.tms.bank.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public List<Vacancy> findByAllVacancy(){
        return vacancyRepository.findAll();
    }

    public VacancyDTO createVacancy (VacancyDTO vacancyDTO){
        VacancyDTO newVacancyDTO = VacancyMapper.mapToDTO(vacancyRepository.save(VacancyMapper.mapToEntity(vacancyDTO)));

        return newVacancyDTO;
    }

    public boolean existsVacancyById(Long id){
        return vacancyRepository.existsById(id);
    }

    public VacancyDTO findVacancyById(Long id){
        if(vacancyRepository.findById(id).isPresent()){
            return VacancyMapper.mapToDTO(vacancyRepository.findById(id).get());
        }
        return null;
    }

    public ArrayList<Vacancy> addListVacanvyForshowVacanciesDetail(Long id){
        Optional<Vacancy> vacancy = vacancyRepository.findById(id);
        ArrayList<Vacancy> vacancies = new ArrayList<>();
        vacancy.ifPresent(vacancies::add);
        return vacancies;
    }

    public Vacancy editVacancies (Long id){
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        vacancyRepository.save(vacancy);
        return vacancy;
    }

    public void deleteVacancies (Long id){//castomException trows
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        vacancyRepository.delete(vacancy);
    }

    public void saveVacancy (Vacancy vacancy){
        vacancyRepository.save(vacancy);
    }
}
