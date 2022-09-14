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

//    public Vacancy findVacancyById(Long id){
//        if(vacancyRepository.findById(id).isPresent()){
//            return vacancyRepository.findById(id).get();
//        }
//        return null;
//    }

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

    public void deleteVacancies (Long id){
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow();
        vacancyRepository.delete(vacancy);
    }

    public void saveVacancy (Vacancy vacancy){
        vacancyRepository.save(vacancy);
    }

    public boolean findVacancyByVocation(String vocation){
        if (vacancyRepository.getVacancyByVocationVacancy(vocation).isPresent()) {
            return true;
        }
        return false;
    }

    public Long getIDByVocation(String vocation){
        Long id = null;
        if (vacancyRepository.getVacancyByVocationVacancy(vocation).isPresent()) {
            id = vacancyRepository.getVacancyByVocationVacancy(vocation).get().getId();
        }
        return id;
    }
}
