package com.example.ApiRestErrorHandling.services;

import com.example.ApiRestErrorHandling.entities.Victime;
import com.example.ApiRestErrorHandling.repositories.VictimeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VictimeService {
    VictimeRepository victimeRepository;
    public VictimeService(VictimeRepository victimeRepository)
    {
        this.victimeRepository = victimeRepository;
    }

    public Victime addVictime(Victime victimeToAdd) throws EntityExistsException
    {
        Victime victime = victimeRepository.findByNumerotel(victimeToAdd.getNumerotel());
        if(victime!=null)
        {
            throw new EntityExistsException("Ce numero de téléphone existe");
        }
        return victimeRepository.save(victimeToAdd);
    }

    public Victime getVictimeById(int idVictime)
    {
        return victimeRepository.findById(idVictime).orElseThrow(
                ()-> new EntityNotFoundException("Victime non trouvée à l'id: "+idVictime)
        );
    }

    public List<Victime> getAllVictimes(){
        return victimeRepository.findAll();
    }

    public Boolean deleteById(int idVictime)
    {
        victimeRepository.deleteById(idVictime);
        return true;
    }

    public String updateVictime(Victime victime)
    {
        Victime victimeExist = victimeRepository.findById(victime.getId()).orElseThrow(
                ()-> new EntityNotFoundException("Victime non trouveé")
        );
        victimeExist.setNom(victime.getNom());
        victimeExist.setPrenom(victime.getPrenom());
        victimeExist.setNumerotel(victime.getNumerotel());
        victimeRepository.save(victimeExist);
        return "Modification éffectuée avec succès";
    }
}
