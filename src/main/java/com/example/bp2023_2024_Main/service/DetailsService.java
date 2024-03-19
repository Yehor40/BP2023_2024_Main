package com.example.bp2023_2024_Main.service;

import com.example.bp2023_2024_Main.entity.Project_Task;
import com.example.bp2023_2024_Main.entity.Task_Details;
import com.example.bp2023_2024_Main.repository.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DetailsService {
@Autowired
    private DetailsRepository detailsRepository;

    public List<Task_Details> getAllDetails() {
        return detailsRepository.findAll();
    }

    public Optional<Task_Details> getDetailById(Long id) {
        return detailsRepository.findById(id);
    }

    public Task_Details createDetail(Task_Details details) {
        return detailsRepository.save(details);
    }

    public Task_Details updateDetail(Task_Details details) {
        return detailsRepository.save(details);
    }
    public Task_Details updateDetail(Task_Details updatedDetail, Long id) {
        Task_Details existingDetail = new Task_Details();
        existingDetail = detailsRepository.findById(id).orElseThrow();
        existingDetail.setCharge(updatedDetail.getCharge());
        existingDetail.setMonth(updatedDetail.getMonth());
        existingDetail.setState(updatedDetail.getState());
        existingDetail.setRealTime(updatedDetail.getRealTime());
        existingDetail.setTimeSpent(updatedDetail.getTimeSpent());
        existingDetail.setTotal(updatedDetail.getTotal());
        existingDetail.setTask(updatedDetail.getTask());

        return detailsRepository.save(existingDetail);
    }

    public void deleteDetail(Long id) {
        detailsRepository.deleteById(id);
    }

public int calculateTotal(Long id){
    Task_Details details = detailsRepository.findById(id).orElse(null);
    int res = Integer.parseInt(details.getTotal()) ;
    if (details != null) {
        return res = Integer.parseInt(details.getCharge()) * Integer.parseInt(details.getRealTime());
    }
    else {
        return 0;
    }
}
}


