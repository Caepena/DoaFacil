package br.com.fiap.campanha;

import br.com.fiap.config.MessageHelper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final MessageHelper messageHelper;

    public CampanhaService(CampanhaRepository campanhaRepository, MessageHelper messageHelper) {
        this.campanhaRepository = campanhaRepository;
        this.messageHelper = messageHelper;
    }

    public List<Campanha> getAllCampanhas() { return campanhaRepository.findAll(); }

    public Campanha save(Campanha campanha) { return campanhaRepository.save(campanha); }

    public void deleteById(Long id) { campanhaRepository.delete(getCampanha(id)); }

    private Campanha getCampanha(Long id) {
        return campanhaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(messageHelper.get("campaign.notfound"))
        );
    }
}
