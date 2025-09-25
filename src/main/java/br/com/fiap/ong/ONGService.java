package br.com.fiap.ong;

import br.com.fiap.config.MessageHelper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ONGService {

    private final ONGRepository ONGRepository;
    private final MessageHelper messageHelper;

    public ONGService(ONGRepository ONGRepository, MessageHelper messageHelper) {
        this.ONGRepository = ONGRepository;
        this.messageHelper = messageHelper;
    }

    public ONG save(ONG ong) {
        return ONGRepository.save(ong);
    }

    public List<ONG> getAllONGS() {
        return ONGRepository.findAll();
    }

    public void deleteById(Long id) {
        ONGRepository.delete(getONG(id));
    }

    private ONG getONG(Long id) {
        return ONGRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(messageHelper.get("ong.notfound"))
        );
    }
}